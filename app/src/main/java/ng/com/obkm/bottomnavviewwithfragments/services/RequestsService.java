package ng.com.obkm.bottomnavviewwithfragments.services;

import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.requests.RequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestsService {

    @GET("requests")
    Call<List<RequestModel>> getRequests();

    @GET("requests/{id}")
    Call<RequestModel> getRequestById(@Path("id") String id);

    @POST("requests")
    Call<RequestModel> createRequest(@Body RequestModel requestModel);

    @PUT("requests")
    Call<RequestModel> putPost(@Path("id")int id,@Body RequestModel requestModel);

}
