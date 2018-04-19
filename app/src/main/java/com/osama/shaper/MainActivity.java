package com.osama.shaper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.osama.shaper.dependencies.BaseApplication;
import com.osama.shaper.dependencies.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponentManager().add(new ToastComponent())
                .add(new FABComponent());


        BaseApplication.get(this)
                .component()
                .getService()
                .getAllRepos()
                .enqueue(new Callback<List<GithubRepo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GithubRepo>> call, @NonNull Response<List<GithubRepo>> response) {
                        Toast.makeText(MainActivity.this, response.body().get(0).fullName, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GithubRepo>> call, @NonNull Throwable t) {

                    }
                });

    }
}
