package sg.edu.rp.c346.id20008460.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnReset;
    Button btnConfirm;

    TimePicker tp ;
    DatePicker dp;

    RadioGroup rdArea;

    TextView result;

    EditText name;
    EditText pax;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.buttonReset);
        btnConfirm = findViewById(R.id.buttonConfirm);

        tp = findViewById(R.id.timePicker);
        dp = findViewById(R.id.datePicker);

        rdArea = findViewById(R.id.radioBtnArea);

        result = findViewById(R.id.tvResult);

        pax = findViewById(R.id.editSize);
        name = findViewById(R.id.editName);
        phone = findViewById(R.id.editPhone);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.setMinDate(System.currentTimeMillis() - 1000);
                Date currentTime = Calendar.getInstance().getTime();

                if (name.getText().toString().trim().length() != 0  && phone.getText().toString().trim().length() != 0 &&  pax.getText().toString().trim().length() != 0) {
                    if (rdArea.getCheckedRadioButtonId() == R.id.radioNSA) {
                        result.setText("Your booking for " + dp.getDayOfMonth() + "/" +
                                dp.getMonth() + "/" + dp.getYear() + " , " + tp.getCurrentHour() + ":" + tp.getCurrentMinute() + " has been booked \nOverview of details : \nName: " + name.getText().toString() +
                                "\nNumber of pax : " + pax.getText().toString() + "\nSeating :  Non-Smoking Area ");
                    } else {
                        result.setText("Your booking for " + dp.getDayOfMonth() + "/" +
                                dp.getMonth() + "/" + dp.getYear() + " , " + tp.getCurrentHour() + ":" + tp.getCurrentMinute() + " has been booked \nOverview of details : \nName: " + name.getText().toString() +
                                "\nNumber of pax : " + pax.getText().toString() + "\nSeating :  Smoking Area ");

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please key in all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                result.setText("");
                rdArea.clearCheck();
                result.setText("");


            }


        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                int currentMin = rightNow.get(Calendar.MINUTE);


                if (tp.getCurrentHour() > currentHour ) { // if set Hour is more than the timing now && the timing now is

                        if (hourOfDay >= 8) { // if the time is 8am and above
                            if (hourOfDay >= 21) { // if the time is 9pm and above ////////////
                                tp.setCurrentHour(20); // set to 8pm as the timing is 9pm and above (condition) - enhancement
                            }

                        } else {
                            tp.setCurrentHour(8); // set to 8 am as the timing is not 9pm and above ; set to 8 am - enhancement
                        }

                }

            }
        });




    }
}