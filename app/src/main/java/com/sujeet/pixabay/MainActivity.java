package com.sujeet.pixabay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sujeet.pixabay.adapter.ImageRecyclerAdapter;
import com.sujeet.pixabay.api.ApiClient;
import com.sujeet.pixabay.api.ApiInterface;
import com.sujeet.pixabay.model.ImageModel;
import com.sujeet.pixabay.model.ImageModelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private ImageButton mSearchImageButton;
    private RecyclerView mRecyclerView;
    private ImageRecyclerAdapter mImageRecyclerAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    public void initUi() {

        mSearchEditText = (EditText) findViewById(R.id.search_edit_text);
        mSearchImageButton = (ImageButton) findViewById(R.id.search_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mImageRecyclerAdapter = new ImageRecyclerAdapter(this);
        mRecyclerView.setAdapter(mImageRecyclerAdapter);
    }

    public void onSearchClick(View view) {
        String searchTerm = mSearchEditText.getText().toString();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            searchAndShowTheResult(searchTerm);
        } else {
            showToast("Search text missing!");
        }
    }

    private void searchAndShowTheResult(String searchTerm) {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

        Call<ImageModelResponse> responseCall = apiClient.getSearchImages(searchTerm);

        mProgressBar.setVisibility(View.VISIBLE);
        responseCall.enqueue(new Callback<ImageModelResponse>() {
            @Override
            public void onResponse(Call<ImageModelResponse> call, Response<ImageModelResponse> response) {

                ImageModelResponse modelResponse = response.body();
                List<ImageModel> imageModelList = modelResponse.getImageModelList();
                mProgressBar.setVisibility(View.GONE);
                if (imageModelList != null && imageModelList.size() > 0) {

                    mImageRecyclerAdapter.setImageModelList(imageModelList);
                    mImageRecyclerAdapter.notifyDataSetChanged();
                } else {
                    showToast("No Result found");
                }
            }

            @Override
            public void onFailure(Call<ImageModelResponse> call, Throwable t) {
                Log.i("failure", t.getMessage());
            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
