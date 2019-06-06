package dev.rodni.ru.retrofittutorialsnotapp.Lesson3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.rodni.ru.retrofittutorialsnotapp.Lesson2.User;
import dev.rodni.ru.retrofittutorialsnotapp.Lesson2.UserClient;
import dev.rodni.ru.retrofittutorialsnotapp.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity3 extends AppCompatActivity {

    private EditText edit1, edit2, edit3, edit4;
    private Button createAccountBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);

        edit1 = findViewById(R.id.lesson_2_login);
        edit2 = findViewById(R.id.lesson_2_login2);
        edit3 = findViewById(R.id.lesson_2_login3);
        edit4 = findViewById(R.id.lesson_2_login4);

        createAccountBtn = findViewById(R.id.lesson2_btn);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(
                        edit1.getText().toString(),
                        edit2.getText().toString(),
                        Integer.parseInt(edit3.getText().toString()),
                        edit4.getText().toString().split(",")
                );

                sendToServer(user);
            }
        });

    }

    private void sendToServer(User user) {

        //create OkHttpClient
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        //we can log all the headers and operations by using loggintInterceptor and the example below
        //create loggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //adding loggingInterceptor to okHttpBuilder
        okHttpBuilder.addInterceptor(loggingInterceptor);

        //we can hide this interceptor when we are in release build by doing this
        //if (BuildConfig.DEBUG) {
        //    okHttpBuilder.addInterceptor(loggingInterceptor);
        //}

        //create retrofit builder with OkHttpClient by using .client() method
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpBuilder.build());

        //building to retrofit instance
        Retrofit retrofit = builder.build();

        //creating client
        UserClient client = retrofit.create(UserClient.class);
        //executing our method
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(Activity3.this, response.body().getId() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Activity3.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
