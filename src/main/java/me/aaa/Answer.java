package me.aaa;

import org.glassfish.grizzly.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class Answer {

    private static Map<Pair<String,String>, String> data = new HashMap<Pair<String,String> , String>() {{
        //6 decks, S17, D10, No DAS, Early Surrender, Peek

        //{My,D}, {A}

        /*  H   = Hit   S   = Stand P   = Split
            D   = Double (Hit if not allowed)
            RH  = Surrender (Hit if not allowed)
            RS  = Surrender (Stand if not allowed)
            RP  = Surrender (Split if not allowed)
        */
        put(new Pair<String, String>("5","2" ), "H");       put(new Pair<String, String>("6","2" ), "H");       put(new Pair<String, String>("7","2" ), "H");
        put(new Pair<String, String>("5","3" ), "H");       put(new Pair<String, String>("6","3" ), "H");       put(new Pair<String, String>("7","3" ), "H");
        put(new Pair<String, String>("5","4" ), "H");       put(new Pair<String, String>("6","4" ), "H");       put(new Pair<String, String>("7","4" ), "H");
        put(new Pair<String, String>("5","5" ), "H");       put(new Pair<String, String>("6","5" ), "H");       put(new Pair<String, String>("7","5" ), "H");
        put(new Pair<String, String>("5","6" ), "H");       put(new Pair<String, String>("6","6" ), "H");       put(new Pair<String, String>("7","6" ), "H");
        put(new Pair<String, String>("5","7" ), "H");       put(new Pair<String, String>("6","7" ), "H");       put(new Pair<String, String>("7","7" ), "H");
        put(new Pair<String, String>("5","8" ), "H");       put(new Pair<String, String>("6","8" ), "H");       put(new Pair<String, String>("7","8" ), "H");
        put(new Pair<String, String>("5","9" ), "H");       put(new Pair<String, String>("6","9" ), "H");       put(new Pair<String, String>("7","9" ), "H");
        put(new Pair<String, String>("5","10"), "H");       put(new Pair<String, String>("6","10"), "H");       put(new Pair<String, String>("7","10"), "H");
        put(new Pair<String, String>("5","A" ), "RH");      put(new Pair<String, String>("6","A" ), "RH");      put(new Pair<String, String>("7","A" ), "RH");

        put(new Pair<String, String>("8","2" ), "H");       put(new Pair<String, String>("9","2" ), "H");       put(new Pair<String, String>("10","2" ), "D");
        put(new Pair<String, String>("8","3" ), "H");       put(new Pair<String, String>("9","3" ), "H");       put(new Pair<String, String>("10","3" ), "D");
        put(new Pair<String, String>("8","4" ), "H");       put(new Pair<String, String>("9","4" ), "H");       put(new Pair<String, String>("10","4" ), "D");
        put(new Pair<String, String>("8","5" ), "H");       put(new Pair<String, String>("9","5" ), "H");       put(new Pair<String, String>("10","5" ), "D");
        put(new Pair<String, String>("8","6" ), "H");       put(new Pair<String, String>("9","6" ), "H");       put(new Pair<String, String>("10","6" ), "D");
        put(new Pair<String, String>("8","7" ), "H");       put(new Pair<String, String>("9","7" ), "H");       put(new Pair<String, String>("10","7" ), "D");
        put(new Pair<String, String>("8","8" ), "H");       put(new Pair<String, String>("9","8" ), "H");       put(new Pair<String, String>("10","8" ), "D");
        put(new Pair<String, String>("8","9" ), "H");       put(new Pair<String, String>("9","9" ), "H");       put(new Pair<String, String>("10","9" ), "D");
        put(new Pair<String, String>("8","10"), "H");       put(new Pair<String, String>("9","10"), "H");       put(new Pair<String, String>("10","10"), "H");
        put(new Pair<String, String>("8","A" ), "H");       put(new Pair<String, String>("9","A" ), "H");       put(new Pair<String, String>("10","A" ), "H");

        put(new Pair<String, String>("11","2" ), "D");      put(new Pair<String, String>("12","2" ), "H");      put(new Pair<String, String>("13","2" ), "S");
        put(new Pair<String, String>("11","3" ), "D");      put(new Pair<String, String>("12","3" ), "H");      put(new Pair<String, String>("13","3" ), "S");
        put(new Pair<String, String>("11","4" ), "D");      put(new Pair<String, String>("12","4" ), "S");      put(new Pair<String, String>("13","4" ), "S");
        put(new Pair<String, String>("11","5" ), "D");      put(new Pair<String, String>("12","5" ), "S");      put(new Pair<String, String>("13","5" ), "S");
        put(new Pair<String, String>("11","6" ), "D");      put(new Pair<String, String>("12","6" ), "S");      put(new Pair<String, String>("13","6" ), "S");
        put(new Pair<String, String>("11","7" ), "D");      put(new Pair<String, String>("12","7" ), "H");      put(new Pair<String, String>("13","7" ), "H");
        put(new Pair<String, String>("11","8" ), "D");      put(new Pair<String, String>("12","8" ), "H");      put(new Pair<String, String>("13","8" ), "H");
        put(new Pair<String, String>("11","9" ), "D");      put(new Pair<String, String>("12","9" ), "H");      put(new Pair<String, String>("13","9" ), "H");
        put(new Pair<String, String>("11","10"), "D");      put(new Pair<String, String>("12","10"), "H");      put(new Pair<String, String>("13","10"), "H");
        put(new Pair<String, String>("11","A" ), "H");      put(new Pair<String, String>("12","A" ), "RH");     put(new Pair<String, String>("13","A" ), "RH");

        put(new Pair<String, String>("14","2" ), "S");      put(new Pair<String, String>("15","2" ), "S");      put(new Pair<String, String>("16","2" ), "S");
        put(new Pair<String, String>("14","3" ), "S");      put(new Pair<String, String>("15","3" ), "S");      put(new Pair<String, String>("16","3" ), "S");
        put(new Pair<String, String>("14","4" ), "S");      put(new Pair<String, String>("15","4" ), "S");      put(new Pair<String, String>("16","4" ), "S");
        put(new Pair<String, String>("14","5" ), "S");      put(new Pair<String, String>("15","5" ), "S");      put(new Pair<String, String>("16","5" ), "S");
        put(new Pair<String, String>("14","6" ), "S");      put(new Pair<String, String>("15","6" ), "S");      put(new Pair<String, String>("16","6" ), "S");
        put(new Pair<String, String>("14","7" ), "H");      put(new Pair<String, String>("15","7" ), "H");      put(new Pair<String, String>("16","7" ), "H");
        put(new Pair<String, String>("14","8" ), "H");      put(new Pair<String, String>("15","8" ), "H");      put(new Pair<String, String>("16","8" ), "H");
        put(new Pair<String, String>("14","9" ), "H");      put(new Pair<String, String>("15","9" ), "H");      put(new Pair<String, String>("16","9" ), "RH");
        put(new Pair<String, String>("14","10"), "RH");     put(new Pair<String, String>("15","10"), "RH");     put(new Pair<String, String>("16","10"), "RH");
        put(new Pair<String, String>("14","A" ), "RH");     put(new Pair<String, String>("15","A" ), "RH");     put(new Pair<String, String>("16","A" ), "RH");

        put(new Pair<String, String>("17","2" ), "S");  
        put(new Pair<String, String>("17","3" ), "S");  
        put(new Pair<String, String>("17","4" ), "S");  
        put(new Pair<String, String>("17","5" ), "S");  
        put(new Pair<String, String>("17","6" ), "S");  
        put(new Pair<String, String>("17","7" ), "S");  
        put(new Pair<String, String>("17","8" ), "S");  
        put(new Pair<String, String>("17","9" ), "S");  
        put(new Pair<String, String>("17","10"), "S"); 
        put(new Pair<String, String>("17","A" ), "RS"); 

        put(new Pair<String, String>("A2","2" ), "H");      put(new Pair<String, String>("A3","2" ), "H");      put(new Pair<String, String>("A4","2" ), "H");
        put(new Pair<String, String>("A2","3" ), "H");      put(new Pair<String, String>("A3","3" ), "H");      put(new Pair<String, String>("A4","3" ), "H");
        put(new Pair<String, String>("A2","4" ), "H");      put(new Pair<String, String>("A3","4" ), "H");      put(new Pair<String, String>("A4","4" ), "H");
        put(new Pair<String, String>("A2","5" ), "H");      put(new Pair<String, String>("A3","5" ), "H");      put(new Pair<String, String>("A4","5" ), "H");
        put(new Pair<String, String>("A2","6" ), "H");      put(new Pair<String, String>("A3","6" ), "H");      put(new Pair<String, String>("A4","6" ), "H");
        put(new Pair<String, String>("A2","7" ), "H");      put(new Pair<String, String>("A3","7" ), "H");      put(new Pair<String, String>("A4","7" ), "H");
        put(new Pair<String, String>("A2","8" ), "H");      put(new Pair<String, String>("A3","8" ), "H");      put(new Pair<String, String>("A4","8" ), "H");
        put(new Pair<String, String>("A2","9" ), "H");      put(new Pair<String, String>("A3","9" ), "H");      put(new Pair<String, String>("A4","9" ), "H");
        put(new Pair<String, String>("A2","10"), "H");      put(new Pair<String, String>("A3","10"), "H");      put(new Pair<String, String>("A4","10"), "H");
        put(new Pair<String, String>("A2","A" ), "H");      put(new Pair<String, String>("A3","A" ), "H");      put(new Pair<String, String>("A4","A" ), "H");

        put(new Pair<String, String>("A5","2" ), "H");      put(new Pair<String, String>("A6","2" ), "H");      put(new Pair<String, String>("A7","2" ), "S");
        put(new Pair<String, String>("A5","3" ), "H");      put(new Pair<String, String>("A6","3" ), "H");      put(new Pair<String, String>("A7","3" ), "S");
        put(new Pair<String, String>("A5","4" ), "H");      put(new Pair<String, String>("A6","4" ), "H");      put(new Pair<String, String>("A7","4" ), "S");
        put(new Pair<String, String>("A5","5" ), "H");      put(new Pair<String, String>("A6","5" ), "H");      put(new Pair<String, String>("A7","5" ), "S");
        put(new Pair<String, String>("A5","6" ), "H");      put(new Pair<String, String>("A6","6" ), "H");      put(new Pair<String, String>("A7","6" ), "S");
        put(new Pair<String, String>("A5","7" ), "H");      put(new Pair<String, String>("A6","7" ), "H");      put(new Pair<String, String>("A7","7" ), "S");
        put(new Pair<String, String>("A5","8" ), "H");      put(new Pair<String, String>("A6","8" ), "H");      put(new Pair<String, String>("A7","8" ), "S");
        put(new Pair<String, String>("A5","9" ), "H");      put(new Pair<String, String>("A6","9" ), "H");      put(new Pair<String, String>("A7","9" ), "H");
        put(new Pair<String, String>("A5","10"), "H");      put(new Pair<String, String>("A6","10"), "H");      put(new Pair<String, String>("A7","10"), "H");
        put(new Pair<String, String>("A5","A" ), "H");      put(new Pair<String, String>("A6","A" ), "H");      put(new Pair<String, String>("A7","A" ), "H");

        put(new Pair<String, String>("A8","2" ), "S");      put(new Pair<String, String>("A9","2" ), "S");  
        put(new Pair<String, String>("A8","3" ), "S");      put(new Pair<String, String>("A9","3" ), "S");  
        put(new Pair<String, String>("A8","4" ), "S");      put(new Pair<String, String>("A9","4" ), "S");  
        put(new Pair<String, String>("A8","5" ), "S");      put(new Pair<String, String>("A9","5" ), "S");  
        put(new Pair<String, String>("A8","6" ), "S");      put(new Pair<String, String>("A9","6" ), "S");  
        put(new Pair<String, String>("A8","7" ), "S");      put(new Pair<String, String>("A9","7" ), "S");  
        put(new Pair<String, String>("A8","8" ), "S");      put(new Pair<String, String>("A9","8" ), "S");  
        put(new Pair<String, String>("A8","9" ), "S");      put(new Pair<String, String>("A9","9" ), "S");  
        put(new Pair<String, String>("A8","10"), "S");      put(new Pair<String, String>("A9","10"), "S"); 
        put(new Pair<String, String>("A8","A" ), "S");      put(new Pair<String, String>("A9","A" ), "S"); 

        put(new Pair<String, String>("22","2" ), "H");      put(new Pair<String, String>("33","2" ), "H");      put(new Pair<String, String>("44","2" ), "H");
        put(new Pair<String, String>("22","3" ), "H");      put(new Pair<String, String>("33","3" ), "H");      put(new Pair<String, String>("44","3" ), "H");
        put(new Pair<String, String>("22","4" ), "P");      put(new Pair<String, String>("33","4" ), "P");      put(new Pair<String, String>("44","4" ), "H");
        put(new Pair<String, String>("22","5" ), "P");      put(new Pair<String, String>("33","5" ), "P");      put(new Pair<String, String>("44","5" ), "H");
        put(new Pair<String, String>("22","6" ), "P");      put(new Pair<String, String>("33","6" ), "P");      put(new Pair<String, String>("44","6" ), "H");
        put(new Pair<String, String>("22","7" ), "P");      put(new Pair<String, String>("33","7" ), "P");      put(new Pair<String, String>("44","7" ), "H");
        put(new Pair<String, String>("22","8" ), "H");      put(new Pair<String, String>("33","8" ), "H");      put(new Pair<String, String>("44","8" ), "H");
        put(new Pair<String, String>("22","9" ), "H");      put(new Pair<String, String>("33","9" ), "H");      put(new Pair<String, String>("44","9" ), "H");
        put(new Pair<String, String>("22","10"), "H");      put(new Pair<String, String>("33","10"), "H");      put(new Pair<String, String>("44","10"), "H");
        put(new Pair<String, String>("22","A" ), "H");      put(new Pair<String, String>("33","A" ), "RH");     put(new Pair<String, String>("44","A" ), "H");

        put(new Pair<String, String>("55","2" ), "D");      put(new Pair<String, String>("66","2" ), "H");      put(new Pair<String, String>("77","2" ), "P");
        put(new Pair<String, String>("55","3" ), "D");      put(new Pair<String, String>("66","3" ), "P");      put(new Pair<String, String>("77","3" ), "P");
        put(new Pair<String, String>("55","4" ), "D");      put(new Pair<String, String>("66","4" ), "P");      put(new Pair<String, String>("77","4" ), "P");
        put(new Pair<String, String>("55","5" ), "D");      put(new Pair<String, String>("66","5" ), "P");      put(new Pair<String, String>("77","5" ), "P");
        put(new Pair<String, String>("55","6" ), "D");      put(new Pair<String, String>("66","6" ), "P");      put(new Pair<String, String>("77","6" ), "P");
        put(new Pair<String, String>("55","7" ), "D");      put(new Pair<String, String>("66","7" ), "H");      put(new Pair<String, String>("77","7" ), "P");
        put(new Pair<String, String>("55","8" ), "D");      put(new Pair<String, String>("66","8" ), "H");      put(new Pair<String, String>("77","8" ), "H");
        put(new Pair<String, String>("55","9" ), "D");      put(new Pair<String, String>("66","9" ), "H");      put(new Pair<String, String>("77","9" ), "H");
        put(new Pair<String, String>("55","10"), "H");      put(new Pair<String, String>("66","10"), "H");      put(new Pair<String, String>("77","10"), "RH");
        put(new Pair<String, String>("55","A" ), "H");      put(new Pair<String, String>("66","A" ), "RH");     put(new Pair<String, String>("77","A" ), "RH");

        put(new Pair<String, String>("88","2" ), "P");      put(new Pair<String, String>("99","2" ), "P");
        put(new Pair<String, String>("88","3" ), "P");      put(new Pair<String, String>("99","3" ), "P");  
        put(new Pair<String, String>("88","4" ), "P");      put(new Pair<String, String>("99","4" ), "P");  
        put(new Pair<String, String>("88","5" ), "P");      put(new Pair<String, String>("99","5" ), "P");  
        put(new Pair<String, String>("88","6" ), "P");      put(new Pair<String, String>("99","6" ), "P");  
        put(new Pair<String, String>("88","7" ), "P");      put(new Pair<String, String>("99","7" ), "S");  
        put(new Pair<String, String>("88","8" ), "P");      put(new Pair<String, String>("99","8" ), "P");  
        put(new Pair<String, String>("88","9" ), "P");      put(new Pair<String, String>("99","9" ), "P");  
        put(new Pair<String, String>("88","10"), "RP");     put(new Pair<String, String>("99","10"), "S"); 
        put(new Pair<String, String>("88","A" ), "RP");     put(new Pair<String, String>("99","A" ), "S"); 

        put(new Pair<String, String>("TT","2" ), "S");      put(new Pair<String, String>("AA","2" ), "P");
        put(new Pair<String, String>("TT","3" ), "S");      put(new Pair<String, String>("AA","3" ), "P");  
        put(new Pair<String, String>("TT","4" ), "S");      put(new Pair<String, String>("AA","4" ), "P");  
        put(new Pair<String, String>("TT","5" ), "S");      put(new Pair<String, String>("AA","5" ), "P");  
        put(new Pair<String, String>("TT","6" ), "S");      put(new Pair<String, String>("AA","6" ), "P");  
        put(new Pair<String, String>("TT","7" ), "S");      put(new Pair<String, String>("AA","7" ), "P");  
        put(new Pair<String, String>("TT","8" ), "S");      put(new Pair<String, String>("AA","8" ), "P");  
        put(new Pair<String, String>("TT","9" ), "S");      put(new Pair<String, String>("AA","9" ), "P");  
        put(new Pair<String, String>("TT","10"), "S");      put(new Pair<String, String>("AA","10"), "P"); 
        put(new Pair<String, String>("TT","A" ), "S");      put(new Pair<String, String>("AA","A" ), "P"); 

    }};

    public static String getTurn(String firstH, String secondH){
        return null;
    }
}
