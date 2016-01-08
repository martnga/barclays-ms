package org.mansa.barclaysms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mansa on 1/6/16.
 */
public class ThreeFragment extends Fragment {

    CardView mOneCardView;
    CardView mTwoCardView;
    TextView mUpTextTwo;
    TextView mUpTextOne;
    TextView mBottomTextOne;
    TextView mBottomTextTwo;
    EditText mDepositAmountEditTxt;
    ImageView mSendButton;
    String mAmountSent;
    int mPotSize = 233;
    String mNotification1 = "You have deposited Ksh ";
    String mNotification2 = "Your remaining Balance is Ksh ";
    String mNotification3 = "Current Chama Pot Size is Ksh ";
    int chamaPot = 36450;
    int status = 0;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_three, container, false);
        mOneCardView = (CardView) layout.findViewById(R.id.card_one);
        mTwoCardView = (CardView) layout.findViewById(R.id.card_two);
        mUpTextOne = (TextView) layout.findViewById(R.id.up_txt_one);
        mUpTextTwo = (TextView) layout.findViewById(R.id.up_txt_two);
        mBottomTextOne = (TextView) layout.findViewById(R.id.down_txt_one);
        mBottomTextTwo = (TextView) layout.findViewById(R.id.down_txt_two);
        mDepositAmountEditTxt = (EditText) layout.findViewById(R.id.deposit_amount_txt);
        mSendButton = (ImageView) layout.findViewById(R.id.btnSend);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmountSent = mDepositAmountEditTxt.getText().toString().trim();
                if(!mAmountSent.isEmpty()){
                    final String mBalance = String.format("%,d",  mPotSize - Integer.parseInt(mAmountSent));
                    final String mPotBalance = String.format("%,d",  chamaPot + Integer.parseInt(mAmountSent));

                    if(status == 0){
                        //initialize edit text to get amount received by customer
                        final EditText mPIN = new EditText(getActivity());
                        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                        mPIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        InputFilter[] filters = new InputFilter[1];
                        filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
                        mPIN.setFilters(filters);

                        new AlertDialog.Builder(getActivity())
                                .setTitle("Confirmation Pin")
                                .setMessage("Please Input Your PIN To Proceed")
                                .setView(mPIN, 35, 5, 15, 15)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String amountReceived = mPIN.getText().toString();

                                        if (!amountReceived.isEmpty()) {
                                            mUpTextOne.setText(mNotification1 + mAmountSent + ". " + mNotification2 + mBalance + "."
                                                    + mNotification3 + mPotBalance + ".");
                                            mBottomTextOne.setText(setTime());
                                            mDepositAmountEditTxt.setText("");
                                            mOneCardView.setVisibility(View.VISIBLE);
                                            status ++;
                                        }


                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();

                                    }
                                })
                                .show();
                    }else if(status == 1){

                        //initialize edit text to get pin received by customer
                        final EditText mPIN = new EditText(getActivity());
                        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                        mPIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        InputFilter[] filters = new InputFilter[1];
                        filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
                        mPIN.setFilters(filters);

                        new AlertDialog.Builder(getActivity())
                                .setTitle("Confirmation Pin")
                                .setMessage("Please Input Your PIN To Proceed")
                                .setView(mPIN, 35, 5, 15, 15)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String amountReceived = mPIN.getText().toString();

                                        if (!amountReceived.isEmpty()) {
                                            mUpTextTwo.setText(mNotification1 + mAmountSent + ". " + mNotification2 + mBalance + "."
                                                    + mNotification3 + mPotBalance + ".");
                                            mBottomTextTwo.setText(setTime());
                                            mDepositAmountEditTxt.setText("");
                                            mTwoCardView.setVisibility(View.VISIBLE);
                                            status += 1;
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
            }
        });

        return layout;
    }

    public String setTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String strDate = sdf.format(c.getTime());
        return  strDate;
    }


}
