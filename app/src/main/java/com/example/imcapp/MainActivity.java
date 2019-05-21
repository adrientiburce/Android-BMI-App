package com.example.imcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText edt_poids, edt_taille;

    //private Editable editable;
    private Button mButton, mScanBtn;
    private RadioButton radionBtnMan, radionBtnWoman;
    Boolean hasPoids = false;
    Boolean hasTaille = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // wire widgets
        edt_poids = findViewById(R.id.edt_poids);
        edt_taille = findViewById(R.id.edt_taille);
        mButton = findViewById(R.id.btn_submit);
        mScanBtn = findViewById(R.id.btn_scan);
        radionBtnMan = findViewById(R.id.radio_btn_man);
        radionBtnWoman = findViewById(R.id.radio_btn_woman);

        mButton.setEnabled(false);

        edt_poids.addTextChangedListener(this);
        edt_taille.addTextChangedListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent secondActivity = new Intent(MainActivity.this, ResultActivity.class);


                Float resultImc;
                Float poids = Float.valueOf(edt_poids.getText().toString());
                Float taille = Float.valueOf(edt_taille.getText().toString()) / 100;  // en metre dans la formule
                resultImc = poids / (taille * taille);

                String resultSex = "Vous"; // 1 : men, 2 woman
                if (radionBtnMan.isChecked()) {
                    resultSex = "Homme";
                }
                if (radionBtnWoman.isChecked()) {
                    resultSex = "Femme";
                }
                secondActivity.putExtra("keyIMC", String.valueOf(resultImc));
                secondActivity.putExtra("sex", resultSex);
                startActivity(secondActivity);


            }
        });

        mScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void afterTextChanged(Editable editable) {
        if (editable == edt_poids.getEditableText()) {
            if(editable.toString().length() != 0) hasPoids = true;
            Log.i("CAT", " " + hasPoids);

        } else if (editable == edt_taille.getEditableText()) {
            if(editable.toString().length() != 0 && Integer.valueOf(editable.toString()) > 0) hasTaille = true;

        }
        if(hasPoids && hasTaille){
            mButton.setEnabled(true);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}
