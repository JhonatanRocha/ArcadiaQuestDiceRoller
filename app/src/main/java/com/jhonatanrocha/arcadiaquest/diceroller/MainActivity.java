package com.jhonatanrocha.arcadiaquest.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText inputOfensiveDice;
    private EditText inputDefenseDice;
    private CheckBox checkBoxRerollOffensive;
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
        this.checkBoxRerollOffensive = (CheckBox) findViewById(R.id.checkBoxRerollOffensiveId);
        this.checkBoxRerollShield = (CheckBox) findViewById(R.id.checkBoxRerollShieldId);
        this.inputRerollOffensive = (EditText) findViewById(R.id.formDecimalRerollAttack);
        this.inputRerollDefensive = (EditText) findViewById(R.id.formDecimalRerollDefense);
        configOffensiveSpinner();
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
                    intent.putExtra("inputRerollOffensiveValue", inputRerollOffensive.getText().toString());
                    intent.putExtra("inputRerollDefensiveValue", inputRerollDefensive.getText().toString());

                    intent.putExtra("flagMeleeReroll",
                            offensiveSelector.getSelectedItem().toString().equals("MELEE") ? "true" : "false");
                    intent.putExtra("flagRangedReroll",
                            offensiveSelector.getSelectedItem().toString().equals("RANGED") ? "true" : "false");

                    Boolean flagDefensiveReroll = isDefensiveRerollDataValid();
                    intent.putExtra("flagDefensiveReroll", flagDefensiveReroll.toString());
                    intent.putExtra("flagOffensiveReroll", Boolean.toString(checkBoxRerollOffensive.isChecked()));


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

    protected Boolean isOffensiveRerollDataValid() {

        Boolean flagIsValid = false;

        final Integer rollAmount = Integer.parseInt(inputRerollOffensive.getText().toString());

        if(rollAmount < 100 && checkBoxRerollOffensive.isChecked()) {
            flagIsValid = true;
        } else if(!checkBoxRerollOffensive.isChecked()) {
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

    protected void configOffensiveSpinner() {
        this.offensiveSelector = (Spinner) findViewById(R.id.offensiveSelector);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.offensiveMethod)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView textView = ((TextView) v);
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setSingleLine();
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                return v;
            }
        };
//        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.offensiveMethod,
//                android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        offensiveSelector.setAdapter(dataAdapter);
        offensiveSelector.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
