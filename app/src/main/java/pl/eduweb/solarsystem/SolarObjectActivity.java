package pl.eduweb.solarsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SolarObjectActivity extends AppCompatActivity {

    public static final String OBJECT_KEY = "object";

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    private SolarObject solarObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_object);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.solarObjectToolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        solarObject = (SolarObject) getIntent().getSerializableExtra(OBJECT_KEY);

        toolbarLayout.setTitle(solarObject.getName());

        SolarObjectFragment solarObjectFragment = (SolarObjectFragment) getSupportFragmentManager().findFragmentById(R.id.solarObjectFragment);
        solarObjectFragment.setSolarObject(solarObject);


    }

    public static void start(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT_KEY, solarObject);
        activity.startActivity(intent);
    }


}
