package com.example.aluno.jsonretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.aluno.jsonretrofit.dominio.ApiEndpoint;
import com.example.aluno.jsonretrofit.dominio.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder()

                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<Post> call = apiService.obterpost(1);
        call.enqueue(new Callback<Post>() {

            public void onResponse(Call<Post> call, Response<Post> response) {
                int statusCode = response.code();
                Post post = response.body();
                Log.i("teste", "statuscode: " + statusCode);
                Log.i("teste", "title: " + post.getTitle());
            }

            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("teste", t.toString());
            }
        });
    }
}
