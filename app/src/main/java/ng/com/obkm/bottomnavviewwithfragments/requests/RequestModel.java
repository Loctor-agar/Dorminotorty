package ng.com.obkm.bottomnavviewwithfragments.requests;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RequestModel {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("date")
    public Date date;
    @SerializedName("status")
    public String status;
    @SerializedName("room")
    public String room;
    @SerializedName("author")
    public String author;
    @SerializedName("category")
    public String category;

    //Date()

    public RequestModel(){

    }

    public RequestModel(int id, String title, String description, Date date, String status, String room, String author, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
        this.room = room;
        this.author = author;
        this.category = category;
    }

    public RequestModel(int id, String title, String description, Date date, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }



    public RequestModel(String title, String description, Date date,String status) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public RequestModel(String rqTitle, String dfgdfg, Date currenttDate) {
        this.title = rqTitle;
        this.description = dfgdfg;
        this.date = currenttDate;
        //this.status = status;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", room='" + room + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
