package ng.com.obkm.bottomnavviewwithfragments.services;

import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnnouncementService {
    @GET("announcements")
    Call<List<AnnouncementModel>> getAnnouncements();

    @GET("announcements/{id}")
    Call<AnnouncementModel> getAnnouncementById(@Path("id") String id);

    @POST("announcements")
    Call<AnnouncementModel> createAnnouncement(@Body AnnouncementModel announcementModel);

    @PUT("announcements")
    Call<AnnouncementModel> putPost(@Path("id")int id,@Body AnnouncementModel announcementModel);
}
