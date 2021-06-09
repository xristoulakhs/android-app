package com.example.homefinderz.view.modifyAccount;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homefinderz.R;
import com.example.homefinderz.view.Account.AccountView;
import com.example.homefinderz.view.ValidateMail.ValidateMailActivity;

public class ModifyAccountActivity extends AppCompatActivity implements AccountView {

    private EditText email;
    private EditText firstName;
    private EditText lastName;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    ModifyAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            presenter.saveChangedEmail(result.getData().getStringExtra("validated"));
                        }
                    }
                });

        setContentView(R.layout.activity_modify_account);

        Intent intent = getIntent();
        presenter = new ModifyAccountPresenter(this);
        presenter.setAccountLoggedIn(intent.getIntExtra("accountID", -1));

        email     = (EditText) findViewById(R.id.modifyEmail);
        firstName = (EditText) findViewById(R.id.modifyFirstName);
        lastName  = (EditText) findViewById(R.id.modifyLastName);

        email.setHint(presenter.getUserEmail());
        firstName.setHint(presenter.getUserFirstName());
        lastName.setHint(presenter.getUserLastName());

        Button btnSave   = (Button)findViewById(R.id.btnSaveChanges);
        Button btnCancel = (Button)findViewById(R.id.btnCancelModify);
        Button btnDelete = (Button)findViewById(R.id.btmDeleteAccount);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveChanges();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteAccount();
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
        new AlertDialog.Builder(ModifyAccountActivity.this)
                .setCancelable(true)
                .setTitle("Success!")
                .setMessage("Created account successfully")
                        .setPositiveButton(R.string.ok, null).create().show();
    }

    public String getEmail() {
        return ((EditText)findViewById(R.id.modifyEmail)).getText().toString();
    }

    @Override
    public String getPass() {
        return ((EditText)findViewById(R.id.modifyPassword)).getText().toString();
    }

    @Override
    public String getPassConfirm() {
        return ((EditText)findViewById(R.id.modifyPasswordConfirm)).getText().toString();
    }

    @Override
    public String getFirstName() {
        return ((EditText)findViewById(R.id.modifyFirstName)).getText().toString();
    }

    @Override
    public String getLastName() {
        return ((EditText)findViewById(R.id.modifyLastName)).getText().toString();
    }

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

    /**
     * This method is used to check the validity of the user's email address
     * @param email the email to be checked
     */
    @Override
    public void validateMail(String email) {
        Intent intent = new Intent(this, ValidateMailActivity.class);
        intent.putExtra("userEmail", email);
        activityResultLauncher.launch(intent);
    }

    /**
     * Method the presenter can use to end the view
     */
    @Override
    public void endView() {
        finish();
    }

}