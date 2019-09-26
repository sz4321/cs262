package edu.calvin.cs262.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;
/**
  *Creates a main activity
  *@Author: Sebrina Zeleke
 */
public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> operator;
    int selected_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        operator = ArrayAdapter.createFromResource(this,R.array.operators, android.R.layout.simple_spinner_item);
        operator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(operator);
        selected_item = spinner.getSelectedItemPosition();
    }

    /**
    * This method gets called when the calculate button is called
    * @param: View
    * @return void
     */
    public void calculate_operation(View view) {
        //get the first number
        EditText first_num = findViewById(R.id.editText2);
        String first_entry = first_num.getText().toString();

        //get the second number
        EditText second_num = findViewById(R.id.second_value_entry);
        String second_entry = second_num.getText().toString();

        //get the state of the textView to use it to output
        TextView final_result = findViewById(R.id.result_view);

        selected_item = spinner.getSelectedItemPosition();

        //calculate the result based on the operator provided and the number given

        if(selected_item == 0){
            int add_num = Integer.parseInt(first_entry) + Integer.parseInt(second_entry);
            final_result.setText(String.valueOf(add_num));
        }
        else if(selected_item == 1){
            int sub_num = Integer.parseInt(first_entry) - Integer.parseInt(second_entry);
            final_result.setText(String.valueOf(sub_num));
        }
        else if(selected_item == 2){
            int mul_num = Integer.parseInt(first_entry) / Integer.parseInt(second_entry);
            final_result.setText(String.valueOf(mul_num));
        }
        else if(selected_item == 3){
            int div_num = Integer.parseInt(first_entry) * Integer.parseInt(second_entry);
            final_result.setText(String.valueOf(div_num));
        }


    }
}
