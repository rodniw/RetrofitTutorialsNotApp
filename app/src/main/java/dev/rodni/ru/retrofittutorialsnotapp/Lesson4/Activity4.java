package dev.rodni.ru.retrofittutorialsnotapp.Lesson4;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;

import dev.rodni.ru.retrofittutorialsnotapp.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity4 extends AppCompatActivity {

    private Button createAccountBtn;
    private EditText edit1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edit1 = findViewById(R.id.lesson_2_login);

        createAccountBtn = findViewById(R.id.lesson2_btn);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        //        sendToServer();
            }
        });
    }

    /*private void sendToServer(Uri uri) {

        //create RequestBody
        RequestBody descriptionPart = RequestBody.create(
                MultipartBody.FORM,
                edit1.getText().toString()
        );

        //create object fileutils
        //TODO: resolve FileUtils shit
        //File originalFile = FileUtils.getFile(this, uri);

        //filePart
        RequestBody filePart = RequestBody.create(
                MediaType.parse(getContentResolver().getType(uri)),
                originalFile
                //FileUtils.getFile(this, uri)
        );

        //TODO: resolve createFormData problev
        //MultipartBody.Part file = new MultipartBody.Part.createFormData("photo", originalFile.getName(), filePart);

        //create retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .addConverterFactory(GsonConverterFactory.create());

        //building to retrofit instance
        Retrofit retrofit = builder.build();

        //creating client
        UserClient client = retrofit.create(UserClient.class);
        //executing our method
        Call<ResponseBody> call = client.uploadPhoto(descriptionPart, file);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(Activity4.this, "yes" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Activity4.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
