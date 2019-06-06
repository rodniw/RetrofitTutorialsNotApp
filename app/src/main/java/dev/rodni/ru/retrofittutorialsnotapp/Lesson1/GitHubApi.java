package dev.rodni.ru.retrofittutorialsnotapp.Lesson1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    //we are making get request from the server and with Path annotations we can add username dinamically
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> provideRepoList(@Path("user") String user);
}
