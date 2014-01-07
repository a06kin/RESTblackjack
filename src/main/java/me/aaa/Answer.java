package me.aaa;

import org.glassfish.grizzly.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class Answer {

    private static Map<Pair<String,String>, String> data = new HashMap<Pair<String,String> , String>() {{
        //6 decks, S17, D10, No DAS, No Surrender, Peek

        //{My,D}, {A}

        /*  H   = Hit
            S   = Stand
            P   = Split
            D   = Double (Hit if not allowed)
        */
        put(new Pair<>("5","2" ), "H");       put(new Pair<>("6","2" ), "H");       put(new Pair<>("7","2" ), "H");
        put(new Pair<>("5","3" ), "H");       put(new Pair<>("6","3" ), "H");       put(new Pair<>("7","3" ), "H");
        put(new Pair<>("5","4" ), "H");       put(new Pair<>("6","4" ), "H");       put(new Pair<>("7","4" ), "H");
        put(new Pair<>("5","5" ), "H");       put(new Pair<>("6","5" ), "H");       put(new Pair<>("7","5" ), "H");
        put(new Pair<>("5","6" ), "H");       put(new Pair<>("6","6" ), "H");       put(new Pair<>("7","6" ), "H");
        put(new Pair<>("5","7" ), "H");       put(new Pair<>("6","7" ), "H");       put(new Pair<>("7","7" ), "H");
        put(new Pair<>("5","8" ), "H");       put(new Pair<>("6","8" ), "H");       put(new Pair<>("7","8" ), "H");
        put(new Pair<>("5","9" ), "H");       put(new Pair<>("6","9" ), "H");       put(new Pair<>("7","9" ), "H");
        put(new Pair<>("5","10"), "H");       put(new Pair<>("6","10"), "H");       put(new Pair<>("7","10"), "H");
        put(new Pair<>("5","A" ), "H");       put(new Pair<>("6","A" ), "H");       put(new Pair<>("7","A" ), "H");

        put(new Pair<>("8","2" ), "H");       put(new Pair<>("9","2" ), "H");       put(new Pair<>("10","2" ), "D");
        put(new Pair<>("8","3" ), "H");       put(new Pair<>("9","3" ), "H");       put(new Pair<>("10","3" ), "D");
        put(new Pair<>("8","4" ), "H");       put(new Pair<>("9","4" ), "H");       put(new Pair<>("10","4" ), "D");
        put(new Pair<>("8","5" ), "H");       put(new Pair<>("9","5" ), "H");       put(new Pair<>("10","5" ), "D");
        put(new Pair<>("8","6" ), "H");       put(new Pair<>("9","6" ), "H");       put(new Pair<>("10","6" ), "D");
        put(new Pair<>("8","7" ), "H");       put(new Pair<>("9","7" ), "H");       put(new Pair<>("10","7" ), "D");
        put(new Pair<>("8","8" ), "H");       put(new Pair<>("9","8" ), "H");       put(new Pair<>("10","8" ), "D");
        put(new Pair<>("8","9" ), "H");       put(new Pair<>("9","9" ), "H");       put(new Pair<>("10","9" ), "D");
        put(new Pair<>("8","10"), "H");       put(new Pair<>("9","10"), "H");       put(new Pair<>("10","10"), "H");
        put(new Pair<>("8","A" ), "H");       put(new Pair<>("9","A" ), "H");       put(new Pair<>("10","A" ), "H");

        put(new Pair<>("11","2" ), "D");      put(new Pair<>("12","2" ), "H");      put(new Pair<>("13","2" ), "S");
        put(new Pair<>("11","3" ), "D");      put(new Pair<>("12","3" ), "H");      put(new Pair<>("13","3" ), "S");
        put(new Pair<>("11","4" ), "D");      put(new Pair<>("12","4" ), "S");      put(new Pair<>("13","4" ), "S");
        put(new Pair<>("11","5" ), "D");      put(new Pair<>("12","5" ), "S");      put(new Pair<>("13","5" ), "S");
        put(new Pair<>("11","6" ), "D");      put(new Pair<>("12","6" ), "S");      put(new Pair<>("13","6" ), "S");
        put(new Pair<>("11","7" ), "D");      put(new Pair<>("12","7" ), "H");      put(new Pair<>("13","7" ), "H");
        put(new Pair<>("11","8" ), "D");      put(new Pair<>("12","8" ), "H");      put(new Pair<>("13","8" ), "H");
        put(new Pair<>("11","9" ), "D");      put(new Pair<>("12","9" ), "H");      put(new Pair<>("13","9" ), "H");
        put(new Pair<>("11","10"), "D");      put(new Pair<>("12","10"), "H");      put(new Pair<>("13","10"), "H");
        put(new Pair<>("11","A" ), "H");      put(new Pair<>("12","A" ), "H");      put(new Pair<>("13","A" ), "H");

        put(new Pair<>("14","2" ), "S");      put(new Pair<>("15","2" ), "S");      put(new Pair<>("16","2" ), "S");
        put(new Pair<>("14","3" ), "S");      put(new Pair<>("15","3" ), "S");      put(new Pair<>("16","3" ), "S");
        put(new Pair<>("14","4" ), "S");      put(new Pair<>("15","4" ), "S");      put(new Pair<>("16","4" ), "S");
        put(new Pair<>("14","5" ), "S");      put(new Pair<>("15","5" ), "S");      put(new Pair<>("16","5" ), "S");
        put(new Pair<>("14","6" ), "S");      put(new Pair<>("15","6" ), "S");      put(new Pair<>("16","6" ), "S");
        put(new Pair<>("14","7" ), "H");      put(new Pair<>("15","7" ), "H");      put(new Pair<>("16","7" ), "H");
        put(new Pair<>("14","8" ), "H");      put(new Pair<>("15","8" ), "H");      put(new Pair<>("16","8" ), "H");
        put(new Pair<>("14","9" ), "H");      put(new Pair<>("15","9" ), "H");      put(new Pair<>("16","9" ), "H");
        put(new Pair<>("14","10"), "H");      put(new Pair<>("15","10"), "H");      put(new Pair<>("16","10"), "H");
        put(new Pair<>("14","A" ), "H");      put(new Pair<>("15","A" ), "H");      put(new Pair<>("16","A" ), "H");

        put(new Pair<>("17","2" ), "S");  
        put(new Pair<>("17","3" ), "S");  
        put(new Pair<>("17","4" ), "S");  
        put(new Pair<>("17","5" ), "S");  
        put(new Pair<>("17","6" ), "S");  
        put(new Pair<>("17","7" ), "S");  
        put(new Pair<>("17","8" ), "S");  
        put(new Pair<>("17","9" ), "S");  
        put(new Pair<>("17","10"), "S"); 
        put(new Pair<>("17","A" ), "S");

        put(new Pair<>("A2","2" ), "H");      put(new Pair<>("A3","2" ), "H");      put(new Pair<>("A4","2" ), "H");
        put(new Pair<>("A2","3" ), "H");      put(new Pair<>("A3","3" ), "H");      put(new Pair<>("A4","3" ), "H");
        put(new Pair<>("A2","4" ), "H");      put(new Pair<>("A3","4" ), "H");      put(new Pair<>("A4","4" ), "H");
        put(new Pair<>("A2","5" ), "H");      put(new Pair<>("A3","5" ), "H");      put(new Pair<>("A4","5" ), "H");
        put(new Pair<>("A2","6" ), "H");      put(new Pair<>("A3","6" ), "H");      put(new Pair<>("A4","6" ), "H");
        put(new Pair<>("A2","7" ), "H");      put(new Pair<>("A3","7" ), "H");      put(new Pair<>("A4","7" ), "H");
        put(new Pair<>("A2","8" ), "H");      put(new Pair<>("A3","8" ), "H");      put(new Pair<>("A4","8" ), "H");
        put(new Pair<>("A2","9" ), "H");      put(new Pair<>("A3","9" ), "H");      put(new Pair<>("A4","9" ), "H");
        put(new Pair<>("A2","10"), "H");      put(new Pair<>("A3","10"), "H");      put(new Pair<>("A4","10"), "H");
        put(new Pair<>("A2","A" ), "H");      put(new Pair<>("A3","A" ), "H");      put(new Pair<>("A4","A" ), "H");

        put(new Pair<>("A5","2" ), "H");      put(new Pair<>("A6","2" ), "H");      put(new Pair<>("A7","2" ), "S");
        put(new Pair<>("A5","3" ), "H");      put(new Pair<>("A6","3" ), "H");      put(new Pair<>("A7","3" ), "S");
        put(new Pair<>("A5","4" ), "H");      put(new Pair<>("A6","4" ), "H");      put(new Pair<>("A7","4" ), "S");
        put(new Pair<>("A5","5" ), "H");      put(new Pair<>("A6","5" ), "H");      put(new Pair<>("A7","5" ), "S");
        put(new Pair<>("A5","6" ), "H");      put(new Pair<>("A6","6" ), "H");      put(new Pair<>("A7","6" ), "S");
        put(new Pair<>("A5","7" ), "H");      put(new Pair<>("A6","7" ), "H");      put(new Pair<>("A7","7" ), "S");
        put(new Pair<>("A5","8" ), "H");      put(new Pair<>("A6","8" ), "H");      put(new Pair<>("A7","8" ), "S");
        put(new Pair<>("A5","9" ), "H");      put(new Pair<>("A6","9" ), "H");      put(new Pair<>("A7","9" ), "H");
        put(new Pair<>("A5","10"), "H");      put(new Pair<>("A6","10"), "H");      put(new Pair<>("A7","10"), "H");
        put(new Pair<>("A5","A" ), "H");      put(new Pair<>("A6","A" ), "H");      put(new Pair<>("A7","A" ), "H");

        put(new Pair<>("A8","2" ), "S");      put(new Pair<>("A9","2" ), "S");  
        put(new Pair<>("A8","3" ), "S");      put(new Pair<>("A9","3" ), "S");  
        put(new Pair<>("A8","4" ), "S");      put(new Pair<>("A9","4" ), "S");  
        put(new Pair<>("A8","5" ), "S");      put(new Pair<>("A9","5" ), "S");  
        put(new Pair<>("A8","6" ), "S");      put(new Pair<>("A9","6" ), "S");  
        put(new Pair<>("A8","7" ), "S");      put(new Pair<>("A9","7" ), "S");  
        put(new Pair<>("A8","8" ), "S");      put(new Pair<>("A9","8" ), "S");  
        put(new Pair<>("A8","9" ), "S");      put(new Pair<>("A9","9" ), "S");  
        put(new Pair<>("A8","10"), "S");      put(new Pair<>("A9","10"), "S"); 
        put(new Pair<>("A8","A" ), "S");      put(new Pair<>("A9","A" ), "S"); 

        put(new Pair<>("22","2" ), "H");      put(new Pair<>("33","2" ), "H");      put(new Pair<>("44","2" ), "H");
        put(new Pair<>("22","3" ), "H");      put(new Pair<>("33","3" ), "H");      put(new Pair<>("44","3" ), "H");
        put(new Pair<>("22","4" ), "P");      put(new Pair<>("33","4" ), "P");      put(new Pair<>("44","4" ), "H");
        put(new Pair<>("22","5" ), "P");      put(new Pair<>("33","5" ), "P");      put(new Pair<>("44","5" ), "H");
        put(new Pair<>("22","6" ), "P");      put(new Pair<>("33","6" ), "P");      put(new Pair<>("44","6" ), "H");
        put(new Pair<>("22","7" ), "P");      put(new Pair<>("33","7" ), "P");      put(new Pair<>("44","7" ), "H");
        put(new Pair<>("22","8" ), "H");      put(new Pair<>("33","8" ), "H");      put(new Pair<>("44","8" ), "H");
        put(new Pair<>("22","9" ), "H");      put(new Pair<>("33","9" ), "H");      put(new Pair<>("44","9" ), "H");
        put(new Pair<>("22","10"), "H");      put(new Pair<>("33","10"), "H");      put(new Pair<>("44","10"), "H");
        put(new Pair<>("22","A" ), "H");      put(new Pair<>("33","A" ), "H");      put(new Pair<>("44","A" ), "H");

        put(new Pair<>("55","2" ), "D");      put(new Pair<>("66","2" ), "H");      put(new Pair<>("77","2" ), "P");
        put(new Pair<>("55","3" ), "D");      put(new Pair<>("66","3" ), "P");      put(new Pair<>("77","3" ), "P");
        put(new Pair<>("55","4" ), "D");      put(new Pair<>("66","4" ), "P");      put(new Pair<>("77","4" ), "P");
        put(new Pair<>("55","5" ), "D");      put(new Pair<>("66","5" ), "P");      put(new Pair<>("77","5" ), "P");
        put(new Pair<>("55","6" ), "D");      put(new Pair<>("66","6" ), "P");      put(new Pair<>("77","6" ), "P");
        put(new Pair<>("55","7" ), "D");      put(new Pair<>("66","7" ), "H");      put(new Pair<>("77","7" ), "P");
        put(new Pair<>("55","8" ), "D");      put(new Pair<>("66","8" ), "H");      put(new Pair<>("77","8" ), "H");
        put(new Pair<>("55","9" ), "D");      put(new Pair<>("66","9" ), "H");      put(new Pair<>("77","9" ), "H");
        put(new Pair<>("55","10"), "H");      put(new Pair<>("66","10"), "H");      put(new Pair<>("77","10"), "H");
        put(new Pair<>("55","A" ), "H");      put(new Pair<>("66","A" ), "H");      put(new Pair<>("77","A" ), "H");

        put(new Pair<>("88","2" ), "P");      put(new Pair<>("99","2" ), "P");
        put(new Pair<>("88","3" ), "P");      put(new Pair<>("99","3" ), "P");  
        put(new Pair<>("88","4" ), "P");      put(new Pair<>("99","4" ), "P");  
        put(new Pair<>("88","5" ), "P");      put(new Pair<>("99","5" ), "P");  
        put(new Pair<>("88","6" ), "P");      put(new Pair<>("99","6" ), "P");  
        put(new Pair<>("88","7" ), "P");      put(new Pair<>("99","7" ), "S");  
        put(new Pair<>("88","8" ), "P");      put(new Pair<>("99","8" ), "P");  
        put(new Pair<>("88","9" ), "P");      put(new Pair<>("99","9" ), "P");  
        put(new Pair<>("88","10"), "P");      put(new Pair<>("99","10"), "S");
        put(new Pair<>("88","A" ), "P");      put(new Pair<>("99","A" ), "S");

        put(new Pair<>("TT","2" ), "S");      put(new Pair<>("AA","2" ), "P");
        put(new Pair<>("TT","3" ), "S");      put(new Pair<>("AA","3" ), "P");  
        put(new Pair<>("TT","4" ), "S");      put(new Pair<>("AA","4" ), "P");  
        put(new Pair<>("TT","5" ), "S");      put(new Pair<>("AA","5" ), "P");  
        put(new Pair<>("TT","6" ), "S");      put(new Pair<>("AA","6" ), "P");  
        put(new Pair<>("TT","7" ), "S");      put(new Pair<>("AA","7" ), "P");  
        put(new Pair<>("TT","8" ), "S");      put(new Pair<>("AA","8" ), "P");  
        put(new Pair<>("TT","9" ), "S");      put(new Pair<>("AA","9" ), "P");  
        put(new Pair<>("TT","10"), "S");      put(new Pair<>("AA","10"), "P"); 
        put(new Pair<>("TT","A" ), "S");      put(new Pair<>("AA","A" ), "P"); 

    }};

    public static String getTurn(String player, String dealerUpcard){
        return data.get(new Pair<>(player, dealerUpcard));
    }
}
