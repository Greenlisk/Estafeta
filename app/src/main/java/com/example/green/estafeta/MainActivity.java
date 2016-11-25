package com.example.green.estafeta;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.green.estafeta.adapters.TasksListAdapter;

import com.example.green.estafeta.classes.Presenter;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private final static String LOG_TAG = "MainActivity";

    private Presenter application;
    private TasksListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.main_toolbar));

        RecyclerView tasksList = (RecyclerView)findViewById(R.id.tasks_list);
        tasksList.setLayoutManager(new LinearLayoutManager(this));
        tasksList.addItemDecoration(new SimpleDividerItemDecoration(this));
        application = (Presenter)getApplication();
        adapter = new TasksListAdapter(application.fetchData(), this);
        tasksList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.replaceData(application.getFiltered(newText));
        return true;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.divider_line, null);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
