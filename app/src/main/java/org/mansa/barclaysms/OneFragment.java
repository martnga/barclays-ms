package org.mansa.barclaysms;

/**
 * Created by mansa on 1/6/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class OneFragment extends Fragment{

    CardView mOneCardView;
    CardView mTwoCardView;
    TextView mUpTextOne;
    TextView mUpTextTwo;
    TextView mBottomTextOne;
    TextView mBottomTextTwo;
    EditText mDepositAmountEditTxt;
    Button mSendButton;
    String mAmountSent;
    int mPotSize = 10000;
    String mNotification1 = "You have deposited Ksh ";
    String mNotification2 = "Your remaining Balance is Ksh ";
    String mNotification3 = "Current Pendo Chama Pot Amount is Ksh ";
    int chamaPot = mPotSize * 12 - 4567;
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
        mTwoCardView = (CardView) layout.findViewById(R.id.card_two);
        mUpTextOne = (TextView) layout.findViewById(R.id.up_txt_one);
        mUpTextTwo = (TextView) layout.findViewById(R.id.up_txt_two);
        mBottomTextOne = (TextView) layout.findViewById(R.id.down_txt_one);
        mBottomTextTwo = (TextView) layout.findViewById(R.id.down_txt_two);


        mDepositAmountEditTxt = (EditText) layout.findViewById(R.id.deposit_amount_txt);
        mSendButton = (Button) layout.findViewById(R.id.btnSend);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAmountSent = mDepositAmountEditTxt.getText().toString().trim();
                if(!mAmountSent.isEmpty()){
                    String mBalance = String.format("%,d",  mPotSize - Integer.parseInt(mAmountSent));
                    String mPotBalance = String.format("%,d",  chamaPot + Integer.parseInt(mAmountSent));
                    if(status == 0){
                        mUpTextOne.setText(mNotification1 + mAmountSent + ". " + mNotification2 + mBalance + "."
                                + mNotification3 + mPotBalance + ".");
                        mBottomTextOne.setText(setTime());
                        mDepositAmountEditTxt.setText("");
                        mOneCardView.setVisibility(View.VISIBLE);
                        status ++;
                    }else if(status == 1){
                        mUpTextTwo.setText(mNotification1 + mAmountSent + ". " + mNotification2 + mBalance + "."
                                + mNotification3 + mPotBalance + ".");
                        mBottomTextTwo.setText(setTime());
                        mDepositAmountEditTxt.setText("");
                        mTwoCardView.setVisibility(View.VISIBLE);
                        status += 1;
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
