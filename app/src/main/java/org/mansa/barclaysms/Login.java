package org.mansa.barclaysms;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mansa on 1/4/16.
 */
public class Login extends AppCompatActivity {

    EditText mAccountNumber;
    EditText mPin;
    Button mLoginBtn;
    public static final String PREFS_NAME = "CREDENTIALS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if(Application.getInstance().isCurrentUser()) {

            startActivity(new Intent(Login.this, Home.class));
            finish();
        }

        mAccountNumber = (EditText) findViewById(R.id.account_number_editText);
        mPin = (EditText) findViewById(R.id.pin_editText);
        mLoginBtn = (Button) findViewById(R.id.login_button);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(!ifPinEmpty() && !ifAccountEmpty()){
                     String account = mAccountNumber.getText().toString().trim();
                     saveCredentials(account);
                     startActivity(new Intent(Login.this, Home.class));
                 }
            }
        });



    }

    public boolean ifPinEmpty() {
        String pin = mPin.getText().toString().trim();
        boolean b;
        if (pin.isEmpty()) {
            b = true;
        }else {
            b = false;
        }
        return b;
    }


    public boolean ifAccountEmpty() {
        String account = mAccountNumber.getText().toString().trim();
        boolean b;
        if (account.isEmpty()) {
            b = true;
        }else {
            b = false;
        }
        return b;
    }

    public void saveCredentials(String string){

        //saving credentials in shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCOUNT", string);
        editor.commit();
    }

}
