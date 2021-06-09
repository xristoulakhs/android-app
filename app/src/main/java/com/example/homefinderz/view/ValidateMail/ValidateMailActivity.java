package com.example.homefinderz.view.ValidateMail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homefinderz.R;

public class ValidateMailActivity extends AppCompatActivity implements ValidateMailView{

    private Button btnConfirm;
    private Button btnSendCode;
    private Button btnCancel;
    private ValidateMailPresenter presenter;

    private String userEmail;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_mail);

        intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        presenter = new ValidateMailPresenter(this);
        presenter.generateCode();
        btnConfirm  = (Button)findViewById(R.id.btnConfirmCode);
        btnSendCode = (Button)findViewById(R.id.btnSendCode);
        btnCancel   = (Button)findViewById(R.id.btnCancelValidation);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkCode();
            }
        });
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.generateCode();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    /**
     * this method terminates the verification process
     */
    @Override
    public void validated() {
        intent.putExtra("validated", userEmail);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * This method shows the user a code which simulates the verification code that would be sent
     * to their email
     * @param code the code to be displayed
     */
    @Override
    public void sendCode(int code) {
        new AlertDialog.Builder(ValidateMailActivity.this)
                .setCancelable(true)
                .setTitle("Validation Code sent to: " + userEmail)
                .setMessage(Integer.toString(code))
                .setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void setCode(int code) {
        //stub
    }

    @Override
    public int getCode() {
        return Integer.parseInt(((EditText)findViewById(R.id.etCodeInput)).getText().toString());
    }
    @Override
    public void showErrorMessage() {
        new AlertDialog.Builder(ValidateMailActivity.this)
                .setCancelable(true)
                .setTitle("Error")
                .setMessage("Wrong code")
                .setPositiveButton(R.string.ok, null).create().show();
    }
}