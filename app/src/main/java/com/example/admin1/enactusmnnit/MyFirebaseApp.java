package com.example.admin1.enactusmnnit;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin1 on 28-07-2017.
 */
public class MyFirebaseApp extends android.app.Application {
    public void onCreate()
    {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
