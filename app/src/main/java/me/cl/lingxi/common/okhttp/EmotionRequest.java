package me.cl.lingxi.common.okhttp;

import android.util.Log;

import me.cl.lingxi.common.config.Api;
import okhttp3.*;

import java.io.IOException;

public class EmotionRequest {

    private static final String URL = Api.positive;
    public static void sendInfoToBackend(String info) {
        OkHttpClient client = new OkHttpClient();
        // 构建请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("mInfo", info)
                .build();
        // 构建请求
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        // 发送请求并处理响应
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                // 如果需要处理响应数据，可以在这里进行操作
                String responseData = response.body().string();
                 Log.d("Response from server: ", responseData);
            }
        });
    }
}
