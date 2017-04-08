package abc.moodanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnDisplay = (Button) findViewById(R.id.button);
        Bundle extras = getIntent().getExtras();
        final  String  b=extras.getString("gender");
        final String a=extras.getString("age");

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                total=0;
                int j9;
                int j8;

                radioGroup = (RadioGroup) findViewById(R.id.rg8);

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                String i9=(String) radioButton.getText();
                j9 =Integer.parseInt(i9);
                total=total+j9;
                radioGroup = (RadioGroup) findViewById(R.id.rg9);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String i8=(String) radioButton.getText();
                j8 =Integer.parseInt(i8);
                total=total+j8;
                radioGroup = (RadioGroup) findViewById(R.id.rg1);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
               if(i8.equals("Yes")){
                   total=total+2;
               }
                radioGroup = (RadioGroup) findViewById(R.id.rg2);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg4);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg5);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg6);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg7);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }
                radioGroup = (RadioGroup) findViewById(R.id.rg8);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                i8=(String) radioButton.getText().toString();
                if(i8.equals("Yes")){
                    total=total+2;
                }


                Intent i =new Intent(getBaseContext(),Main3Activity.class);
                i.putExtra("gender",b);
                i.putExtra("age",a);
                i.putExtra("total",total);
                startActivity(i);
              // Toast.makeText(getBaseContext(),""+total, Toast.LENGTH_SHORT).show();


            }

        });

    }
}
