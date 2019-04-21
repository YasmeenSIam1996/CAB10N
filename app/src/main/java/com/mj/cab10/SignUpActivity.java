package com.mj.cab10;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp;
    EditText txtSignUp1, txtSignUp2, txtSignUp3, txtSignUp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();


    }

    private void findViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }


    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {


            if (source.equals(" ")) {
                int startSelection = txtSignUp1.getSelectionStart();
                int endSelection = txtSignUp1.getSelectionEnd();
                txtSignUp1.setText(txtSignUp1.getText().toString().trim());
                txtSignUp1.setSelection(startSelection, endSelection);

            }

            return null;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                showSignUpDialog();
                break;
        }
    }

    private void showSignUpDialog() {
        Dialog signUpDialog = new Dialog(this);
        signUpDialog.setContentView(R.layout.dialog_complete_signup);
        signUpDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        txtSignUp1 = signUpDialog.findViewById(R.id.txtSignUp1);
        txtSignUp2 = signUpDialog.findViewById(R.id.txtSignUp2);
        txtSignUp3 = signUpDialog.findViewById(R.id.txtSignUp3);
        txtSignUp4 = signUpDialog.findViewById(R.id.txtSignUp4);

        txtSignUp1.setFilters(new InputFilter[]{filter});


        signUpDialog.show();
    }
}
