package ng.com.obkm.bottomnavviewwithfragments.services;

import ng.com.obkm.bottomnavviewwithfragments.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {
    @POST("/api/login")
    Call<User> loginUser(@Body User user);


}
