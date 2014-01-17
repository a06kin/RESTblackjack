package me.aaa.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TEST2 {

    private enum Bet {
        TEN("10"),
        TWENTY("20"),
        THIRTY("30");

        public String value;

        private Bet(String value) {
            this.value = value;
        }

        private static final List<Bet> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Bet random()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public static void main(String[] args){



        String s = "{\"status\":\"ok\",\"state\":10,\"rightBet\":10,\"newRightHand\":[[5,\"c\",5],[\"j\",\"c\",15]],\"newDealerHand\":[[9,\"s\",9],[\"?\",\"?\",9]],\"blackjack\":false,\"secure\":\"\",\"split\":\"\",\"double\":null,\"chipStart\":\"3168\",\"allowed\":null,\"newkey\":\"182e905b7e20553f80606e6babb21473\",\"oldkey\":\"a4c97ce1752062fa74dd881f0a8cd997\",\"session\":{\"rightBet\":10,\"leftBet\":0,\"secureBet\":0,\"rightHand\":[[5,\"c\"],[\"j\",\"c\"]],\"leftHand\":[],\"dealerHand\":[[9,\"s\"]],\"gameState\":10},\"sessionstart\":{\"rightBet\":null,\"leftBet\":null,\"secureBet\":null,\"rightHand\":null,\"leftHand\":null,\"dealerHand\":null,\"gameState\":null}}";
        JSONParser parser=new JSONParser();
        try {
            JSONObject test = (JSONObject)parser.parse(s);

            JSONArray myCard = (JSONArray)test.get("newDealerHand");


            System.out.println(myCard);

            System.out.println(Bet.random().value);
            System.out.println(Bet.random().value);
            System.out.println(Bet.random().value);
            System.out.println(Bet.random().value);
            System.out.println(Bet.random().value);
            System.out.println(Bet.random().value);



            System.out.println(Bet.random());
            System.out.println(Bet.random());
            System.out.println(Bet.random());
            System.out.println(Bet.random());
            System.out.println(Bet.random());
            System.out.println(Bet.random());




        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
