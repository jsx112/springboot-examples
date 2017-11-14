package com.springboot.resttemplate;/**
 * Created by dell on 2017/11/3.
 */

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

/**
 * Okhttp测试
 *
 * @author jiasx
 * @create 2017-11-03 18:11
 **/
public class OkhttpTest {

    @Test
    public void test() {
        String url = "https://127.0.0.1:8082/contract/new";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
