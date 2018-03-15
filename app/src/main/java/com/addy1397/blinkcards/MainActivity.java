package com.addy1397.blinkcards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.spec.ECField;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {

    //private WebView webView;

    private Button button_go;
    private Button button_forgotPassword;
    private CircleButton button_signUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private ImageView imageView_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*webView = (WebView) findViewById(R.id.formula_page);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        try{
            webView.loadUrl("file:///android_asset/mathscribe/COPY-ME.html");
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/

        button_go = (Button) findViewById(R.id.button_GO);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGO(v);
            }
        });

        button_forgotPassword = (Button) findViewById(R.id.button_forgotPassword);

        button_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_forgot(v);
            }
        });

        button_signUp = (CircleButton) findViewById(R.id.circleButton_SignUp);

        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSignUp(v);
            }
        });

        editTextEmail = (EditText) findViewById(R.id.editText_Email);

        editTextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailEnter(v);
            }
        });

        editTextPassword = (EditText) findViewById(R.id.editText_password);

        editTextPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordEnter(v);
            }
        });

        editTextUsername = (EditText) findViewById(R.id.editText_Username);

        editTextUsername.setVisibility(View.GONE);

        imageView_username = (ImageView) findViewById(R.id.imageView_username);

        imageView_username.setVisibility(View.GONE);

    }

    @SuppressLint("ResourceAsColor")
    private void PasswordEnter(View v) {

    }

    @SuppressLint("ResourceAsColor")
    private void EmailEnter(View v) {

    }

    @SuppressLint("ResourceAsColor")
    private void buttonSignUp(View v) {
        Toast.makeText(this, "To be Completed", Toast.LENGTH_SHORT).show();

        editTextUsername.setVisibility(View.VISIBLE);
        imageView_username.setVisibility(View.VISIBLE);
        button_go.setText("NEXT");


    }

    @SuppressLint("ResourceAsColor")
    private void button_forgot(View v) {
        Toast.makeText(this, "To be completed", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("ResourceAsColor")
    private void buttonGO(View v) {

        button_go.setBackgroundColor(R.color.colorDarkBlue);
        button_go.setText("DONE");

        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        if(Email.matches("") || Password.matches(""))
        {
            Toast.makeText(this, "Complete The Form", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        if(Email.matches("addy@addy.1397") && Password.matches("addy1397"))
        {
            Toast.makeText(this, "SignIn Complete", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

}
