package models;

import java.util.*;


public class Schedule {

    private List<Period> periods = new ArrayList();

    public Schedule() { }

    public Schedule(List<Period> periods) {
        this.periods = periods;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

}
