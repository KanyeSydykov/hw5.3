package com.pharos.a1_homework_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ActivityTwo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageButton imagebtncalendar;

    private EditText edText;
    private Button btn;
    public static String KEY2 = "key2";
    public String date;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        init();
        Intent intent = getIntent();
        if (intent != null) {
            Title title = (Title) intent.getSerializableExtra(MainActivity.KEY);
            edText.setText(title.name);
        }
    }

    private void init() {
        textViewDate = findViewById(R.id.textViewDate);
        edText = findViewById(R.id.etText);
        btn = findViewById(R.id.buttonResult);
        imagebtncalendar = findViewById(R.id.imageButtonPopup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Title title = new Title(edText.getText().toString(), date);
                intent.putExtra(KEY2, title);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imagebtncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment pickerFragment = new DataPickerFragment();
                pickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textViewDate.setText(date);
    }
}