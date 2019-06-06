package dev.rodni.ru.retrofittutorialsnotapp.Lesson3;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    //Post request with pojo body which in our example is user object
    @POST("user")
    Call<User> createAccount(@Body User user);

    //if we want to just update user then we need just to use PUT annotation instead of POST
    //@PUT("user")
    //Call<User> updateAccount(@Body User user);
}
