package com.example.imcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textResultFromMain;
    private TextView txt_result, txt_analyse;
    private ImageView image_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // wire widgets
        txt_result = findViewById(R.id.txt_result);
        txt_analyse = findViewById(R.id.txt_analyse);
        image_result = findViewById(R.id.image_result);

        // get Extras

        Float resultImc = Float.valueOf(this.getIntent().getStringExtra("keyIMC"));
        String resultSex = this.getIntent().getStringExtra("sex");

        txt_result.setText(resultSex + " avec un IMC de : " + resultImc);


        txt_analyse.setText("Vous êtes en : ");

        if (resultImc < 16.5) {
            txt_analyse.append("sous-alimentation");
            image_result.setImageDrawable(getResources().getDrawable(R.drawable.ic_smyley_very_sad));
        } else if (resultImc < 20) {
            txt_analyse.append("faible sous-poids");
            image_result.setImageDrawable(getResources().getDrawable(R.mipmap.ic_smyley_sad));
        } else if (resultImc < 30) {
            txt_analyse.append("bonne santé !");
            image_result.setImageDrawable(getResources().getDrawable(R.mipmap.ic_smyley_happy));

        } else {
            txt_analyse.append("surpoids");
            image_result.setImageDrawable(getResources().getDrawable(R.mipmap.ic_smyley_big));

        }
    }
}
