package com.example.no0ne.datepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar mCalendar;

    private int year, month, day;
    private int hour, minute;

    private TextView mDateTextView;
    private TextView mTimeTextView;
    private Button mDateButton;
    private Button mTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateTextView = findViewById(R.id.text_view_date);
        mTimeTextView = findViewById(R.id.text_view_time);
        mDateButton = findViewById(R.id.button_date);
        mTimeButton = findViewById(R.id.button_time);

        // Get the calender
        mCalendar = Calendar.getInstance();

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // From calender get the year, month, day, hour, minute
                year = mCalendar.get(Calendar.YEAR);
                month = mCalendar.get(Calendar.MONTH);
                day = mCalendar.get(Calendar.DAY_OF_MONTH);

                updateDate();
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                minute = mCalendar.get(Calendar.MINUTE);

                updateTime();
            }
        });
    }

    private void updateDate() {
        new DatePickerDialog(this, dateListener,
                mCalendar.get(Calendar.DAY_OF_MONTH),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.YEAR))
                .show();
    }

    private void updateTime() {
        new TimePickerDialog(this, timeListener,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE), false)
                .show();
    }

    // Date picker dialog
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
            String date = String.valueOf(month + 1) + "/" + String.valueOf(day) + "/" + String.valueOf(year);

            mDateTextView.setText(date);
        }
    };

    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);

            mTimeTextView.setText(time);
        }
    };
}
