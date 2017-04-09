package cn.fdzk.uidscanner.uitl;

import android.content.Context;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.concurrent.TimeUnit;

import cn.fdzk.uidscanner.common.JavaNetCookieJar;
import cn.fdzk.uidscanner.common.PersistentCookieStore;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by konie on 2017/4/8.
 */

public class ServiceUtil {

    private static final String API_BASE_PATH = "http://183.129.241.36:5536";


    private static CookieStore cookieStore;
    private static CookieManager cookieManager;
    private static OkHttpClient okHttpClient;

    private static Retrofit retrofitInstance;

    public static void init(Context context) {

        cookieStore = new PersistentCookieStore(context);
        cookieManager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);

        int cacheSize = 5 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        okhttp3.OkHttpClient.Builder okHttpClientBuilder= new okhttp3.OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(30, TimeUnit.SECONDS)
                .cookieJar(new JavaNetCookieJar(cookieManager));

        okHttpClient = okHttpClientBuilder.build();

        retrofitInstance = new Retrofit.Builder()
                .baseUrl(API_BASE_PATH)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public static <T> T createRestService(Class<T> service) {
        retrofitInstance = new Retrofit.Builder()
                .baseUrl(API_BASE_PATH)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return retrofitInstance.create(service);
    }

    public static int parseResponseForCode(String response) {
        if (response == null) {
            return -1;
        }

        int splitPos = response.indexOf('|');

        if (splitPos < 0) {
            return -1;
        }

        int code = -1;

        try {
            String codeStr = response.substring(0, splitPos);
            code = Integer.parseInt(codeStr);
        } catch (NumberFormatException e) {
            code = -1;
        }

        return code;
    }

    public static <T> T parseResponseForResult(String response, Class<T> clazz) {
        if (response == null || response.length() < 3) {
            return null;
        }

        int code = parseResponseForCode(response);

        if (code != 1) {
            response = response.substring(2);
            response = response.substring(0, response.length() - 1);
        } else {
            response = response.substring(3);
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<T> adapter = moshi.adapter(clazz);

        T result = null;

        if (clazz.equals(String.class)) {
            return (T) response;
        }

        try {
            result = adapter.fromJson(response);
        } catch (IOException e) {
            LogUtil.logError("ServiceUtil", e);
        }

        return result;
    }

}
