package com.example.green.estafeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.green.estafeta.classes.Presenter;
import com.example.green.estafeta.classes.Task;

public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.details_toolbar);
        Presenter presenter = (Presenter) getApplicationContext();
        Task task = presenter.getTask(getIntent().getIntExtra("position", 0));
        toolbar.setTitle(task.Number);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView detailsTitleNumber = (TextView)findViewById(R.id.details_title_number);
        TextView detailsValueNumber = (TextView)findViewById(R.id.details_value_number);
        if(task.Number != null) {
            detailsValueNumber.setVisibility(View.VISIBLE);
            detailsValueNumber.setText(task.Number);
            detailsTitleNumber.setVisibility(View.VISIBLE);
        }
        TextView detailsTitlePlanDateStart = (TextView)findViewById(R.id.details_title_plan_date_start);
        TextView detailsValuePlanDateStart = (TextView)findViewById(R.id.details_value_plan_date_start);
        if(task.ActualStartDate != null) {
            detailsValuePlanDateStart.setVisibility(View.VISIBLE);
            detailsValuePlanDateStart.setText(task.ActualStartDate);
            detailsTitlePlanDateStart.setVisibility(View.VISIBLE);
        }
        TextView detailsTitlePlanDateEnd = (TextView)findViewById(R.id.details_title_plan_date_end);
        TextView detailsValuePlanDateEnd = (TextView)findViewById(R.id.details_value_plan_date_end);
        if(task.ActualEndDate != null) {
            detailsValuePlanDateEnd.setVisibility(View.VISIBLE);
            detailsValuePlanDateEnd.setText(task.ActualEndDate);
            detailsTitlePlanDateEnd.setVisibility(View.VISIBLE);
        }
        TextView detailsTitleVin = (TextView)findViewById(R.id.details_title_vin);
        TextView detailsValueVin = (TextView)findViewById(R.id.details_value_vin);
        if(task.Vin != null) {
            detailsValueVin.setVisibility(View.VISIBLE);
            detailsValueVin.setText(task.Vin);
            detailsTitleVin.setVisibility(View.VISIBLE);
        }
        TextView detailsTitleBrand = (TextView)findViewById(R.id.details_title_brand);
        TextView detailsValueBrand = (TextView)findViewById(R.id.details_value_brand);
        if(task.Brand != null) {
            detailsValueBrand.setVisibility(View.VISIBLE);
            detailsValueBrand.setText(task.Brand);
            detailsTitleBrand.setVisibility(View.VISIBLE);
        }
        TextView detailsTitleModel = (TextView)findViewById(R.id.details_title_model);
        TextView detailsValueModel = (TextView)findViewById(R.id.details_value_model);
        if(task.Model != null) {
            detailsValueModel.setVisibility(View.VISIBLE);
            detailsValueModel.setText(task.Vin);
            detailsTitleModel.setVisibility(View.VISIBLE);
        }
        TextView detailsTitleModelCode = (TextView)findViewById(R.id.details_title_model_code);
        TextView detailsValueModelCode = (TextView)findViewById(R.id.details_value_model_code);
        if(task.ModelCode != null) {
            detailsValueModelCode.setVisibility(View.VISIBLE);
            detailsValueModelCode.setText(task.ModelCode);
            detailsTitleModelCode.setVisibility(View.VISIBLE);
        }
        TextView detailsTitleSurveyPoint = (TextView)findViewById(R.id.details_title_survey_point);
        TextView detailsValueSurveyPoint = (TextView)findViewById(R.id.details_value_survey_point);
        if(task.SurveyPoint != null) {
            detailsValueSurveyPoint.setVisibility(View.VISIBLE);
            detailsValueSurveyPoint.setText(task.SurveyPoint);
            detailsTitleSurveyPoint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
