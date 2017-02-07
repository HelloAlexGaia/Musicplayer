package com.example.android.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginInActivity extends AppCompatActivity {
    private EditText etAccount;
    private EditText etPassword;
    private ImageButton mIbLogIn;
    private String passString;
    private String account;
    private String password;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        initial();
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                account =charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIbLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAccout(account,password);
            }
        });
    }

    private void initial(){
        etAccount= (EditText) findViewById(R.id.et_account);
        etPassword= (EditText) findViewById(R.id.et_passage);
        mIbLogIn= (ImageButton) findViewById(R.id.ib_log_in);
        passString=getString(R.string.udacity);
    }
    private void checkAccout(String account,String password){
        String passInfo=null;
        if (account!=null&&password!=null){
            if (account.equals(passString)){
                if (password.equals(passString)){
                    startActivity(MusicListActivity.getIntent(this));
                    return;
                }else {
                    passInfo=getString(R.string.wrong_password_info);
                }
            }else {
                passInfo=getString(R.string.wrong_account);
            }
        }else {
            passInfo=getString(R.string.null_account);
        }
        showInfo(passInfo);
    }

    private void showInfo(String passInfo){
        if (mToast==null){
            mToast=Toast.makeText(this,passInfo,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(passInfo);
        }
        mToast.show();
    }
}
