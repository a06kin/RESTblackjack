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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static CloseableHttpClient client = HttpClients.createDefault();

    private enum Bet {
        TEN("10"),
        TWENTY("20"),
        THIRTY("30");

        public String value;

        private Bet(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//        HttpGet getIndex = new HttpGet("http://www.moswar.ru/");
//
//        HttpResponse indexResponse = client.execute(getIndex);
//
//        System.out.println(indexResponse.getStatusLine());
//        System.out.println(indexResponse.toString());
//
//        Thread.sleep(2000);

        login("sensej_sanjok@inbox.lv","`1");
        Thread.sleep(2000);
        index();
        Thread.sleep(2000);
        blackJackIndex();
        Thread.sleep(2000);
        newBet(Bet.TEN);
        client.getConnectionManager().shutdown();
    }

    private static void blackJackIndex() throws IOException {
        HttpGet getBlackJackIndex = new HttpGet("http://www.moswar.ru/casino/blackjack/");

        CloseableHttpResponse BlackJackIndexResponse = client.execute(getBlackJackIndex);

        System.out.println(BlackJackIndexResponse.toString());

        BlackJackIndexResponse.close();
    }

    private static void index() throws IOException {
        HttpGet getPlayer = new HttpGet("http://www.moswar.ru/player");

        CloseableHttpResponse getPlayerResponse = client.execute(getPlayer);

        System.out.println(getPlayerResponse.toString());

        getPlayerResponse.close();

    }

    private static void login(String username, String password) throws IOException {

        List<NameValuePair> indexFormParam = new ArrayList<NameValuePair>();
        indexFormParam.add(new BasicNameValuePair("action", "login"));
        indexFormParam.add(new BasicNameValuePair("email", "sensej_sanjok@inbox.lv"));
        indexFormParam.add(new BasicNameValuePair("password", "`1"));
        indexFormParam.add(new BasicNameValuePair("remember", "off"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(indexFormParam, Consts.UTF_8);
        HttpPost postIndex = new HttpPost("http://www.moswar.ru");
        postIndex.setEntity(entity);

        CloseableHttpResponse postIndexResponse = client.execute(postIndex);

        System.out.println(postIndexResponse.toString());

        postIndexResponse.close();
    }

    //TODO:add json
    private static void newBet(Bet betSize) throws IOException {
        List<NameValuePair> betFormParam = new ArrayList<NameValuePair>();
        betFormParam.add(new BasicNameValuePair("action", "new"));
        betFormParam.add(new BasicNameValuePair("bet", betSize.value));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(betFormParam, Consts.UTF_8);
        HttpPost postBet = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postBet.setEntity(entity);

        CloseableHttpResponse postBetResponse = client.execute(postBet);

        BufferedReader rd = new BufferedReader(new InputStreamReader(postBetResponse.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            JSONParser j = new JSONParser();
            JSONObject o = (JSONObject)j.parse(line);
            Map response = (Map)o.get("response");

            System.out.println(response.get("somevalue"));
        }

        System.out.println(postBetResponse.toString());

        postBetResponse.close();

    }
}
