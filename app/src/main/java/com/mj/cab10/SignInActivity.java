package com.mj.cab10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNewUser;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViews();

    }

    private void findViews() {

        btnSignIn = findViewById(R.id.btnSignIn);
        tvNewUser = findViewById(R.id.tvNewUser);

        tvNewUser.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvNewUser:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btnSignIn:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }
}
