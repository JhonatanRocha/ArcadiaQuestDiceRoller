package com.jhonatanrocha.arcadiaquest.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView meleeDiceImage;
    private ImageView shieldDiceImage;
    private EditText inputOfensiveDice;
    private EditText inputDefenseDice;
    private ImageButton buttonFight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loadComponents();
        fight();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void loadComponents() {
        this.meleeDiceImage = (ImageView) findViewById(R.id.imgMeleeLabel);
        this.inputOfensiveDice = (EditText) findViewById(R.id.formDecimalAttack);
        this.shieldDiceImage = (ImageView) findViewById(R.id.imgDefenseLabel);
        this.inputDefenseDice = (EditText) findViewById(R.id.formDecimalDefense);
        this.buttonFight = (ImageButton) findViewById(R.id.imageButtonThrowDiceId);
    }

    protected void fight(){
        buttonFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataValid()) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("inputOfensiveValue", inputOfensiveDice.getText().toString());
                    intent.putExtra("inputDefensiveValue", inputDefenseDice.getText().toString());

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Type a number from 0 to 99 on each input!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected Boolean isDataValid() {
        Boolean flagIsValid = false;

        if(!inputOfensiveDice.getText().toString().isEmpty() &&
                !inputDefenseDice.getText().toString().isEmpty()) {
            flagIsValid = true;
        }
        return flagIsValid;
    }
}
