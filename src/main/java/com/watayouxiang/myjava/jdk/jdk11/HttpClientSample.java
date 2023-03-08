package com.watayouxiang.myjava.jdk.jdk11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HttpClientSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //1.实例化HttpClient客户端
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build().newHttpClient();
        //2.构建请求对象
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.imooc.com")).GET().build();
        //3.发送请求,处理响应
        CompletableFuture<HttpResponse<String>> asyncResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String body = asyncResponse.thenApply(response -> response.body()).get(5, TimeUnit.SECONDS);
        System.out.println(body);
    }
}
