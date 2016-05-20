package xyz.a4dgames.a4dgames;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

/**
 * Created by Issac on 5/19/2016.
 */
public class DisableDaysDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SUNDAY || weekDay == Calendar.TUESDAY || weekDay == Calendar.WEDNESDAY
                || weekDay == Calendar.SATURDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }

}

