package com.example.weatherlens.Session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.weatherlens.loginpage;

import java.util.HashMap;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_FIRST_NAME = "fname";
    public static final String KEY_LAST_NAME = "lname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_TYPE = "type";



    // Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    /**
     * Check login method wil check user login status If false it will redirect
     * user to login page Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, loginpage.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        checkLogin();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void createLoginSession(String fname, String lname ,String mobile,String email,String type) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_FIRST_NAME, fname);
        // Storing email in pref
        editor.putString(KEY_LAST_NAME, lname);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TYPE, type);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, null));

        // user email id
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_TYPE, pref.getString(KEY_TYPE, null));


        // return user
        return user;
    }
}
