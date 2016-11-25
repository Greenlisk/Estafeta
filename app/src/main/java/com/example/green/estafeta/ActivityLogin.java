package com.example.green.estafeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.green.estafeta.classes.LoginView;
import com.example.green.estafeta.classes.Presenter;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, LoginView{
    private final static String LOG_TAG = "ActivityLogin";

    private Button button;
    private ProgressDialog progressDialog;
    private TextView errorField;
    private Presenter presenter;
    private EditText usernameField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = (Presenter)getApplicationContext();
        presenter.setView(this);
        usernameField = (EditText)findViewById(R.id.username_field);
        passwordField = (EditText)findViewById(R.id.password_field);
        button = (Button)findViewById(R.id.button_login);
        button.setOnClickListener(this);
        errorField = (TextView)findViewById(R.id.error_field);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }


    @Override
    public void onClick(View view) {
        if(usernameField.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty()){
            return;
        }
        presenter.authenticate(usernameField.getText().toString(), passwordField.getText().toString());
    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void setError() {
        errorField.setVisibility(View.VISIBLE);
    }

    @Override
    public void stepNext() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
