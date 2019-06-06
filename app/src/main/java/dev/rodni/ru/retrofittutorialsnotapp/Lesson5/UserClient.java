package dev.rodni.ru.retrofittutorialsnotapp.Lesson5;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserClient {

    //Post request to upload file to the server
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadPhoto(
            @Part("description") RequestBody description,
            @Part("location") RequestBody location,
            @Part("photographer") RequestBody photographer,
            @Part("year") RequestBody year,
            @Part MultipartBody.Part photo
    );
}
