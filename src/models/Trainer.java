package models;


public class Trainer {

    private String name;
    private String subjectOne;
    private String subjectTwo;

    public Trainer(String name, String subjectOne, String subjectTwo) {
        this.name = name;
        this.subjectOne = subjectOne;
        this.subjectTwo = subjectTwo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectOne() {
        return this.subjectOne;
    }

    public void setSubjectOne(String subjectOne) {
        this.subjectOne = subjectOne;
    }

    public String getSubjectTwo() {
        return this.subjectTwo;
    }

    public void setSubjectTwo(String subjectTwo) {
        this.subjectTwo = subjectTwo;
    }

}
