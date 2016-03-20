package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

public class PreviousTransactions extends AppCompatActivity {

    Manager manager = null;
    String msg = "PreviousTransactions : ";
    Transaction[] transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (Manager) ObjectFactory.getInstanceOf(AppConstants.Manager);
        setContentView(R.layout.activity_previous_transactions);
        setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_previous_transactions, menu);
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

    private void setView() {
        buttonSetup();
        listSetup();
    }

    private void listSetup() {
        transactions = manager.getCustomerTransactions();
        if(null != transactions){
            ListView listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.transaction_list_item_1, transactions);
            listView.setAdapter(adapter);
            if(transactions.length == 0){
                TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
                errorMessage.setText(AppConstants.NO_TRANSACTIONS_FOUND);
            }
        }
    }

    private void buttonSetup() {
        Button button;
        button = (Button) findViewById(R.id.homeButton);
        button.setOnClickListener(new BtnHandler());
    }

    class BtnHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;

            switch (view.getId()) {
                case R.id.homeButton:
                    intent = new Intent(PreviousTransactions.this, HomeActivity.class);
                    Log.i(msg, "Home Button pressed");
                    break;

                default:
                    Log.e(msg, "Invalid ID: " + view.getId());
            }

            PreviousTransactions.this.startActivity(intent);
        }
    }
}
