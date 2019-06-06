package dev.rodni.ru.retrofittutorialsnotapp.Lesson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import dev.rodni.ru.retrofittutorialsnotapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        listView = findViewById(R.id.listview_lesson1);

        //-----smaller way
        //Retrofit retrofit = new Retrofit.Builder()
        //        .baseUrl("https://api.github.com/")
        //        .addConverterFactory(GsonConverterFactory.create())
        //        .build();

        //-----full way
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //-----creating client
        GitHubApi client = retrofit.create(GitHubApi.class);
        //-----reffer to out clients method and push our user name there
        Call<List<GitHubRepo>> call = client.provideRepoList("rodniw");

        //-----async query
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                //putting results into listview
                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                //in case of problems
            }
        });
    }
}
