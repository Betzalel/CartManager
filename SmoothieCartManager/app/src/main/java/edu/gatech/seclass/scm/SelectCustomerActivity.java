package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

public class SelectCustomerActivity extends AppCompatActivity {

    private Manager manager = null;
    Customer[] customers;
    String msg = "SelectCustomer : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);
        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        getCustomersForList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getCustomersForList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setClickable(true);
        customers = manager.getAllCustomers();

        ArrayAdapter<Customer> adapter = new ArrayAdapter<Customer>(this, android.R.layout.simple_expandable_list_item_1, customers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickHandler);

    }

    public void goHome(View view) {
        Log.i(msg, "Home button clicked");
        Intent intent = new Intent(SelectCustomerActivity.this, HomeActivity.class);
        SelectCustomerActivity.this.startActivity(intent);
    }

    public void goScan(View view) {
        manager.scanQRForCustomer();
        if(manager.scanQRForCustomer()){
            Log.i(msg,"ActivityCustomer selected");

            Intent intent = new Intent(SelectCustomerActivity.this, CustomerReview.class);
            SelectCustomerActivity.this.startActivity(intent);
        }
        //Intent intent = new Intent(SelectCustomerActivity.this, ReviewCustomer.class);
        //SelectCustomerActivity.this.startActivity(intent);
        Log.i(msg,"Scan QR button clicked");
    }

    private AdapterView.OnItemClickListener clickHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            Log.i(msg, "The item in position " + position + " was clicked");
            Customer customer = customers[position];
            Log.i(msg, "Customer Name:  " + customer.getName());
            Log.i(msg, "Customer Email: " + customer.getEmailAddress());
            // TODO: Remove this
            customer.setReward(7.0);
            // -------
            getAppSession().setCurrentCustomer(customer);
            Intent intent = new Intent(SelectCustomerActivity.this, CustomerReview.class);
            SelectCustomerActivity.this.startActivity(intent);
        }
    };

    public AppSession getAppSession(){
        return (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
    }
}
