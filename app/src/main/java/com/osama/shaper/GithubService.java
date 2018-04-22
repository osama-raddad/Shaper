package com.osama.shaper;


import java.util.List;

import rx.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

  @GET("users/{username}/repos")
  Call<List<GithubRepo>> getReposForUser(@Path("username") String username);

  @GET("repositories")
  Observable<List<GithubRepo>> getAllRepos();

  @GET("users/{username}")
  Observable<GithubUser> getUser(@Path("username") String username);
}
