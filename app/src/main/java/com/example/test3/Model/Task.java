package com.example.test3.Model;
import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private int id;
    private String title;
    private String description;
    private int year;
    private int month;
    private int date;
    private boolean complete=false;



    public Task(int id, String title, String description, int year, int month, int date){
        this.id=id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.month = month;
        this.date = date;
    }

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        year = in.readInt();
        month = in.readInt();
        date = in.readInt();
        complete = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeByte((byte) (complete ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }





}
