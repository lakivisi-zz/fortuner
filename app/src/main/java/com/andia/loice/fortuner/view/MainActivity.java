package com.andia.loice.fortuner.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.andia.loice.fortuner.R;
import com.andia.loice.fortuner.databinding.ActivityMainBinding;
import com.andia.loice.fortuner.databinding.ContentMainBinding;
import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.viewmodel.FortuneViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    ActivityMainBinding activityMainBinding;
    ContentMainBinding contentMainBinding;


    private FortuneViewModel fortuneViewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView fortuneText;
    private TextView writerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        contentMainBinding = DataBindingUtil.setContentView(this, R.layout.content_main);
        toolbar = activityMainBinding.toolbar;
        fortuneText = contentMainBinding.fortuneText;
        writerText = contentMainBinding.writerInfo;
        fab = activityMainBinding.fab;

        setSupportActionBar(toolbar);

        fortuneViewModel = ViewModelProviders.of(this, viewModelFactory).get(FortuneViewModel.class);

        fetchFortune();

        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    private void fetchFortune() {

        final Observer<Fortune> fortuneObserver = results -> {
            displayFortune(results);
        };

        fortuneViewModel.getFortunes().observe(this, fortuneObserver);

    }

    private void displayFortune(Fortune fortune) {

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
