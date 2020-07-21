package app.project.library.model;

public class Magazine extends Publication {

    private int month;
    private int day;
    private String language;

    public Magazine(int year, String title, String publisher, int month, int day, String language) {
        super(year, title, publisher);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void printInfo() {
        String info = getTitle() + "; " + getPublisher() + "; " + getYear() + "; " + this.month + "; " + this.day + "; " + this.language;
        System.out.println(info);
    }
}
