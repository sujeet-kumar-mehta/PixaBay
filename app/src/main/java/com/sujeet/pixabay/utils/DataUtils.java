package com.sujeet.pixabay.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class DataUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static void checkNetworkCallAndDeside(Context context) {
        if (!DataUtils.isNetworkAvailable(context)) {
            Toast.makeText(context, "No Network Available!", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
