package com.andia.loice.fortuner.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andia.loice.fortuner.R;
import com.andia.loice.fortuner.databinding.ActivityMainBinding;
import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.viewmodel.FortuneViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    ActivityMainBinding activityMainBinding;

    private FortuneViewModel fortuneViewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private Toolbar toolbar;
    private TextView fortuneText;
    private TextView writerText;
    private ProgressBar progressBar;
    private FloatingActionButton refreshButton;
    private ConstraintLayout contentMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        toolbar = activityMainBinding.toolbar;
        fortuneText = activityMainBinding.fortuneText;
        writerText = activityMainBinding.writerInfo;
        progressBar = activityMainBinding.loadingPage;
        refreshButton = activityMainBinding.fab;
        contentMain = activityMainBinding.contentMain;

        setSupportActionBar(toolbar);

//        progressBar.setVisibility(View.VISIBLE);
//        contentMain.setVisibility(View.GONE);

        fortuneViewModel = ViewModelProviders.of(this, viewModelFactory).get(FortuneViewModel.class);

        refreshButton.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            fetchFortune();
        });

        fetchFortune();


    }

    private void fetchFortune() {
        final Observer<Fortune> fortuneObserver = results -> {
            Log.d("reselts ", results.toString());
            displayFortune(results);

        };

        fortuneViewModel.getFortunes().observe(this, fortuneObserver);

    }

    private void displayFortune(Fortune fortune) {

        progressBar.setVisibility(View.GONE);
        contentMain.setVisibility(View.VISIBLE);

        fortuneText.setText(fortune.getFortune());
        writerText.setText(fortune.getWriter());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            progressBar.setVisibility(View.VISIBLE);
            fetchFortune();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
