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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText inputOfensiveDice;
    private EditText inputDefenseDice;
    private CheckBox checkBoxRerollMelee;
    private CheckBox checkBoxRerollRanged;
    private CheckBox checkBoxRerollShield;
    private EditText inputRerollOffensive;
    private EditText inputRerollDefensive;
    private Spinner offensiveSelector;
    private ImageButton buttonFight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loadComponents();
        checkboxesOfensiveOnClickListener();
        fight();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void loadComponents() {
        this.inputOfensiveDice = (EditText) findViewById(R.id.formDecimalAttack);
        this.inputDefenseDice = (EditText) findViewById(R.id.formDecimalDefense);
        this.buttonFight = (ImageButton) findViewById(R.id.imageButtonThrowDiceId);
        this.checkBoxRerollMelee = (CheckBox) findViewById(R.id.checkBoxRerollMeleeId);
        this.checkBoxRerollRanged = (CheckBox) findViewById(R.id.checkBoxRerollRangedId);
        this.checkBoxRerollShield = (CheckBox) findViewById(R.id.checkBoxRerollShieldId);
        this.inputRerollOffensive = (EditText) findViewById(R.id.formDecimalRerollAttack);
        this.inputRerollDefensive = (EditText) findViewById(R.id.formDecimalRerollDefense);
        this.offensiveSelector = (Spinner) findViewById(R.id.offensiveSelector);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.offensiveMethod,
                android.R.layout.simple_spinner_item);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        offensiveSelector.setAdapter(dataAdapter);
        offensiveSelector.setOnItemSelectedListener(this);
    }

    protected void fight(){
        buttonFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataEmpty() && isDataValid() &&
                        isOffensiveRerollDataValid() && isDefensiveRerollDataValid()) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("inputOfensiveValue", inputOfensiveDice.getText().toString());
                    intent.putExtra("inputDefensiveValue", inputDefenseDice.getText().toString());

                    Boolean flagOffensiveReroll = isOffensiveRerollDataValid();
                    intent.putExtra("flagOffensiveReroll", flagOffensiveReroll.toString());

                    Boolean flagDefensiveReroll = isDefensiveRerollDataValid();
                    intent.putExtra("flagDefensiveReroll", flagDefensiveReroll.toString());

                    intent.putExtra("flagMeleeReroll", Boolean.toString(checkBoxRerollMelee.isChecked()));
                    intent.putExtra("flagRangedReroll", Boolean.toString(checkBoxRerollRanged.isChecked()));
                    intent.putExtra("inputRerollOffensiveValue", inputRerollOffensive.getText().toString());
                    intent.putExtra("inputRerollDefensiveValue", inputRerollDefensive.getText().toString());

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Type a number from 0 to 99 on each input!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected Boolean isDataEmpty() {
        Boolean flagIsValid = false;

        if(!inputOfensiveDice.getText().toString().isEmpty() &&
                !inputDefenseDice.getText().toString().isEmpty()) {
            flagIsValid = true;
        }
        return flagIsValid;
    }

    protected Boolean isDataValid() {
        Boolean flagIsValid = false;

        Integer qtdOffensiveDice = Integer.parseInt(inputOfensiveDice.getText().toString());
        Integer qtdDefensiveDice = Integer.parseInt(inputDefenseDice.getText().toString());

        if(qtdOffensiveDice < 100 && qtdDefensiveDice < 100) {
            flagIsValid = true;
        }
        return flagIsValid;
    }

    protected void checkboxesOfensiveOnClickListener() {
        checkBoxRerollMelee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxRerollRanged.isChecked()) {
                    checkBoxRerollRanged.setChecked(false);
                }
            }
        });

        checkBoxRerollRanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxRerollMelee.isChecked()) {
                    checkBoxRerollMelee.setChecked(false);
                }
            }
        });
    }

    protected Boolean isOffensiveRerollDataValid() {

        Boolean flagIsValid = false;

        final Integer rollAmount = Integer.parseInt(inputRerollOffensive.getText().toString());

        if(rollAmount < 100 && (checkBoxRerollMelee.isChecked() || checkBoxRerollRanged.isChecked())) {
            flagIsValid = true;
        } else if(!checkBoxRerollMelee.isChecked() && !checkBoxRerollRanged.isChecked()) {
            flagIsValid = true;
        }

        return flagIsValid;
    }

    protected Boolean isDefensiveRerollDataValid() {

        Boolean flagIsValid = false;

        final Integer rollAmount = Integer.parseInt(inputRerollDefensive.getText().toString());

        if(rollAmount < 100 && checkBoxRerollShield.isChecked()) {
            flagIsValid = true;
        }

        return flagIsValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
