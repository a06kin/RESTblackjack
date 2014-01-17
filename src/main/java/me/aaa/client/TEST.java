package me.aaa.client;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TEST {

    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpGet getIndex = new HttpGet("http://localhost:7777/action/getID");

        CloseableHttpResponse indexResponse = client.execute(getIndex);

        try {
            System.out.println("indexResponse");
            System.out.println(indexResponse.getStatusLine());
            System.out.println(indexResponse.toString());
        } finally {
            indexResponse.close();
        }

        Thread.sleep(2000);

        List<NameValuePair> indexFormParam = new ArrayList<NameValuePair>();
//        action=login&email=sensej_sanjok%40inbox.lv&password=%601&remember=on
        indexFormParam.add(new BasicNameValuePair("ID", "123"));
        indexFormParam.add(new BasicNameValuePair("hand", "1"));
        indexFormParam.add(new BasicNameValuePair("player", "TT"));
        indexFormParam.add(new BasicNameValuePair("dealer", "2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(indexFormParam, Consts.UTF_8);
        HttpPost postIndex = new HttpPost("http://localhost:7777/action/getTurn");
        postIndex.setEntity(entity);

        CloseableHttpResponse postIndexResponse = client.execute(postIndex);

        try {
            System.out.println("postIndexResponse");
            System.out.println(postIndexResponse.getStatusLine());
            System.out.println(postIndexResponse.toString());
        } finally {
            postIndexResponse.close();
        }

        Thread.sleep(2000);

        HttpGet getPlayer = new HttpGet("http://localhost:7777/action/getID");

        CloseableHttpResponse getPlayerResponse = client.execute(getPlayer);
        try{
            System.out.println("getPlayerResponse");
            System.out.println(getPlayerResponse.getStatusLine());
            System.out.println(getPlayerResponse.toString());
        } finally {
            getPlayerResponse.close();
        }

        Thread.sleep(2000);

        HttpGet getBlackJackIndex = new HttpGet("http://localhost:7777/action/getID");

        CloseableHttpResponse BlackJackIndexResponse = client.execute(getBlackJackIndex);

        try{
            System.out.println("BlackJackIndexResponse");
            System.out.println(BlackJackIndexResponse.getStatusLine());
            System.out.println(BlackJackIndexResponse.toString());
        } finally {
            getPlayerResponse.close();
        }

        client.getConnectionManager().shutdown();
    }
}
