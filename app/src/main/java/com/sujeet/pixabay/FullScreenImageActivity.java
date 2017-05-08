package com.sujeet.pixabay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by sujeetmehta on 08/05/17.
 */

public class FullScreenImageActivity extends AppCompatActivity {

    public static final String FULL_IMAGE_URL = "url";
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra(FULL_IMAGE_URL)).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).dontAnimate().into(imageView);

    }

    public void onCloseClicked(View view) {
        supportFinishAfterTransition();
    }
}
