package dev.rodni.ru.retrofittutorialsnotapp.Lesson7;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FileDownloadClient {

    //just fixed link
    @GET("images/futurestudio-university-logo.png")
    Call<ResponseBody> downloadFile();

    //not fixed link here
    @GET
    Call<ResponseBody> downloadFile(@Url String url);

    //this annotation allows us to shout down caching in the exact moment to the memory
    //it helps us when we have huge files like 50 mb(android is gonna crash because of the large memory leak)
    @Streaming
    @GET
    Call<ResponseBody> downloadFileStream(@Url String url);
}
