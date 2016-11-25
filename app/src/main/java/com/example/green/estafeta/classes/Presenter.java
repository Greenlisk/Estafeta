package com.example.green.estafeta.classes;

import java.util.List;

/**
 * Created by green on 11/25/16.
 */

public interface Presenter {
    List<Task> fetchData();
    void setView(LoginView view);
    Task getTask(int position);
    List<Task> getFiltered(String filter);
    void authenticate(String username, String password);
}
