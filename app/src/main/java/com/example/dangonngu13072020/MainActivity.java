package com.example.dangonngu13072020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText mEdtAccount, mEdtPass;
    Button mBtnLogin,mBtnEn,mBtnVn;
    TextView mTvInfo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("lange",MODE_PRIVATE);
        setLanguageLocal();
        setContentView(R.layout.activity_main);
        mEdtAccount = findViewById(R.id.editTextAccount);
        mEdtPass = findViewById(R.id.editTextPassWord);
        mBtnLogin = findViewById(R.id.buttonLogin);
        mTvInfo = findViewById(R.id.textViewInFo);
        mBtnEn = findViewById(R.id.buttonEn);
        mBtnVn = findViewById(R.id.buttonVn);




        mBtnVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("keylang","vi");
                editor.commit();
                finish();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mBtnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("keylang","en");
                editor.commit();
                finish();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textAccount = mEdtAccount.getText().toString();
                String textPassword = mEdtPass.getText().toString();

                String titleAccount = getResources().getString(R.string.text_account);
                String titlePassword = getResources().getString(R.string.text_password);

                mTvInfo.setText(
                        String.format(
                                "%s : %s\n%s : %s",
                                titleAccount,
                                textAccount,
                                titlePassword,
                                textPassword
                        ));
            }
        });

    }
    private void setLanguageLocal(){
        String keyLang = sharedPreferences.getString("keylang","en");
        Locale locale = new Locale(keyLang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(getResources().getConfiguration());
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
    }
}