package com.jhonatanrocha.arcadiaquest.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>Class that represents the Main Activity of the App.</p>
 *
 * @author Jhonatan Rocha
 * @version 1.0
 * @since 2018-08-21
 */
public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText inputOffensiveDice;
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

    /**
     *<p>Overriding the Method from Super Class,
     *<br>to Keep the Screen Orientation to just Portrait.
     * @param newConfig ({@link Configuration})
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     *<p>Load all the Components from the Main Activity.</p>
     */
    protected void loadComponents() {
        this.inputOffensiveDice = (EditText) findViewById(R.id.formDecimalAttack);
        this.inputDefenseDice = (EditText) findViewById(R.id.formDecimalDefense);
        this.buttonFight = (ImageButton) findViewById(R.id.imageButtonThrowDiceId);
        this.checkBoxRerollOffensive = (CheckBox) findViewById(R.id.checkBoxRerollOffensiveId);
        this.checkBoxRerollShield = (CheckBox) findViewById(R.id.checkBoxRerollShieldId);
        this.inputRerollOffensive = (EditText) findViewById(R.id.formDecimalRerollAttack);
        this.inputRerollDefensive = (EditText) findViewById(R.id.formDecimalRerollDefense);
        configOffensiveSpinner();
    }

    /**
     * <p>Action of the Button</p>
     */
    protected void fight(){
        buttonFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataEmpty() && isDataValid() &&
                        isRerollDataValid(inputRerollOffensive, checkBoxRerollOffensive) &&
                        isRerollDataValid(inputRerollDefensive, checkBoxRerollShield)) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("inputOfensiveValue", inputOffensiveDice.getText().toString());
                    intent.putExtra("inputDefensiveValue", inputDefenseDice.getText().toString());
                    intent.putExtra("inputRerollOffensiveValue", inputRerollOffensive.getText().toString());
                    intent.putExtra("inputRerollDefensiveValue", inputRerollDefensive.getText().toString());

                    intent.putExtra("flagMeleeReroll",
                            offensiveSelector.getSelectedItem().toString().equals("MELEE") ? "true" : "false");
                    intent.putExtra("flagRangedReroll",
                            offensiveSelector.getSelectedItem().toString().equals("RANGED") ? "true" : "false");

                    intent.putExtra("flagDefensiveReroll", Boolean.toString(checkBoxRerollShield.isChecked()));
                    intent.putExtra("flagOffensiveReroll", Boolean.toString(checkBoxRerollOffensive.isChecked()));

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Type a number from 0 to 99 on each input!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * <p>Check if the Offensive and Defensive Input is Empty.</p>
     * @return {@link Boolean}
     */
    protected Boolean isDataEmpty() {
        Boolean flagIsValid = false;

        if(!inputOffensiveDice.getText().toString().isEmpty() &&
                !inputDefenseDice.getText().toString().isEmpty()) {
            flagIsValid = true;
        }
        return flagIsValid;
    }

    /**
     * <p>Check if the Offensive and Defensive Input is Valid.</p>
     * @return {@link Boolean}
     */
    protected Boolean isDataValid() {
        Boolean flagIsValid = false;

        Integer qtdOffensiveDice = Integer.parseInt(inputOffensiveDice.getText().toString());
        Integer qtdDefensiveDice = Integer.parseInt(inputDefenseDice.getText().toString());

        if(qtdOffensiveDice < 100 && qtdDefensiveDice < 100) {
            flagIsValid = true;
        }
        return flagIsValid;
    }

    /**
     * <p>Check if the Reroll is Valid by Checking the Checkbox state and input values.</p>
     * @param input ({@link EditText})
     * @param checkBox ({@link CheckBox})
     * @return {@link Boolean}
     */
    protected Boolean isRerollDataValid(final EditText input, final CheckBox checkBox) {
        Boolean flagIsValid = false;

        if(!input.getText().toString().isEmpty()) {
            final Integer rollAmount = Integer.parseInt(input.getText().toString());
            if(checkBox.isChecked() && rollAmount < 100) {
                flagIsValid = true;
            } else if(!checkBox.isChecked()) {
                flagIsValid = true;
            }
        }
        return flagIsValid;
    }

    /**
     * <p>Method to Config the Offensive Spinner.</p>
     */
    protected void configOffensiveSpinner() {
        this.offensiveSelector = (Spinner) findViewById(R.id.offensiveSelector);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
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
