package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

public class CustomerReview extends AppCompatActivity {

    private Button button1, button2, button3;
    private Manager manager;
    private AppSession appSession;
    private Customer cust;

    private String custname = null;
    private TextView tvcustname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        appSession = (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);

        setContentView(R.layout.activity_customer_review);

            cust = appSession.getCurrentCustomer();

            if (cust != null) {
                custname = cust.getName();
                tvcustname = (TextView) findViewById(R.id.txt_Custname);
                tvcustname.setText(custname);
            }

            button1 = (Button) findViewById(R.id.btn_Placeorder);
            button1.setOnClickListener(new BtnHandler());
            button2 = (Button) findViewById(R.id.btn_Editacct);
            button2.setOnClickListener(new BtnHandler());
            button3 = (Button) findViewById(R.id.btn_ViewPurch);
            button3.setOnClickListener(new BtnHandler());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class BtnHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_Placeorder:
                    intent = new Intent(view.getContext(), ProductActivity.class);
                    break;
                case R.id.btn_Editacct:
                    intent = new Intent(view.getContext(), CustAcctActivity.class);
                    break;
                case R.id.btn_ViewPurch:
                    intent = new Intent(view.getContext(), PreviousTransactions.class);
                    break;
            }
                if (intent != null) {
                    startActivity(intent);
                }
        }
    }

}
