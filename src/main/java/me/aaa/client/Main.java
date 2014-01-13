package me.aaa.client;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClients.createDefault();
//        HttpGet getIndex = new HttpGet("http://www.moswar.ru/");
//
//        HttpResponse indexResponse = client.execute(getIndex);
//
//        System.out.println(indexResponse.getStatusLine());
//        System.out.println(indexResponse.toString());
//
//        Thread.sleep(2000);

        List<NameValuePair> indexFormParam = new ArrayList<NameValuePair>();
//        action=login&email=sensej_sanjok%40inbox.lv&password=%601&remember=on
        indexFormParam.add(new BasicNameValuePair("action", "login"));
        indexFormParam.add(new BasicNameValuePair("email", "sensej_sanjok@inbox.lv"));
        indexFormParam.add(new BasicNameValuePair("password", "`1"));
        indexFormParam.add(new BasicNameValuePair("remember", "on"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(indexFormParam, Consts.UTF_8);
        HttpPost postIndex = new HttpPost("http://www.moswar.ru");
        postIndex.setEntity(entity);

        HttpResponse postIndexResponse = client.execute(postIndex);

        System.out.println(postIndexResponse.getStatusLine());
        System.out.println(postIndexResponse.toString());

        Thread.sleep(2000);

        HttpGet getPlayer = new HttpGet("http://www.moswar.ru/player");

        HttpResponse getPlayerResponse = client.execute(getPlayer);

        System.out.println(getPlayerResponse.getStatusLine());
        System.out.println(getPlayerResponse.toString());

        Thread.sleep(2000);

        HttpGet getBlackJackIndex = new HttpGet("http://www.moswar.ru/casino/blackjack/");

        HttpResponse BlackJackIndexResponse = client.execute(getBlackJackIndex);

        System.out.println(BlackJackIndexResponse.getStatusLine());
        System.out.println(BlackJackIndexResponse.toString());

        client.getConnectionManager().shutdown();
    }
}
