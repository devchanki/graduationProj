package com.chanki.tmi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    private ArrayAdapter adapter;
    private String userId;
    private String userPassword;
    private String userGender;
    private String userName;
    private String userMajor;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText idText = (EditText) findViewById(R.id.email);
        final EditText passwordText = (EditText) findViewById(R.id.password);
        final EditText nameText = (EditText) findViewById(R.id.userName);
        final EditText majorText = (EditText) findViewById(R.id.userMajor);

        RadioGroup gender = (RadioGroup) findViewById(R.id.userGender);
        int genderId = gender.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genderId)).getText().toString();

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton genderButton = (RadioButton) findViewById(checkedId);
                userGender = genderButton.getText().toString();
            }
        });
        final Button exit = (Button) findViewById(R.id.signUpExit);
        exit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String userIdCheck = idText.getText().toString();
                if(validate){
                    Log.v("plusma","ss");
                    return;
                }

                if(userIdCheck.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    dialog = builder.setMessage("아이디가 입력되지 않았습니다. ")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                dialog = builder.setMessage("아이디가 사용가능합니다. ")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                dialog = builder.setMessage("아이디를 사용할 수 없습니다. ")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Log.v("asdfa","asdfasdfs");
                        }

                    }
                };
                validateRequest validateRequest = new validateRequest(userIdCheck, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(validateRequest);
            }
        });

        Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userId = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                String userMajor = majorText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    dialog = builder.setMessage("중복체크 부탁드립니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(userId.equals("") || userPassword.equals("")|| userName.equals("")|| userMajor.equals("") || userGender.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    dialog = builder.setMessage("채워지지 않은 부분이 있습니다. ")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                dialog = builder.setMessage("회원 등록에 성공했습니다. ")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                dialog = builder.setMessage("회원 가입에 실패하였습니다. ")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                };
                signUpRequest signUpRequest = new signUpRequest(userId,userPassword,userGender,userName,userMajor, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(signUpRequest);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
