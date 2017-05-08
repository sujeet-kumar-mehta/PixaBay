package com.sujeet.pixabay.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sujeetmehta on 07/05/17.
 */

public class ImageModelResponse {

    @SerializedName("hits")
    List<ImageModel> imageModelList;

    public List<ImageModel> getImageModelList() {
        return imageModelList;
    }

    public void setImageModelList(List<ImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }
}
