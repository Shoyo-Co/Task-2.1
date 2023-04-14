package com.example.task21;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private EditText editText;
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview1 = findViewById(R.id.source_unit_label);
        spinner1 =  findViewById(R.id.source_unit_sipnner);
        textview2 = findViewById(R.id.destination_unit_label);
        spinner2 =  findViewById(R.id.destination_unit_sipnner);
        editText = findViewById(R.id.value_input);
        button = findViewById(R.id.button);
        textview3 = findViewById(R.id.result_label);
        textview4 = findViewById(R.id.result_output);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Temperature_units, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] Temperature_units = getResources().getStringArray(R.array.Temperature_units);
                Toast.makeText(MainActivity.this, "you select: "+Temperature_units[pos], Toast.LENGTH_SHORT).show();
                String selectedOption = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] Temperature_units = getResources().getStringArray(R.array.Temperature_units);
                Toast.makeText(MainActivity.this, "you select: "+Temperature_units[pos], Toast.LENGTH_SHORT).show();
                String selectedOption = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String fromUnit = spinner1.getSelectedItem().toString();
//                String toUnit = spinner2.getSelectedItem().toString();
//                double Tem = Double.parseDouble(editText.getText().toString();
                double Tem = 0;

                try {
                    String input = editText.getText().toString();

                    // Attempt to parse the input as a double
                    Tem = Double.parseDouble(input);

                    // If parsing is successful, input is a double
                    System.out.println("Input is a double: " + Tem);
                } catch (NumberFormatException e) {
                    // If parsing fails, input is not a double
                    System.out.println("Input is not a double: " + e.getMessage());
                }

                int pos1 = spinner1.getSelectedItemPosition();
                int pos2 = spinner2.getSelectedItemPosition();

                if ( pos1 == pos2) {
                    Toast.makeText( MainActivity.this, "Please select different unit.", Toast.LENGTH_LONG).show();
                }
                else if ((pos1==2 & Tem < 0) | (pos1 == 0 & Tem < -273.15) | (pos1 == 1 & Tem < -459.67)) {
                    Toast.makeText( MainActivity.this, "Invalid input! Try again!", Toast.LENGTH_LONG).show();
                }

                else if (pos1 == 0 & pos2 ==1) {
                    textview4.setText(String.valueOf(Tem * 1.8 + 32));
                }
                else if (pos1 == 0 & pos2 ==2) {
                    textview4.setText(String.valueOf( Tem + 273.15));
                }
                else if (pos1 == 1 & pos2 ==0) {
                    textview4.setText(String.valueOf((Tem - 32) / 1.8));
                }
                else if (pos1 == 1 & pos2 ==2) {
                    textview4.setText(String.valueOf((Tem - 32) / 1.8 + 273.15));
                }
                else if (pos1 == 2 & pos2 ==0) {
                    textview4.setText(String.valueOf( Tem - 273.15));
                }
                else if (pos1 == 2 & pos2 ==1) {
                    textview4.setText(String.valueOf((Tem - 273.15) * 1.8 + 32 ));
                }


//

//                Toast.makeText( MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();
//                Log.v("Check Message", "Hello World!");

        }
        });

    }
}