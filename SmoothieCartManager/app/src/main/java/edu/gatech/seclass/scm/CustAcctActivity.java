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

public class CustAcctActivity extends AppCompatActivity {

    TextView tvcustname, tvemail, tvaddress;
    Customer cust = null;
    Button bEdit, bCancel,bOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Manager m = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        setContentView(R.layout.activity_cust_acct);

        cust = getAppSession().getCurrentCustomer();

        setButtonHandler();

        if (cust != null) {
            tvcustname = (TextView) findViewById(R.id.txt_custName);
            tvaddress = (TextView) findViewById(R.id.txt_address);
            tvemail = (TextView) findViewById(R.id.txt_email);
            tvcustname.setText(cust.getName().toString());
            tvaddress.setText(cust.getBillingAddress().toString());
            tvemail.setText(cust.getEmailAddress().toString());
            enabledText(false);
        }
    }

    private void setButtonHandler() {
        bEdit = (Button) findViewById(R.id.btn_editSave);
        bEdit.setOnClickListener(new BtnHandler());
        if (cust != null) {
            bEdit.setTag(1);
        } else {
            bEdit.setTag(0);
            bEdit.setText("Save");
        }
        bCancel = (Button) findViewById(R.id.btn_cancel);
        bCancel.setOnClickListener(new BtnHandler());
        bOrder = (Button) findViewById(R.id.btn_placeorder);
        bOrder.setOnClickListener(new BtnHandler());
        bOrder.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_cust_acct, menu);
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


    class BtnHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;


            if (view.getId() == R.id.btn_editSave)
            {
                if ((Integer) view.getTag() == 1)
                {
                    bEdit.setText("Save");
                    view.setTag(0);
                    if (cust != null) {
                        enabledText(true);
                    }
                }
                else {
                    bEdit.setText("Edit");
                    view.setTag(1);
                    if (cust != null) {
                        enabledText(false);
                    }
                }

            }
            else if (view.getId() == R.id.btn_cancel)
            {
                intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        }
    }

    private void enabledText(boolean flg) {
          tvcustname.setEnabled(flg);
          tvaddress.setEnabled(flg);
          tvemail.setEnabled(flg);
    }

    public AppSession getAppSession(){
        return (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
    }


}
