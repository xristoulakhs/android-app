package com.example.homefinderz.view.signUp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homefinderz.R;
import com.example.homefinderz.view.Account.AccountView;
import com.example.homefinderz.view.ValidateMail.ValidateMailActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements AccountView {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private SignUpPresenter presenter;
    private String email;
    private String password;
    private String passConfirm;
    private String firstname;
    private String lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            presenter.createAccount(result.getData().getStringExtra("validated"));
                        }
                    }
                });

        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);
        Button btnSignUp = (Button)findViewById(R.id.btnSignUp);
        Button btnCancel = (Button)findViewById(R.id.btnCancelSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignUp();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(),"Error! " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getApplicationContext(),"Account Created!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public String getEmail() {
        email = ((EditText)findViewById(R.id.signUpEmail)).getText().toString();
        return email;
    }

    @Override
    public String getPass() {
        password = ((EditText)findViewById(R.id.signUpPassword)).getText().toString();
        return password;
    }

    @Override
    public String getPassConfirm() {
        passConfirm = ((EditText)findViewById(R.id.signUpPasswordConfirm)).getText().toString();
        return passConfirm;
    }

    @Override
    public String getFirstName() {
        firstname = ((EditText)findViewById(R.id.signUpName)).getText().toString();
        return firstname;
    }

    @Override
    public String getLastName() {
        lastname = ((EditText)findViewById(R.id.signUpLastName)).getText().toString();
        return lastname;
    }

    //========METHODS FOR TEST PURPOSES=======================
    @Override
    public void setEmail(String email) {
        //stub
    }

    @Override
    public void setPass(String pass) {
        //stub
    }

    @Override
    public void setPassConfirm(String pass) {
        //stub
    }

    @Override
    public void setFirstName(String name) {
        //stub
    }

    @Override
    public void setLastName(String name) {
        //stub
    }

    //====================================================

    /**
     * This checks the validity of a new user's email address
     * @param email the email to be checked
     */
    @Override
    public void validateMail(String email) {
        Intent intent = new Intent(this, ValidateMailActivity.class);
        intent.putExtra("userEmail", email);
        activityResultLauncher.launch(intent);
    }

    /**
     * method which terminates the activity.
     */
    @Override
    public void endView() {
        finish();
    }

}