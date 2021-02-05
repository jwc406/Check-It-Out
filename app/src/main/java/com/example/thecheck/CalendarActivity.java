package com.example.thecheck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.prolificinteractive.materialcalendarview.CalendarDay.*;

public class CalendarActivity extends AppCompatActivity {

    MaterialCalendarView materialCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        materialCardView = findViewById(R.id.calendarView);
        materialCardView.setDateSelected(today(), true);
        materialCardView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );
        OneDayDecorator oneDayDecorator = new OneDayDecorator();

        materialCardView.addDecorators(
                oneDayDecorator
        );
        materialCardView.setSelectedDate(CalendarDay.today());

        materialCardView.addDecorators(new EventDecorator(Color.BLACK, Collections.singleton(CalendarDay.today())));


    }

}