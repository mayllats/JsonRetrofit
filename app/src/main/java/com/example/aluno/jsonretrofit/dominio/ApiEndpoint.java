package com.example.aluno.jsonretrofit.dominio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("posts/{id}")
    Call<Post> obterpost(@Path("id")int userID);
}
