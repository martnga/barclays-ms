package org.mansa.barclaysms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 04-06-2015.
 */
public class QuitChamaFragment extends Fragment {

    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quit_chama,container,false);
        // Get ListView object from xml
        listView = (ListView) v.findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Pendo",
                "Vinnas",
                "Caltech",
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.ic_delete_black,
                R.drawable.ic_delete_black,
                R.drawable.ic_delete_black
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
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.account_list_view, from, to);

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
                        setAlertDialog();
                        break;
                    case 1:
                        setAlertDialog();
                        break;
                    case 2:
                        setAlertDialog();
                        break;

                }

            }

        });

        return v;

    }


    public void setAlertDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm Quit");
        builder.setMessage("Are you Sure you want to quit chama?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PinRequest();
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void PinRequest() {
        //initialize edit text to get pin received by customer
        final EditText mPIN = new EditText(getActivity());
        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mPIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
        mPIN.setFilters(filters);
        ;
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirmation Pin")
                .setMessage("Please Input Your PIN To Proceed")
                .setView(mPIN, 35, 5, 15, 15)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String pin = mPIN.getText().toString();

                        if (!pin.isEmpty()) {
                            startActivity(new Intent(getActivity(), Home.class));
                            dialog.dismiss();
                        }

                    }
                }).show();
    }


}
