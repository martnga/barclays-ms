package org.mansa.barclaysms;

import com.parse.Parse;

/**
 * Created by mansa on 1/5/16.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this);
    }
}
