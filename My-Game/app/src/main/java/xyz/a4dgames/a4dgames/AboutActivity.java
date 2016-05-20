package xyz.a4dgames.a4dgames;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class AboutActivity extends AppCompatActivity {

    private ImageView imageView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("About 4D Games");

        imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(imageView);
        fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_favorite).actionBar().color(Color.WHITE));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
