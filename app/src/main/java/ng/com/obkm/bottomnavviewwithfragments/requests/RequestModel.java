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

    //Date()

    public RequestModel(){

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
                '}';
    }
}
