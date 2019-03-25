package com.jhonatanrocha.arcadiaquest.diceroller.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonatanrocha.arcadiaquest.diceroller.MainActivity;
import com.jhonatanrocha.arcadiaquest.diceroller.R;
import com.jhonatanrocha.arcadiaquest.diceroller.ResultActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText inputOffensiveDice;
    private EditText inputDefenseDice;
    private CheckBox checkBoxRerollOffensive;
    private CheckBox checkBoxRerollShield;
    private EditText inputRerollOffensive;
    private EditText inputRerollDefensive;
    private Spinner offensiveSelector;
    private ImageButton buttonFight;
    private View view;

    public BattleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_battle, container, false);
        loadComponents();
        fight();

        return view;
    }

    /**
     *<p>Load all the Components from the Main Activity.</p>
     */
    protected void loadComponents() {
        this.inputOffensiveDice = (EditText)  view.findViewById(R.id.formDecimalAttack);
        this.inputDefenseDice = (EditText) view.findViewById(R.id.formDecimalDefense);
        this.buttonFight = (ImageButton) view.findViewById(R.id.imageButtonThrowDiceId);
        this.checkBoxRerollOffensive = (CheckBox) view.findViewById(R.id.checkBoxRerollOffensiveId);
        this.checkBoxRerollShield = (CheckBox) view.findViewById(R.id.checkBoxRerollShieldId);
        this.inputRerollOffensive = (EditText) view.findViewById(R.id.formDecimalRerollAttack);
        this.inputRerollDefensive = (EditText) view.findViewById(R.id.formDecimalRerollDefense);
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
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
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
                    Toast.makeText(getActivity().getApplicationContext(), "Type a number from 0 to 99 on each input!", Toast.LENGTH_SHORT).show();
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
        this.offensiveSelector = (Spinner) view.findViewById(R.id.offensiveSelector);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
