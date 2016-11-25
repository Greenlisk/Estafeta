package com.example.green.estafeta;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.example.green.estafeta.classes.LoginView;
import com.example.green.estafeta.classes.Presenter;
import com.example.green.estafeta.classes.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by green on 11/25/16.
 */

public class EstafetaApplication extends Application implements Callback<List<Task>>, Presenter {
    private final static String LOG_TAG = "EstafetaApplication";
    private List<Task> tasks;
    private TasksFetcher fetcher;
    private LoginView view;

    private interface TasksFetcher{
        @GET("api/mobilesurveytasks/gettestsurveytasks")
        Call<List<Task>> fetchTasks(@Header("Authorization") String token);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://amt1.estafeta.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fetcher = retrofit.create(TasksFetcher.class);

    }

    @Override
    public List<Task> fetchData(){
        if(tasks != null) {
            return tasks;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void onFailure(Call<List<Task>> call, Throwable t) {
        Log.e(LOG_TAG, t.toString());
        view.dismissDialog();
        view.setError();
    }

    @Override
    public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
        if(response.isSuccessful()) {
            tasks = response.body();
            for (Task task : response.body()) {
                Log.v(LOG_TAG, task.Vin);
            }
            view.dismissDialog();
            view.stepNext();
        } else {
            view.dismissDialog();
            view.setError();
        }
    }

    @Override
    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public Task getTask(int position) {
        return tasks.get(position);
    }

    @Override
    public List<Task> getFiltered(String filter) {
        if(filter.isEmpty()){
            return tasks;
        }
        List<Task> result = new ArrayList<>();
        for(Task task : tasks){
            if(task.Vin != null && task.Vin.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
                continue;
            }
            if(task.ActualStartDate != null && task.ActualStartDate.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
                continue;
            }
            if(task.Model != null && task.Model.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
                continue;
            }
            if(task.Brand != null && task.Brand.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
                continue;
            }
            if(task.Driver != null && task.Driver.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
                continue;
            }
            if(task.ModelCode != null && task.ModelCode.toLowerCase().contains(filter.toLowerCase())){
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public void authenticate(String username, String password) {
        String creds = username + "@9F346DDB-8FF8-4F42-8221-6E03D6491756:" + password;
        Call<List<Task>> call = fetcher.fetchTasks("Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP));
        call.enqueue(this);
        view.showDialog();
    }
}
