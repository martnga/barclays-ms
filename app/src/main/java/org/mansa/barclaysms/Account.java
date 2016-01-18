package org.mansa.barclaysms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mansa on 1/4/16.
 */
public class Account extends AppCompatActivity{

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Balance",
                "Statement",
                "Payment",
                "Withdrawal",
                "Transfer to Mpesa"
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.ic_touch_app_black_24dp,
                R.drawable.ic_description_black_24dp,
                R.drawable.ic_import_export_black_24dp,
                R.drawable.ic_eject_black_24dp,
                R.drawable.ic_flight_takeoff_black_24dp
        };

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<flags.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "" + values[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.account_list_view, from, to);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data



        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                switch (itemPosition){
                    case 0:
                        setAlertDialog("Account Balance", "Your Account Balance is Ksh 134,500. Thank you for using Barclays MS");
                        break;
                    case 1:
                        setAlertDialog("Account Statement","Sent Ksh 4,400 to JOHN DOE MAHINDI\n\n" +
                                "04/12/2015   Sent Ksh 3,500 to BURU DONAH DIHEM\n\n" +
                                "04/12/2015   Paid Ksh 14,450 to DUALCOM VENTURES\n\n" +
                                "12/12/2015   Sent Ksh 400 to BIKO LAS VEGAS\n\n" +
                                "17/12/2015   Transfered Ksh 56,400 to MUTOKO DILR MISO\n\n" +
                                "07/01/2016   Sent Ksh 1,400 to CALTECH CHAMA\n\n" +
                                "08/01/2016   Received Ksh 104,000 from MANDELA BISORO AHMED \n");
                        break;
                    case 2:
                        setInputDialog("Payments", "Enter The Amount Of Payment");
                        break;
                    case 3:
                        setInputDialog("Withdrawal", "Enter The Amount You Want To Withdraw");
                        break;
                    case 4:
                        setInputDialog("Transfer to Mpesa", "Enter The Amount You Want To Transfer To MPesa");
                        break;

                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            ChangePinFragment fragment = new ChangePinFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setAlertDialog(String title,String string){
        AlertDialog.Builder builder = new AlertDialog.Builder(Account.this);
        builder.setTitle(title);
        builder.setMessage(string);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public  void setInputDialog(String title,String string){

        //initialize edit text to get amount received by customer
        final EditText mPIN = new EditText(Account.this);
        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(5); //Filter to 4 characters
        mPIN.setFilters(filters);

        new android.support.v7.app.AlertDialog.Builder(Account.this)
                .setTitle(title)
                .setMessage(string)
                .setView(mPIN, 35, 5, 15, 15)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String amountReceived = mPIN.getText().toString();

                        if (!amountReceived.isEmpty()) {
                            setPinDialog("PIN Confirmation", "Please Input Your PIN To Proceed");
                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();

                    }
                })
                .show();
    }

    public  void setPinDialog(String title,String string){

        //initialize edit text to get amount received by customer
        final EditText mPIN = new EditText(Account.this);
        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mPIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
        mPIN.setFilters(filters);

        new android.support.v7.app.AlertDialog.Builder(Account.this)
                .setTitle(title)
                .setMessage(string)
                .setView(mPIN, 35, 5, 15, 15)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String amountReceived = mPIN.getText().toString();

                        if (!amountReceived.isEmpty()) {
                            setAlertDialog("Confirmed", "Transaction 3F56643F3D Confirmed. Thank you for using Barclays MS.");
                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();

                    }
                })
                .show();
    }

}
