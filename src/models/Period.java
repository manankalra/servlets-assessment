package models;


public class Period {

    private String title;
    private int startTime;
    private int endTime;
    private int day;
    private Trainer trainer;

    public Period(String title, int startTime, int endTime, int day) {
        this.title = title;
        this.startTime =  startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public Period(String title, int startTime, int endTime, int day, Trainer trainer) {
        this.title = title;
        this.startTime =  startTime;
        this.endTime = endTime;
        this.day = day;
        this.trainer = trainer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}
