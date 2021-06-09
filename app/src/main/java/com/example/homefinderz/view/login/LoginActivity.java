package com.example.homefinderz.view.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homefinderz.R;
import com.example.homefinderz.view.dao.MemoryInitializer;
import com.example.homefinderz.view.search.SearchActivity;
import com.example.homefinderz.view.signUp.SignUpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static boolean initialized = false;

    EditText etUserEmail, etPassword;
    String userEmail, password;
    Button btLogin, btSignup;

    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserEmail = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);
        btSignup = (Button) findViewById(R.id.bt_sign_up);

        presenter = new LoginPresenter(this);

        if (!initialized) {
            (new MemoryInitializer()).prepareData();
            initialized = true;
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.login() == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                } else {
                    startSearch();
                }
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This method navigates a successfully logged in user, to the app's Search Page
     */
    private void startSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("accountID", presenter.getAccountID());
        startActivity(intent);
    }

    @Override
    public String getEmail() {
        userEmail = etUserEmail.getText().toString();
        return userEmail;
    }

    @Override
    public String getPassword() {
        password = etPassword.getText().toString();
        return password;
    }

    @Override
    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    @Override
    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public void showSuccessMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_check);
        builder.setTitle("Login Successfully");
        builder.setMessage("Welcome to HomeFinderz");
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }
}
