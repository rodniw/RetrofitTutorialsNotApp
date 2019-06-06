package dev.rodni.ru.retrofittutorialsnotapp.Lesson2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    //Post request with pojo body which in our example is user object
    @POST("user")
    Call<User> createAccount(@Body User user);
}
