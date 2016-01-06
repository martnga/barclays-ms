package org.mansa.barclaysms;

/**
 * Created by mansa on 1/4/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by nganga on 9/22/15.
 */
public class MyFragment extends Fragment {

    ListView listView;
    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.my_fragment, container, false);
        // Get ListView object from xml
        listView = (ListView) layout.findViewById(R.id.list);
        TextView textView = (TextView) layout.findViewById(R.id.tabFragmentText);
        Bundle bundle = getArguments();

        if (bundle != null) {

            textView.setText("You are on Page " + bundle.getInt("position"));
        }


        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Request Statement",
                "Deposit",
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


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

                        break;
                    case 1:

                        break;
                }

            }

        });


        return layout;
    }


}
