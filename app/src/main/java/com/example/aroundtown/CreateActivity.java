package com.example.aroundtown;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;


import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener  {
    Button btn;
    Button create;
    Button queuedate, queuetime;
    Button eventdate, eventtime;
    Button edit_picture;
    private static int RESULT_LOAD_IMAGE;
    ImageView event_picture;
    EditText txtDate, txtTime, queueDate, queueTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    TextView selectedType;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        btn = (Button)findViewById(R.id.addType);
        create = (Button)findViewById(R.id.Create);

        queuedate=(Button)findViewById(R.id.btn_queue_date);
        queuetime=(Button)findViewById(R.id.btn_queue_time);
        eventdate=(Button)findViewById(R.id.btn_event_date);
        eventtime=(Button)findViewById(R.id.btn_event_time);
        txtDate=(EditText)findViewById(R.id.event_date);
        txtTime=(EditText)findViewById(R.id.event_time);
        queueDate=(EditText)findViewById(R.id.queue_date);
        queueTime=(EditText)findViewById(R.id.queue_time);

        queuedate.setOnClickListener(this);
        queuetime.setOnClickListener(this);
        eventdate.setOnClickListener(this);
        eventtime.setOnClickListener(this);

        event_picture = (ImageView) findViewById(R.id.event_picture);
        edit_picture = (Button) findViewById(R.id.edit_picture);
        edit_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent, RESULT_LOAD_IMAGE);



            }



        });





        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.repeatingEvent, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ageRating, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        selectedType = (TextView) findViewById(R.id.tvItemSelected);

        listItems = getResources().getStringArray(R.array.event_type);
        checkedItems = new boolean[listItems.length];




        create.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(CreateActivity.this, "Creating Event...", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateActivity.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        selectedType.setTextSize(15);
                        selectedType.setText(item);

                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            selectedType.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        CreateActivity.super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            event_picture.setImageURI(selectedImage);
        }
    }


    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }


    public View getDropDownView(int position, View view,
                                ViewGroup parent) {

        TextView tv = (TextView) view;
        if (position == 0) {
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        isEnabled(position);
        getDropDownView(position, view, parent);
        String text = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onClick(View v) {

        if (v == eventdate || v == queuedate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            if(v == eventdate) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                txtDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

            if(v == queuedate) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                queueDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        }
        if (v == eventtime || v == queuetime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR);
            mMinute = c.get(Calendar.MINUTE);

            if(v == eventtime) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String time = "AM";
                                String zero = "";
                                if (hourOfDay >= 12) {
                                    hourOfDay = hourOfDay - 12;
                                    time = "PM";
                                }
                                if (hourOfDay == 0)
                                    hourOfDay = 12;
                                if (minute < 10)
                                    zero = "0";

                                txtTime.setText(hourOfDay + ":" + zero + minute + " " + time);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

            if(v == queuetime) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String time = "AM";
                                String zero = "";
                                if (hourOfDay >= 12) {
                                    hourOfDay = hourOfDay - 12;
                                    time = "PM";
                                }
                                if (hourOfDay == 0)
                                    hourOfDay = 12;
                                if (minute < 10)
                                    zero = "0";
                                queueTime.setText(hourOfDay + ":" + zero + minute + " " + time);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        }
    }

}