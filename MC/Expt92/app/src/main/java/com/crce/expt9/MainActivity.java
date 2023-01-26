package com.crce.expt9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button submit;
    CheckBox ch, ch1, ch2;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        submit = (Button)findViewById(R.id.submit);
        ch=(CheckBox)findViewById(R.id.checkBox);
        ch1=(CheckBox)findViewById(R.id.checkBox2);
        ch2=(CheckBox)findViewById(R.id.checkBox3);
        et=(EditText)findViewById(R.id.editTextTextPersonName);
        // Uncheck or reset the radio buttons initially

        // Add the Listener to the RadioGroup
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked
                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group, int checkedId)
                    {
                        // Get the selected Radio Button
                        RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
                    }
                });

        // Add the Listener to the Submit Button
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // When submit button is clicked,
                // Ge the Radio Button which is set
                // If no Radio Button is set, -1 will be returned
                String msg="";

                // Concatenation of the checked options in if

                // isChecked() is used to check whether
                // the CheckBox is in true state or not.

                if(ch.isChecked())
                    msg = msg + " TechMax ";
                if(ch1.isChecked())
                    msg = msg + " TechNeo ";
                if(ch2.isChecked())
                    msg = msg + " TechKnowledge ";

                if (msg.equals("")){
                    Toast.makeText(MainActivity.this,
                            "No book has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this,
                            "No subject has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {

                    RadioButton radioButton
                            = (RadioButton)radioGroup
                            .findViewById(selectedId);

                    // Now display the value of selected item
                    // by the Toast message
                    Toast.makeText(MainActivity.this,
                            et.getText().toString()+ "\n Subject: " +radioButton.getText()+ "\n Books:\n" + msg,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}