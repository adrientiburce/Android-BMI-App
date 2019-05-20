package com.example.imcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt_result, txt_analyse;

    private EditText edt_poids, edt_taille;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // wire widgets
        txt_result = findViewById(R.id.txt_result);
        txt_analyse = findViewById(R.id.txt_analyse);
        edt_poids = findViewById(R.id.edt_poids);
        edt_taille = findViewById(R.id.edt_taille);
        mButton = findViewById(R.id.btn_submit);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Float resultImc;

                Float poids = Float.valueOf(edt_poids.getText().toString());
                Float taille = Float.valueOf(edt_taille.getText().toString()) / 100;  // en metre dans la formule
                resultImc = poids / (taille * taille);

                txt_result.setText("Votre IMC est de : " + resultImc);

                if (resultImc < 16.5) {
                    txt_analyse.setText("Anorexie");
                } else if (resultImc < 20) {
                    txt_analyse.setText("Anorexie");
                } else if (resultImc < 30) {
                    txt_analyse.setText("Poids normal");
                } else {
                    txt_analyse.setText("Surpoids");
                }
            }
        });
    }
}
