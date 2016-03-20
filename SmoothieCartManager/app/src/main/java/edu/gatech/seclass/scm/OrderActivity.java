package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.model.CreditCard;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

public class OrderActivity extends AppCompatActivity {

    private Manager manager;

    private AppSession appSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        appSession = (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
        displayData();
        displayButtons();
    }

    private void displayData() {
        Order order = appSession.getOrder();

        TextView subtotal = (TextView) findViewById(R.id.txtViewSubTotal);
        TextView tax      = (TextView) findViewById(R.id.txtViewTax);
        TextView discount = (TextView) findViewById(R.id.txtViewDiscount);
        TextView total    = (TextView) findViewById(R.id.txtViewTotal);

        //Format with 2 decimal places

        DecimalFormat myFormat = new DecimalFormat("$###,###,###.00");

        Double dis=order.getDiscount();
        String sDiscount = myFormat.format(dis);
        Double sub=order.getSubtotalPrice();
        String sSub=myFormat.format(sub);
        Double dTax = order.getTax();
        String sTax = myFormat.format(dTax);
        Double dTotal=order.getTotalPrice();
        String sTotal=myFormat.format(dTotal);

        subtotal.setText(sSub);
        tax.setText(sTax);
        discount.setText(sDiscount);
        total.setText(sTotal);
    }

    private void displayButtons() {
        if (appSession.getOrder().getTotalPrice() < 0.10000001) {
            Button scan = (Button) findViewById(R.id.btnScan);
            scan.setEnabled(false);
        }
    }
    /*
  *Code for the Pay Button Click
   */
    public void pay(View view) {
        TextView textView = (TextView) findViewById(R.id.cardPaymentResult);
        Button pay = (Button) findViewById(R.id.btnPay);
        Button scan = (Button) findViewById(R.id.btnScan);
        CreditCard card = appSession.getCreditCard();
        Customer customer = appSession.getCurrentCustomer();
        Order order = appSession.getOrder();
        // Order matters. Keep it in this order
        if((appSession.getOrder().getTotalPrice() < 0.10000001) || manager.processPayment(customer, order, card)){
            textView.setText(AppConstants.PAYMENT_SUCCESSFUL);
            pay.setEnabled(false);
            scan.setEnabled(false);
        }else{
            textView.setText(AppConstants.PAYMENT_FAILED);
        }
    }

    /*
    *Code for the ScanCard Button Click
     */
    public void scanCard(View view){
        TextView textView = (TextView) findViewById(R.id.cardPaymentResult);
        EditText editText = (EditText) findViewById(R.id.customerNameText);
        EditText editText2 = (EditText) findViewById(R.id.cardNumberText);
        EditText editText3 = (EditText) findViewById(R.id.expDataText);
        EditText editText4 = (EditText) findViewById(R.id.cvvText);
        textView.setText("");
        editText.setText("Name");
        editText2.setText("Card Number");
        editText3.setText("Expiration Date (MM/YY)");
        editText4.setText("CVV");

        CreditCard card = manager.scanCard();
        if(card == null){
           textView.setText("CARD READ FAILED !! SCAN AGAIN !!");
        }else{
            appSession.setCreditCard(card);
            editText.setText(card.getFirstName() + " " + card.getLastName());
            editText2.setText(card.getCcNumber());
            editText3.setText(card.getExpirationDate());
            editText4.setText(card.getSecurityCode());
        }
    }

    /*
   *Code for the Home Button Click
    */
    public void home(View view){
        Intent nextScreen=new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(nextScreen);
    }

    /*
  *Code for the Cancel Button Click
   */
    public void cancel(View view){
        Intent nextScreen=new Intent(getApplicationContext(), ProductActivity.class);
        startActivity(nextScreen);
    }
}
