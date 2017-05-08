package com.sujeet.pixabay.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sujeetmehta on 07/05/17.
 */

public class ImageModel {

    @SerializedName("previewURL")
    private String previewURL;

    @SerializedName("webformatURL")
    private String fullImageUrl;

    @SerializedName("userImageURL")
    private String userImage;

    @SerializedName("id")
    private long id;

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getFullImageUrl() {
        return fullImageUrl;
    }

    public void setFullImageUrl(String fullImageUrl) {
        this.fullImageUrl = fullImageUrl;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
