package com.chanki.tmi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class uploadBoardRequest extends StringRequest {
    final static private String URL = "http://106.10.50.52/uploadBoard.php";
    private Map<String, String> parameters;
    public uploadBoardRequest(String userID, String name, String major,  String topic, String contents, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userId", userID);
        parameters.put("name", name);
        parameters.put("major", major);
        parameters.put("topic", topic);
        parameters.put("contents", contents);
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
