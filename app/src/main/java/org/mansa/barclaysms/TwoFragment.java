package org.mansa.barclaysms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mansa on 1/6/16.
 */
public class TwoFragment extends Fragment {

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

    public TwoFragment() {
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
        View layout = inflater.inflate(R.layout.fragment_two, container, false);
        mOneCardView = (CardView) layout.findViewById(R.id.card_one);
        mTwoCardView = (CardView) layout.findViewById(R.id.card_two);
        mThreeCardView = (CardView) layout.findViewById(R.id.card_three);
        mFourCardView = (CardView) layout.findViewById(R.id.card_four);
        mFiveCardView = (CardView) layout.findViewById(R.id.card_five);
        return layout;
    }
}
