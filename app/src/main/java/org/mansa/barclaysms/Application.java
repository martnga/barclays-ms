package org.mansa.barclaysms;

import android.content.SharedPreferences;

import com.parse.Parse;

/**
 * Created by mansa on 1/5/16.
 */
public class Application extends android.app.Application {

    public static String ACCOUNTNUMBER ="";
    public static final String PREFS_NAME = "CREDENTIALS";
    public static final String TAG = Application.class.getSimpleName();

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Parse.enableLocalDatastore(this);

        Parse.initialize(this);
    }

    public static synchronized Application getInstance() {
        return mInstance;
    }



    public boolean isCurrentUser(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        final String authToken = sharedPreferences.getString("ACCOUNT", "");
        ACCOUNTNUMBER += authToken;

        if(ACCOUNTNUMBER == null || ACCOUNTNUMBER.isEmpty() || ACCOUNTNUMBER.length() < 1){
            return false;
        }

        return true;
    }

}
