package org.mansa.barclaysms;

/**
 * Created by mansa on 1/6/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class OneFragment extends Fragment{

    CardView mOneCardView;
    CardView mTwoCardView;
    CardView mThreeCardView;
    CardView mFourCardView;
    CardView mFiveCardView;
    TextView mUpTextOne;
    TextView mUpTextTwo;
    TextView mUpTextThree;
    TextView mUpTextFive;
    TextView mUpTextFour;
    TextView mBottomTextOne;
    TextView mBottomTextTwo;
    TextView mBottomTextThree;
    TextView mBottomTextFour;
    TextView mBottomTextFive;
    EditText mDepositAmountEditTxt;
    Button mSendButton;

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
        mThreeCardView = (CardView) layout.findViewById(R.id.card_three);
        mFourCardView = (CardView) layout.findViewById(R.id.card_four);
        mFiveCardView = (CardView) layout.findViewById(R.id.card_five);
        return layout;
    }

    public String setTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String strDate = sdf.format(c.getTime());
        return  strDate;
    }

}
