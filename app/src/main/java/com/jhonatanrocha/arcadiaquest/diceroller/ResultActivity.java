package com.jhonatanrocha.arcadiaquest.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends Activity {

    private TextView textMeleeTotalResult;
    private TextView textRangedTotalResult;
    private TextView textCriticalTotalResult;
    private TextView textDefensiveTotalResult;
    private TextView textDefensiveCritTotalResult;
    private Integer inputOfensiveValue;
    private Integer inputDefensiveValue;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);

        textMeleeTotalResult  = (TextView) findViewById(R.id.textResultMeleeId);
        textRangedTotalResult = (TextView) findViewById(R.id.textResultRangedId);
        textCriticalTotalResult = (TextView) findViewById(R.id.textResultCritId);
        textDefensiveTotalResult  = (TextView) findViewById(R.id.textResultDefenseId);
        textDefensiveCritTotalResult = (TextView) findViewById(R.id.textResultCritDefenseId);
        backButton = (ImageView) findViewById(R.id.imgBackButtonId);

        initResult();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void initResult() {
        final Bundle extras = getIntent().getExtras();
        inputOfensiveValue = Integer.parseInt(extras.getString("inputOfensiveValue"));
        inputDefensiveValue = Integer.parseInt(extras.getString("inputDefensiveValue"));
        calculateOfensive();
        calculateDefensive();
    }

    protected void calculateOfensive() {

        final Random generator = new Random();
        Integer ofensiveResult  = 0;
        Integer meleeTotal = 0;
        Integer rangedTotal = 0;
        Integer criticalTotal = 0;

        for(int i = 0; i < inputOfensiveValue; i++) {
            ofensiveResult  = generator.nextInt(6) + 1;
            Log.d("RESULTADO DO DADO", ofensiveResult.toString());
            if(DiceEnum.CRITICAL.equals(DiceEnum.getDice("Ofensive", ofensiveResult))) {
                inputOfensiveValue++;
                criticalTotal++;
                Log.d("Debugando 1", inputOfensiveValue.toString());
                Log.d("Debugando 2", criticalTotal.toString());
            } else if(DiceEnum.MELEE.equals(DiceEnum.getDice("Ofensive", ofensiveResult))) {
                meleeTotal++;
                Log.d("Debugando 3", meleeTotal.toString());
            } else {
                rangedTotal++;
                Log.d("Debugando 4", rangedTotal.toString());
            }
        }
        setTextViewOfensiveResult(meleeTotal, rangedTotal, criticalTotal);
    }

    protected void calculateDefensive() {

        final Random generator = new Random();
        Integer defensiveResult = 0;
        Integer blockTotal = 0;
        Integer blockCriticalTotal = 0;

        for(int i = 0; i < inputDefensiveValue; i++) {
            defensiveResult = generator.nextInt(6) + 1;
            if(DiceEnum.CRITICAL_DEFENSIVE.equals(DiceEnum.getDice("Defensive", defensiveResult))) {
                inputDefensiveValue++;
                blockCriticalTotal++;
            } else if(DiceEnum.DEFENSIVE.equals(DiceEnum.getDice("Defensive", defensiveResult))){
                blockTotal++;
            }
        }
        setTextViewDefensiveResult(blockTotal, blockCriticalTotal);
    }

    protected void setTextViewOfensiveResult(final Integer meleeTotal, final Integer rangedTotal, final Integer criticalTotal) {
        textMeleeTotalResult.setText(meleeTotal.toString());
        textRangedTotalResult.setText(rangedTotal.toString());
        textCriticalTotalResult.setText(criticalTotal.toString());
    }

    protected void setTextViewDefensiveResult(final Integer blockTotal, final Integer blockCriticalTotal) {
        textDefensiveTotalResult.setText(blockTotal.toString());
        textDefensiveCritTotalResult.setText(blockCriticalTotal.toString());
    }
}

