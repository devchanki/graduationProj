package com.chanki.tmi;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class validateRequest extends StringRequest {
    final static private String URL = "http://106.10.50.52/userValidate.php";
    private Map<String, String> parameters;

    public validateRequest(String userId,Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);
        parameters = new HashMap<>();
        parameters.put("userId", userId);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
