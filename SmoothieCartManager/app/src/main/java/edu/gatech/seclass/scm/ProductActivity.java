package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.Product;
import edu.gatech.seclass.scm.model.ProductListView;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;
import edu.gatech.seclass.scm.utils.ProductListViewAdapter;

public class ProductActivity extends AppCompatActivity {

    private Manager manager;
    private AppSession appSession;
    private ArrayList<ProductListView> productListViews;
    private String msg = "ProductActivity : ";
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        appSession = (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        payButton = (Button) findViewById(R.id.btnPay);
        disableButton(payButton);
        populateData();
        getProductMapAndSetOnScreenLoad();
        setOrderInfoOnScreen();
    }

    private void populateData() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setClickable(true);
        List<Product> productList = manager.getAllProducts();
        productListViews = populateArrayList(productList);
        ProductListViewAdapter adapter = new ProductListViewAdapter(this,productListViews);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickHandler);
    }

    private ArrayList<ProductListView> populateArrayList(List<Product> productList) {
        ArrayList<ProductListView> tempArrayList = new ArrayList<ProductListView>(productList.size());
        for (Product current : productList) {
            ProductListView temp = new ProductListView(current.getProductID(), current.getProductDesc(), current.getPrice());
            tempArrayList.add(temp);
        }

        return tempArrayList;
    }

    private AdapterView.OnItemClickListener clickHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            ProductListView product = productListViews.get(position);
            product.increaseCount();

            TextView itemCounter = (TextView) view.findViewById(R.id.item_counter);
            itemCounter.setText(product.getCountString());

            manager.addToOrder(product.getProductId(), product.getCount());
            manager.calculateOrder();

            saveProductQtyToMap(product.getProductId(), product.getCount());

            setOrderInfoOnScreen();
        }
    };

    /*
    *Code for the Home Button Click
     */
    public void goHome(View view){
        Intent nextScreen=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(nextScreen);
    }

    /*
    *Code for the Customer Button Click
     */
    public void goCustomer(View view){
        Intent nextScreen=new Intent(getApplicationContext(),CustomerReview.class);
        startActivity(nextScreen);
    }

    /*
   *Code for the Pay Button Click
    */
    public void goPay(View view){
        Intent nextScreen=new Intent(getApplicationContext(),OrderActivity.class);
        startActivity(nextScreen);
    }

    public void resetQty(View view){
        ListView listView = (ListView) findViewById(R.id.listView);
        View list;
        TextView text;
        for(int i=0; i<productListViews.size(); i++){
            ProductListView productView = productListViews.get(i);
            productView.setCountString(0);
            saveProductQtyToMap(productView.getProductId(), 0);
            manager.removeFromOrder(productView.getProductId(), 0);
        }
        setOrderInfoOnScreen();
        startActivity(this.getIntent());
    }

    private void disableButton(Button button){
        button.setEnabled(false);
    }

    private void enableButton(Button button){
        button.setEnabled(true);
    }

    /**
     *
     */
    private void getProductMapAndSetOnScreenLoad(){
        HashMap<Integer, Integer> productMap = appSession.getProductQtyMap();
        if(null != productMap && !productMap.isEmpty()){
            for(ProductListView productView : productListViews){
                if(productMap.containsKey(productView.getProductId())){
                    int qty = productMap.get(productView.getProductId());
                    productView.setCountString(qty);
                }
            }
        }
    }

    /**
     *
     */
    private void setOrderInfoOnScreen(){
        Order order = appSession.getOrder();
        if(null != order){
            if(order.getOrderEntries().size() != 0){
                enableButton(payButton);
            }

            DecimalFormat myFormat = new DecimalFormat("$###,###,###.00");

            Double dis=order.getDiscount();
            String sDiscount = myFormat.format(dis);
            Double sub=order.getSubtotalPrice();
            String sSub=myFormat.format(sub);
            Double dTax = order.getTax();
            String sTax = myFormat.format(dTax);
            Double dTotal=order.getTotalPrice();
            String sTotal=myFormat.format(dTotal);


            TextView subTotal = (TextView) findViewById(R.id.txtSubtotal);
            subTotal.setText(sSub);

            TextView tax = (TextView) findViewById(R.id.txtTax);
            tax.setText(sTax);

            TextView total = (TextView) findViewById(R.id.txtTotal);
            total.setText(sTotal);

            TextView discount = (TextView) findViewById(R.id.txtDiscount);
            discount.setText(sDiscount);
        }
    }

    /**
     *
     * @param productId
     * @param qty
     */
    private void saveProductQtyToMap(Integer productId, Integer qty){
        HashMap<Integer, Integer> productMap = new HashMap<Integer, Integer>();
        productMap.put(productId, qty);
        appSession.setProductQtyMap(productMap);
    }
}
