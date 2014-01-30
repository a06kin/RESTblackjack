package me.aaa.client;

import me.aaa.server.Answer;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        private static final List<Bet> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Bet random()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
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

        JSONObject data;

        login("sensej_sanjok@inbox.lv","`1");
        Thread.sleep(2000);
        index();
        Thread.sleep(2000);
        blackJackIndex();
        Thread.sleep(2000);
        data = newBet(Bet.TEN);
        Thread.sleep(2000);

        String mySumm = null;
        String myHand = getMyHand(data);
        if (!myHand.contains("A"))
            mySumm = getMySumm(data);
        String dealerHand = getDealerHand(data);

        int i = 0;

        all:
        while(++i < 10){
            if (mySumm != null && Integer.parseInt(mySumm) > 17){
                endGame(stand());
                data = newBet(Bet.TEN);
                myHand = getMyHand(data);
                mySumm = null;
                if (!myHand.contains("A"))
                    mySumm = getMySumm(data);
                dealerHand = getDealerHand(data);
            }
            else{
                switch (Answer.getTurn(myHand, dealerHand)){
                    case "S": //STAND
                        endGame(stand());
                        Thread.sleep(2000);
//                        break all;
                        data = newBet(Bet.TEN);
                        myHand = getMyHand(data);
                        if (!myHand.contains("A"))
                            mySumm = getMySumm(data);
                        dealerHand = getDealerHand(data);
                        break;
                    case "H": //HIT
                        data = more();
                        mySumm = getMyNextSumm(data);
                        myHand = mySumm;
                        break;
                    case "D": //DOUBLE
                        data = doubleBet();
                        mySumm = getMyNextSumm(data);
                        myHand = mySumm;
                        break;
                    case "P": //SPLIT
                        data = more();
                        mySumm = getMyNextSumm(data);
                        myHand = mySumm;
                        break;
                    default:
                        break;
                }

            }
        }

        client.getConnectionManager().shutdown();
    }

    private static String getMySumm(JSONObject data) {
        //data example - "newRightHand":[[5,"c",5],["j","c",15]]

        JSONArray dealerCard = (JSONArray)data.get("newRightHand");

        JSONArray dealerFirstCard = (JSONArray)dealerCard.get(1);

        return dealerFirstCard.get(2).toString();
    }

    private static String getMyNextSumm(JSONObject data) {
        //data example - "card":[[5,"s",17]]

        JSONArray myNewCard = (JSONArray)data.get("card");

        JSONArray mySumm = (JSONArray)myNewCard.get(0);

        return mySumm.get(2).toString();
    }

    private static String getMyNextHand(JSONObject data) {
        //data example - "card":[[5,"s",17]]

        JSONArray myNewCard = (JSONArray)data.get("card");

        JSONArray mySumm = (JSONArray)myNewCard.get(0);

        try
        {
            Integer test = Integer.parseInt(mySumm.get(0).toString());
            //if j,q,k - return 10
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("No num:" + mySumm.get(0).toString());
            return "10";
        }

        return mySumm.get(0).toString();
    }



    private static void endGame(JSONObject stand) {
        //TODO: save result
        System.out.println(stand.toString());
    }

    private static String getDealerHand(JSONObject data) {
        //data example - "newDealerHand":[[9,"s",9],["?","?",9]]

        JSONArray dealerCard = (JSONArray)data.get("newDealerHand");

        JSONArray dealerFirstCard = (JSONArray)dealerCard.get(0);

        if ("a".equalsIgnoreCase(dealerFirstCard.get(0).toString()))
            return "A";
        else return dealerFirstCard.get(2).toString();
    }

    private static String getMyHand(JSONObject data) {
        //data example - "newRightHand":[[5,"c",5],["j","c",15]]

        JSONArray myCard = (JSONArray)data.get("newRightHand");

        JSONArray myFirstCard = (JSONArray)myCard.get(0);
        JSONArray mySecondCard = (JSONArray)myCard.get(1);

        if (!"a".equalsIgnoreCase(myFirstCard.get(0).toString()) && !"a".equalsIgnoreCase(mySecondCard.get(0).toString()))
            return mySecondCard.get(2).toString();
        else
            if ("a".equalsIgnoreCase(myFirstCard.get(0).toString()))
                return "A" + ((Integer)mySecondCard.get(2) - 10);
            else return "A" + myFirstCard.get(2);

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

    private static JSONObject newBet(Bet betSize) throws IOException {

        StringEntity entity = new StringEntity("action=new&bet=" + betSize.value);
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postBet = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postBet.setEntity(entity);

        return postData(postBet);
    }

    private static JSONObject stand() throws IOException {

        StringEntity entity = new StringEntity("action=stop");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postStand = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postStand.setEntity(entity);

        return postData(postStand);
    }

    private static JSONObject more() throws IOException {

        StringEntity entity = new StringEntity("action=more");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postMore = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postMore.setEntity(entity);

        return postData(postMore);

    }

    private static JSONObject doubleBet() throws IOException {

        StringEntity entity = new StringEntity("action=double");
        entity.setContentType("application/x-www-form-urlencoded");

        HttpPost postDouble = new HttpPost("http://www.moswar.ru/casino/blackjack/");
        postDouble.setEntity(entity);

        return postData(postDouble);
    }

    private static JSONObject postData(HttpPost post) throws IOException {
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
