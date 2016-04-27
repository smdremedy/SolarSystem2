package pl.eduweb.solarsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SolarObjectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SolarObjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SolarObjectFragment extends Fragment {

    public SolarObjectFragment() {
        // Required empty public constructor
    }

    public static SolarObjectFragment newInstance(SolarObject solarObject) {
        SolarObjectFragment fragment = new SolarObjectFragment();
        Bundle args = new Bundle();
        args.putSerializable("object", solarObject);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            solarObject = (SolarObject) getArguments().getSerializable("object");
            setSolarObject(solarObject);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solar_object, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static final String OBJECT_KEY = "object";
    @Bind(R.id.objectImageView)
    ImageView objectImageView;
    @Bind(R.id.solarObjectToolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.objectTextView)
    TextView objectTextView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.moonsLabel)
    TextView moonsLabel;
    @Bind(R.id.moonsRecyclerView)
    RecyclerView moonsRecyclerView;
    private SolarObject solarObject;


    public void setSolarObject(SolarObject solarObject) {
        this.solarObject = solarObject;
        try {
            String text = SolarObject.loadStringFromAssets(getContext(), solarObject.getText());
            objectTextView.setText(Html.fromHtml(text));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Glide.with(this)
                .load(solarObject.getImagePath())
                .into(objectImageView);

        moonsRecyclerView.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);
        moonsLabel.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);

        if(solarObject.hasMoons()) {
            //adapter
        }
    }

    public void setUpToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbarLayout.setTitle(solarObject.getName());
    }

    public static void start(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT_KEY, solarObject);
        activity.startActivity(intent);
    }




}
