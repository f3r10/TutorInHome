package com.tnb.f3r10.tutorinhome.bookingtutor.ui;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorPresenter;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.entities.Booking;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingTutorFragment extends DialogFragment implements DialogInterface.OnShowListener, BookingTutorView{

    @Bind(R.id.spinnerSubjec)
    MaterialBetterSpinner spinnerSubjec;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.seekBarTimeHours)
    SeekBar seekBarTimeHours;
    @Bind(R.id.txtHoursPicked)
    TextView txtHoursPicked;
    @Bind(R.id.editTextStartTime)
    TextView editTextStartTime;
    @Bind(R.id.editTextDay)
    TextView editTextDay;
    int step = 30;
    int max = 600;
    int min = 60;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private TutorInHomeApplication app;
    private ArrayList<String> basicSubject;
    private ArrayList<String> profesionalSubject;
    private String tutorId;
    private String numberHoursClass;
    @Inject
    FirebaseAPI firebaseAPI;
    @Inject
    BookingTutorPresenter presenter;

    public BookingTutorFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (TutorInHomeApplication)getActivity().getApplication();
        basicSubject = getArguments().getStringArrayList(app.getBasicSubjectsKey());
        profesionalSubject = getArguments().getStringArrayList(app.getProfesionalSubjectsKey());
        tutorId = getArguments().getString(app.getTutorId());
        basicSubject.addAll(profesionalSubject);
        Log.e("fragment " , tutorId);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.bookingtutor_message_title)
                .setPositiveButton(R.string.bookingtutor_message_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setNegativeButton(R.string.bookingtutor_message_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        LayoutInflater i = getActivity().getLayoutInflater();
        View view = i.inflate(R.layout.fragment_booking_tutor, null);
        ButterKnife.bind(this, view);
        setUpInjection();
        setUpView();
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    private void setUpInjection() {
        app.getBookingTutorComponent(this).inject(this);
    }

    private void setUpView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, basicSubject);
        spinnerSubjec.setAdapter(arrayAdapter);
        setUpSeekBar();
    }

    private void setUpSeekBar() {
        seekBarTimeHours.setMax( (max - min) / step );
        txtHoursPicked.setText( (min/60) + " hora" );
        seekBarTimeHours.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser)
                    {
                        // Ex :
                        // And finally when you want to retrieve the value in the range you
                        // wanted in the first place -> [3-5]
                        //
                        // if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
                        double value = min + (progress * step);
                        numberHoursClass = Double.toString(value/60);
                        txtHoursPicked.setText((value/60) + " hora");

                    }
                }
        );
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        final AlertDialog customDialog = (AlertDialog) getDialog();
        if (customDialog != null) {
            Button positiveButton = customDialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = customDialog.getButton(Dialog.BUTTON_NEGATIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(spinnerSubjec.getText().toString().isEmpty() || tutorId.isEmpty() || firebaseAPI.getAuthUserEmail().isEmpty()
                            || editTextStartTime.getText().toString().isEmpty() || numberHoursClass.isEmpty()){
                        Toast.makeText(getActivity(), R.string.bookingtutor_message_booking_input_incomplete, Toast.LENGTH_LONG).show();
                    }else{
                        Booking booking = new Booking();
                        booking.setDay(spinnerSubjec.getText().toString());
                        booking.setIdTutor(tutorId);
                        booking.setStudentEmail(firebaseAPI.getAuthUserEmail());
                        booking.setStartTime(editTextStartTime.getText().toString());
                        booking.setNumberHours(numberHoursClass);
                        presenter.bookingTutor(booking);
                    }

                }
            });
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }

    @OnClick(R.id.editTextStartTime)
    public void selectTime(){
        // Process to get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog tpd = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Display Selected time in EditText
                        editTextStartTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);

        tpd.show();
    }

    @OnClick(R.id.editTextDay)
    public void selectDay(){
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in EditText
                        editTextDay.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showInput() {
        spinnerSubjec.setVisibility(View.VISIBLE);
        seekBarTimeHours.setVisibility(View.VISIBLE);
        txtHoursPicked.setVisibility(View.VISIBLE);
        editTextStartTime.setVisibility(View.VISIBLE);
        editTextDay.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        spinnerSubjec.setVisibility(View.GONE);
        seekBarTimeHours.setVisibility(View.GONE);
        txtHoursPicked.setVisibility(View.GONE);
        editTextStartTime.setVisibility(View.GONE);
        editTextDay.setVisibility(View.GONE);
    }

    @Override
    public void successBookingTutor() {
        Toast.makeText(getActivity(), R.string.bookingtutor_message_bookingadded, Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void errorBookingTutor() {
        spinnerSubjec.setText("");
        txtHoursPicked.setText( 1 + " hora" );
        seekBarTimeHours.setProgress(min);
        txtHoursPicked.setText("");
        editTextStartTime.setText("");
        editTextDay.setText("");
        Toast.makeText(getActivity(), R.string.bookingtutor_message_bookingerror, Toast.LENGTH_LONG).show();
    }
}
