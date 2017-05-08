package com.sujeet.pixabay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sujeet.pixabay.FullScreenImageActivity;
import com.sujeet.pixabay.R;
import com.sujeet.pixabay.model.ImageModel;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {


    private Activity context;

    private List<ImageModel> ImageModelList;

    public void setImageModelList(List<ImageModel> ImageModelList) {
        this.ImageModelList = ImageModelList;
    }

    public ImageRecyclerAdapter(Activity context) {
        this.context = context;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_image_details, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.setUi(ImageModelList.get(position));
    }

    @Override
    public int getItemCount() {

        return ImageModelList != null ? ImageModelList.size() : 0;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userImageView;
        private ImageView imageView;
        private ProgressBar progressBar;

        public ImageViewHolder(View itemView) {
            super(itemView);
            userImageView = (CircleImageView) itemView.findViewById(R.id.user_image_view);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);

            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);

        }

        public void setUi(final ImageModel imageModel) {


            Glide.with(itemView.getContext()).load(imageModel.getUserImage()).dontAnimate().into(userImageView);
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext()).load(imageModel.getPreviewURL()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent fullImageActivityIntent = new Intent(itemView.getContext(), FullScreenImageActivity.class);
                    fullImageActivityIntent.putExtra(FullScreenImageActivity.FULL_IMAGE_URL, imageModel.getFullImageUrl());
                    fullImageActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    fullImageActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(context, imageView, itemView.getContext().getString(R.string.transition_name_of_image));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        itemView.getContext().startActivity(fullImageActivityIntent, options.toBundle());
                    } else {
                        itemView.getContext().startActivity(fullImageActivityIntent);

                    }

                }
            });
        }

    }


}
