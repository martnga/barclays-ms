package org.mansa.barclaysms;

/**
 * Created by mansa on 1/6/16.
 */
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class OneFragment extends Fragment{

    CardView mZeroCardView;
    CardView mOneCardView;
    CardView mTwoCardView;
    TextView mUpTextOne;
    TextView mUpTextTwo;
    TextView mBottomTextOne;
    TextView mBottomTextTwo;
    EditText mDepositAmountEditTxt;
    ImageView mSendButton;
    String mAmountSent;
    int mPotSize = 2496;
    String mNotification1 = "You have deposited Ksh ";
    String mNotification2 = "Your remaining Balance is Ksh ";
    String mNotification3 = "Current Chama Pot Size is Ksh ";
    String mNotification4 = "You have completed your Chama contribution. Ksh ";
    String mNotification5 = " has been fixed to your Virtual Savings account. The more you save the more you earn. Thank you for using Barclays MS";
    int chamaPot = 156202;
    int status = 0;

    public OneFragment() {
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
        View layout = inflater.inflate(R.layout.fragment_one, container, false);
        mOneCardView = (CardView) layout.findViewById(R.id.card_one);
        mZeroCardView = (CardView) layout.findViewById(R.id.card_zero);
        mTwoCardView = (CardView) layout.findViewById(R.id.card_two);
        mUpTextOne = (TextView) layout.findViewById(R.id.up_txt_one);
        mUpTextTwo = (TextView) layout.findViewById(R.id.up_txt_two);
        mBottomTextOne = (TextView) layout.findViewById(R.id.down_txt_one);
        mBottomTextTwo = (TextView) layout.findViewById(R.id.down_txt_two);

        mDepositAmountEditTxt = (EditText) layout.findViewById(R.id.deposit_amount_txt);
        mSendButton = (ImageView) layout.findViewById(R.id.btnSend);

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(5); //Filter to 4 characters
        mDepositAmountEditTxt.setFilters(filters);

        mZeroCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAmountSent = mDepositAmountEditTxt.getText().toString().trim();
                if(!mAmountSent.isEmpty()){
                    int balance = mPotSize - Integer.parseInt(mAmountSent);
                     String mBalance = String.format("%,d", balance);
                    final String mPotBalance = String.format("%,d",  chamaPot + Integer.parseInt(mAmountSent));
                    if (balance < 0) {
                        mBalance = String.format("%,d", 0  - (balance));
                    }
                    mAmountSent = String.format("%,d", Integer.parseInt(mAmountSent));

                    if(status == 0){
                        //initialize edit text to get amount received by customer
                        final EditText mPIN = new EditText(getActivity());
                        mPIN.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                        mPIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        InputFilter[] filters = new InputFilter[1];
                        filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
                        mPIN.setFilters(filters);

                        final String finalMBalance = mBalance;
                        final String finalMBalance2 = mBalance;
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Confirmation Pin")
                                .setMessage("Please Input Your PIN To Proceed")
                                .setView(mPIN, 35, 5, 15, 15)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String amountReceived = mPIN.getText().toString();

                                        if (!amountReceived.isEmpty()) {
                                            setAlertDialog(finalMBalance, mPotBalance);
                                            mUpTextOne.setText(mNotification1 + mAmountSent + ". " + mNotification2 + finalMBalance2 + "."
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

                        final String finalMBalance1 = mBalance;
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Confirmation Pin")
                                .setMessage("Please Input Your PIN To Proceed")
                                .setView(mPIN, 35, 5, 15, 15)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String amountReceived = mPIN.getText().toString();

                                        if (!amountReceived.isEmpty()) {
                                            setSecondDialog(finalMBalance1);
                                            mUpTextTwo.setText(mNotification1 + mAmountSent + "." + mNotification4 + finalMBalance1 + ""
                                                    + mNotification5 + ".");
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

    public void setAlertDialog(final String mBalance, final String mPotBalance){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Chama Notification");
        builder.setMessage(mNotification1 + mAmountSent + ". " + mNotification2 + mBalance + "."
                + mNotification3 + mPotBalance + ".");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setSecondDialog(final String mBalance){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Chama Notification");
        builder.setMessage(mNotification1 + mAmountSent + ". " + mNotification4 + mBalance + ""
                + mNotification5 + ".");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Chama Notification");
        builder.setMessage("MILKA BAIDU MISOSA has completed his pot contribution.Current Chama's Pot size is Ksh 155,200.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

}
