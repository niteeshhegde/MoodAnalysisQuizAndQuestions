package abc.moodanalysis;

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
        radioGroup = (RadioGroup) findViewById(R.id.rg9);
        btnDisplay = (Button) findViewById(R.id.button);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                total=0;
                int j9;
                int j8;

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                String i9=(String) radioButton.getText();
                j9 =Integer.parseInt(i9);
                total=total+j9;
                radioGroup = (RadioGroup) findViewById(R.id.rg9);
                radioButton = (RadioButton) findViewById(selectedId);
                String i8=(String) radioButton.getText();
                j8 =Integer.parseInt(i8);
                total=total+j8;



                Toast.makeText(getBaseContext(),""+total, Toast.LENGTH_SHORT).show();


            }

        });

    }
}
