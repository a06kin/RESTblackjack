package com.mySampleApplication.server;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BjBot {

    private CloseableHttpClient client = null;
    private JSONObject data;

    private enum Bet {
        TEN("10"),
        TWENTY("20"),
        THIRTY("30");

        public String value;

        private Bet(String value) {
            this.value = value;
        }

        private static final List<Bet> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Bet random()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public BjBot() throws IOException {
        client = HttpClients.createDefault();
    }

    public void login(String username, String password) throws IOException {

        List<NameValuePair> indexFormParam = new ArrayList<>();
        indexFormParam.add(new BasicNameValuePair("action", "login"));
        indexFormParam.add(new BasicNameValuePair("email", username));
        indexFormParam.add(new BasicNameValuePair("password", password));
        indexFormParam.add(new BasicNameValuePair("remember", "off"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(indexFormParam, Consts.UTF_8);
        HttpPost postIndex = new HttpPost("http://www.moswar.ru");
        postIndex.setEntity(entity);

        CloseableHttpResponse postIndexResponse = client.execute(postIndex);

        System.out.println(postIndexResponse.toString());

        postIndexResponse.close();
    }

    public JSONObject newBet(Bet betSize) throws IOException {

        StringEntity entity = new StringEntity("action=new&bet=" + betSize.value);
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postBet = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postBet.setEntity(entity);

        return postData(postBet);
    }

    public JSONObject stand() throws IOException {

        StringEntity entity = new StringEntity("action=stop");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postStand = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postStand.setEntity(entity);

        return postData(postStand);
    }

    public JSONObject more() throws IOException {

        StringEntity entity = new StringEntity("action=more");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postMore = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postMore.setEntity(entity);

        return postData(postMore);

    }

    public JSONObject doubleBet() throws IOException {

        StringEntity entity = new StringEntity("action=double");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postDouble = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postDouble.setEntity(entity);

        return postData(postDouble);
    }

    private JSONObject postData(HttpPost post) throws IOException {
        CloseableHttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line;
        JSONObject rez = null;
        JSONParser parser = new JSONParser();
        if((line = rd.readLine()) != null){
            System.out.println(line);
            try {
                rez = (JSONObject)parser.parse(line);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        System.out.println(response.toString());

        response.close();

        return rez;

    }


}
