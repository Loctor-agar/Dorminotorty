package ng.com.obkm.bottomnavviewwithfragments.announcements;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AnnouncementModel {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public  String title;
    @SerializedName("description")
    public String desc;
    @SerializedName("date")
    public Date date;


    public AnnouncementModel(){

    }



    public AnnouncementModel(int id, String title, String desc, Date date) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public AnnouncementModel(String title, String desc, Date date) {
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
