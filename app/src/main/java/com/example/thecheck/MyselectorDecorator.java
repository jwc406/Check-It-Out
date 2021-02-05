package com.example.thecheck;


import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class MyselectorDecorator implements DayViewDecorator {

    private  final Drawable drawable;
    public MyselectorDecorator(Activity context){
        drawable = context.getResources().getDrawable(R.drawable.ic_icon__19_);
    }
    @Override
    public boolean shouldDecorate(CalendarDay day){
        return true;
    }

    @Override
    public void decorate(DayViewFacade view){
        view.setSelectionDrawable(drawable);
    }
}
