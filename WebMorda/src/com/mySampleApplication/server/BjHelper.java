package com.mySampleApplication.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BjHelper {

    public static String getMySumm(JSONObject data) {
        //data example - "newDealerHand":[[9,"s",9],["?","?",9]]

        JSONArray dealerCard = (JSONArray)data.get("newDealerHand");

        JSONArray dealerFirstCard = (JSONArray)dealerCard.get(1);

        return (String) dealerFirstCard.get(3);
    }

    public static String getDealerHand(JSONObject data) {
        //data example - "newDealerHand":[[9,"s",9],["?","?",9]]

        JSONArray dealerCard = (JSONArray)data.get("newDealerHand");

        JSONArray dealerFirstCard = (JSONArray)dealerCard.get(0);

        if ("a".equalsIgnoreCase((String) dealerFirstCard.get(0)))
            return "A";
        else return (String) dealerFirstCard.get(3);
    }

    public static String getMyHand(JSONObject data) {
        //data example - "newRightHand":[[5,"c",5],["j","c",15]]

        JSONArray myCard = (JSONArray)data.get("newRightHand");

        JSONArray myFirstCard = (JSONArray)myCard.get(0);
        JSONArray mySecondCard = (JSONArray)myCard.get(1);

        if (!"a".equalsIgnoreCase((String) myFirstCard.get(0)) && !"a".equalsIgnoreCase((String) mySecondCard.get(0)))
            return (String)mySecondCard.get(2);
        else
        if ("a".equalsIgnoreCase((String) myFirstCard.get(0)))
            return "A" + ((Integer)mySecondCard.get(2) - 10);
        else return "A" + myFirstCard.get(0);

    }
}
