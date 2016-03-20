package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.FirstRunChecker;
import edu.gatech.seclass.scm.utils.ObjectFactory;

public class HomeActivity extends AppCompatActivity {

    private Manager manager;

    private AppSession appSession;

    String msg = "HomeScreen : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        appSession = (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
        appSession.setContext(HomeActivity.this);

        setContentView(R.layout.activity_home);
        setView();
        setBtnHandler();
        Log.d(msg, "onCreate() called");
        FirstRunChecker.checkFirstRun(HomeActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        Log.d(msg, "onCreateOptionsMenu() called");
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
            Log.i(msg, "A button was clicked: " + view.getId());
            switch (view.getId()) {
                case R.id.scanQrButton:
                    Log.i(msg, "Scan QR Button Pressed");
                    TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
                    if(manager.scanQRForCustomer()){
                        intent = new Intent(HomeActivity.this, CustomerReview.class);
                        errorMessage.setText("");
                    }else{
                        errorMessage.setText(AppConstants.SCAN_QR_ERR_MSG);
                    }
                    break;
                case R.id.addCustomerButton:
                    Log.i(msg, "Add Customer Button Pressed");
                    manager.clearCustomer();
                     intent = new Intent(HomeActivity.this, CustAcctActivity.class);
                    break;
                case R.id.selectCustomerButton:
                    Log.i(msg, "Select Customer Button Pressed");
                    intent = new Intent(HomeActivity.this, SelectCustomerActivity.class);
                    break;
                case R.id.orderReturnButton:
                    Log.i(msg, "Return to Order Button Pressed");
                    intent = new Intent(HomeActivity.this, ProductActivity.class);
                    break;
                case R.id.customerClearButton:
                    Log.i(msg, "Clear Customer Button Pressed");
                    manager.clearCustomer();
                    finish();
                    startActivity(getIntent());
                    break;
                default:
                    Log.e(msg, "Invalid Id returned from click: " + view.getId());
            }

            if (intent != null) {
                HomeActivity.this.startActivity(intent);
            }
        }
    }

    private boolean managerSet() {
        boolean toRet = false;
        if (manager != null)
            toRet = true;
        return toRet;
    }

    private void visibilityCustomerInformation(boolean toShow) {
        visibilityCustomerName(toShow);
        visibilityCustomerNameText(toShow);

        if (toShow) {
            String customerName = manager.getCustomerName();

            TextView cName = (TextView) findViewById(R.id.customerNameText);

            cName.setText(customerName);
        }
    }

    // TextView appears to be the super class to Button
    private void changeVisibility(TextView w, boolean toShow) {
        if (toShow)
            w.setVisibility(View.VISIBLE);
        else
            w.setVisibility(View.GONE);
    }

    private void visibilityCustomerName(boolean toShow) {
        changeVisibility((TextView) findViewById(R.id.customerName), toShow);
    }

    private void visibilityCustomerNameText(boolean toShow) {
        changeVisibility((TextView) findViewById(R.id.customerNameText), toShow);
    }

    private void visibilityCustomerClearButton(boolean toShow) {
        changeVisibility((TextView) findViewById(R.id.customerClearButton), toShow);
    }

    private void visibilityCustomerOrderButton(boolean toShow) {
        changeVisibility((TextView) findViewById(R.id.orderReturnButton), toShow);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setView() {
        if (!managerSet()) {
            manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        }

        if (manager.customerSet()) {
            visibilityCustomerInformation(true);
            visibilityCustomerClearButton(true);
            visibilityCustomerOrderButton(true);
        } else {
            visibilityCustomerInformation(false);
            visibilityCustomerClearButton(false);
            visibilityCustomerOrderButton(false);
        }
    }

    private void setBtnHandler() {
        Button b;

        b = (Button) findViewById(R.id.scanQrButton);
        b.setOnClickListener(new BtnHandler());
        b = (Button) findViewById(R.id.addCustomerButton);
        b.setOnClickListener(new BtnHandler());
        b = (Button) findViewById(R.id.selectCustomerButton);
        b.setOnClickListener(new BtnHandler());
        b = (Button) findViewById(R.id.orderReturnButton);
        b.setOnClickListener(new BtnHandler());
        b = (Button) findViewById(R.id.customerClearButton);
        b.setOnClickListener(new BtnHandler());
    }
}
