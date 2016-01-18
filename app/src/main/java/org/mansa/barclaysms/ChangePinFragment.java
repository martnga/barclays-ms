package org.mansa.barclaysms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Admin on 04-06-2015.
 */
public class ChangePinFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.change_pin_fragment,container,false);
        final EditText mNewPinTxt = (EditText) v.findViewById(R.id.new_pin_edittxt);
        final EditText mConfirmPinTxt = (EditText) v.findViewById(R.id.confirm_pin_edit_text);
        final EditText mCurrentPinTxt = (EditText) v.findViewById(R.id.current_pin_edittext);
        Button mChangePinButton = (Button) v.findViewById(R.id.confirm_pin_button);


        mChangePinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPIN = mNewPinTxt.getText().toString();
                String ConfirmPIN = mConfirmPinTxt.getText().toString();
                if (ifEqual(newPIN, ConfirmPIN) && newPIN.length() == 4) {
                    mNewPinTxt.setText("");
                    mConfirmPinTxt.setText("");
                    mCurrentPinTxt.setText("");
                    SetDialog("Pin Changed");
                }
                else {
                    SetDialog("PIN does not match");
                }
            }
        });

        return v;

    }

    public boolean ifEqual(String newPin, String confirmPin){

        boolean b = false;
        if( newPin.equals(confirmPin)){
            b = true;
        }
        return b;
    }

    public void SetDialog(String message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getActivity(), Home.class));
                //to close the dialog
                dialogInterface.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


}
