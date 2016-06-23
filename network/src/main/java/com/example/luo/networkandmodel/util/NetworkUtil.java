package com.example.luo.networkandmodel.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by H_P on 2016/4/25.
 * @author luo
 * @version 1.0
 */
public class NetworkUtil {
    // method for checking whether the network is avalibale
    public static boolean isNetworkAvaliable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnectedOrConnecting();
    }
}
