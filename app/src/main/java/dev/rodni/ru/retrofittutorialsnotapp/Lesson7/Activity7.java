package dev.rodni.ru.retrofittutorialsnotapp.Lesson7;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dev.rodni.ru.retrofittutorialsnotapp.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Activity7 extends AppCompatActivity {

    private Button downloadBtn, cancelDownloadBtn;
    private static final String TAG = "THIS";
    final String URL = "https://futurestudio.io/images/futurestudio-university-logo.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);
        
        downloadBtn = findViewById(R.id.download_btn);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://futurestudio.io/");

        Retrofit retrofit = builder.build();

        FileDownloadClient client = retrofit.create(FileDownloadClient.class);
        //Call<ResponseBody> call = client.downloadFile(url);
        //instead of using simple get we will use streaming one
        final Call<ResponseBody> call = client.downloadFileStream(URL);
        
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                call.enqueue(new Callback<ResponseBody>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                        //here we have bad approach of asynctask work and its much better to work with rx
                        //but this one is only for the example where we need to work with streaming in one more thread
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                boolean success = writeResponseBodyToDisk(response.body());

                                return null;
                            }
                        }.execute();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (call.isCanceled()) {
                            Toast.makeText(Activity7.this, "canceled", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Activity7.this, "wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        cancelDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.cancel();
                Toast.makeText(Activity7.this, "canceling", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "MyDownload.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
