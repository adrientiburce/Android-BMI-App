package com.example.imcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements TextWatcher, Serializable {

    private EditText edt_poids, edt_taille;
    private TextView txt_welcome;

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
        txt_welcome = findViewById(R.id.txt_welcome);
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


                // get resultImc & resultSex => send as Extra
                Float floatResultImc;
                Float poids = Float.valueOf(edt_poids.getText().toString());
                Float taille = Float.valueOf(edt_taille.getText().toString()) / 100;
                floatResultImc = poids / (taille * taille);

                String resultSex = "Vous"; // 1 : men, 2 woman
                if (radionBtnMan.isChecked()) {
                    resultSex = "Homme";
                }
                if (radionBtnWoman.isChecked()) {
                    resultSex = "Femme";
                }

                int resultImc = Math.round(floatResultImc);

                secondActivity.putExtra("keyIMC", String.valueOf(resultImc));
                secondActivity.putExtra("sex", resultSex);

                //add lastImc to Preferences
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();

                editor.putString("lastImc", String.valueOf(resultImc));
                editor.apply();

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
    protected void onStart() {
        super.onStart();

        SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        String lastImc = settings.getString("lastImc", "");
        txt_welcome.setText("Votre dernier IMC était : " + lastImc);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

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

}
