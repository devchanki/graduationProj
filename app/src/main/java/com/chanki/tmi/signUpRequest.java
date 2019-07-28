package com.chanki.tmi;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class signUpRequest extends StringRequest {
    final static private String URL = "http://106.10.50.52/signup.php/";
    private Map<String, String> parameters;

    public signUpRequest(String userId, String userPassword, String userGender, String userName, String userMajor, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        Log.v("dd",userGender);
        parameters.put("userName", userName);
        parameters.put("userMajor", userMajor);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }
}
