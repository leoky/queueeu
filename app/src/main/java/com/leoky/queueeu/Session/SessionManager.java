package com.leoky.queueeu.Session;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import static com.leoky.queueeu.Activity.QueueDetailActivity.KEY_NAME;

public class SessionManager {

    private static final String PREF_NAME = "UserData";
    private static final String KEY_ID = "userID";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PASS = "userPass";
    private static final String KEY_PHONE = "userPhone";
    private static final String KEY_GENDER = "userGender";
    private static final String KEY_ISLOGIN = "userIsLogin";


    public Context context;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //miss gender
    public void createUserSession(String id, String email, String name,String phone, String gender, String password){
        editor.putString(KEY_ID,id);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASS,password);
        editor.putString(KEY_PHONE,phone);
        editor.putString(KEY_GENDER,gender);
        editor.putBoolean(KEY_ISLOGIN,true);
        editor.commit();
    }
    public void saveSpId(String id){
        editor.putString(KEY_ID,id);
        editor.commit();
    }

    public String getSpId() {
        return pref.getString(KEY_ID, "");
    }

    public void saveSpPhone(String phone){
        editor.putString(KEY_PHONE,phone);
        editor.commit();
    }

    public String getSpPhone() {
        return pref.getString(KEY_PHONE, "");
    }

    public void saveSpName(String name){
        editor.putString(KEY_NAME,name);
        editor.commit();
    }

    public String getSpName() {
        return pref.getString(KEY_NAME, "");
    }

    public void saveSpEmail(String email){
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }

    public String getSpEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public void saveSpGender(String email){
        editor.putString(KEY_GENDER,email);
        editor.commit();
    }

    public String getSpGender() {
        return pref.getString(KEY_GENDER, "");
    }

    public void saveSpPassword(String password) {
        editor.putString(KEY_PASS, password);
        editor.commit();
    }

    public void  saveSpIsLogin(boolean b){
        editor.putBoolean(KEY_ISLOGIN, b);
        editor.commit();
    }

    public boolean isLogin () {
        return pref.getBoolean(KEY_ISLOGIN, false);
    }

    public String getSpPassword() {
        return pref.getString(KEY_PASS, "");
    }

    public void clearSp(){
        pref.edit().clear().commit();
    }
}
