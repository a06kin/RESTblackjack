package com.mySampleApplication.server;

import java.util.HashMap;
import java.util.Map;

public class BjAnswer {

    private static Map<String, String> data = new HashMap<String, String>() {{
        //6 decks, S17, D10, No DAS, No Surrender, Peek

        //{My,D}, {A}

        /*  H   = Hit
            S   = Stand
            P   = Split
            D   = Double (Hit if not allowed)
        */


        System.out.print("Answer map init");

        put("52" , "H");       put("62" , "H");       put("72" , "H");
        put("53" , "H");       put("63" , "H");       put("73" , "H");
        put("54" , "H");       put("64" , "H");       put("74" , "H");
        put("55" , "H");       put("65" , "H");       put("75" , "H");
        put("56" , "H");       put("66" , "H");       put("76" , "H");
        put("57" , "H");       put("67" , "H");       put("77" , "H");
        put("58" , "H");       put("68" , "H");       put("78" , "H");
        put("59" , "H");       put("69" , "H");       put("79" , "H");
        put("510", "H");       put("610", "H");       put("710", "H");
        put("5A" , "H");       put("6A" , "H");       put("7A" , "H");

        put("82" , "H");       put("92" , "H");       put("102" , "D");
        put("83" , "H");       put("93" , "H");       put("103" , "D");
        put("84" , "H");       put("94" , "H");       put("104" , "D");
        put("85" , "H");       put("95" , "H");       put("105" , "D");
        put("86" , "H");       put("96" , "H");       put("106" , "D");
        put("87" , "H");       put("97" , "H");       put("107" , "D");
        put("88" , "H");       put("98" , "H");       put("108" , "D");
        put("89" , "H");       put("99" , "H");       put("109" , "D");
        put("810", "H");       put("910", "H");       put("1010", "H");
        put("8A" , "H");       put("9A" , "H");       put("10A" , "H");

        put("112" , "D");      put("122" , "H");      put("132" , "S");
        put("113" , "D");      put("123" , "H");      put("133" , "S");
        put("114" , "D");      put("124" , "S");      put("134" , "S");
        put("115" , "D");      put("125" , "S");      put("135" , "S");
        put("116" , "D");      put("126" , "S");      put("136" , "S");
        put("117" , "D");      put("127" , "H");      put("137" , "H");
        put("118" , "D");      put("128" , "H");      put("138" , "H");
        put("119" , "D");      put("129" , "H");      put("139" , "H");
        put("1110", "D");      put("1210", "H");      put("1310", "H");
        put("11A" , "H");      put("12A" , "H");      put("13A" , "H");

        put("142" , "S");      put("152" , "S");      put("162" , "S");
        put("143" , "S");      put("153" , "S");      put("163" , "S");
        put("144" , "S");      put("154" , "S");      put("164" , "S");
        put("145" , "S");      put("155" , "S");      put("165" , "S");
        put("146" , "S");      put("156" , "S");      put("166" , "S");
        put("147" , "H");      put("157" , "H");      put("167" , "H");
        put("148" , "H");      put("158" , "H");      put("168" , "H");
        put("149" , "H");      put("159" , "H");      put("169" , "H");
        put("1410", "H");      put("1510", "H");      put("1610", "H");
        put("14A" , "H");      put("15A" , "H");      put("16A" , "H");

        put("172" , "S");  
        put("173" , "S");  
        put("174" , "S");  
        put("175" , "S");  
        put("176" , "S");  
        put("177" , "S");  
        put("178" , "S");  
        put("179" , "S");  
        put("1710", "S"); 
        put("17A" , "S");

        put("A22" , "H");      put("A32" , "H");      put("A42" , "H");
        put("A23" , "H");      put("A33" , "H");      put("A43" , "H");
        put("A24" , "H");      put("A34" , "H");      put("A44" , "H");
        put("A25" , "H");      put("A35" , "H");      put("A45" , "H");
        put("A26" , "H");      put("A36" , "H");      put("A46" , "H");
        put("A27" , "H");      put("A37" , "H");      put("A47" , "H");
        put("A28" , "H");      put("A38" , "H");      put("A48" , "H");
        put("A29" , "H");      put("A39" , "H");      put("A49" , "H");
        put("A210", "H");      put("A310", "H");      put("A410", "H");
        put("A2A" , "H");      put("A3A" , "H");      put("A4A" , "H");

        put("A52" , "H");      put("A62" , "H");      put("A72" , "S");
        put("A53" , "H");      put("A63" , "H");      put("A73" , "S");
        put("A54" , "H");      put("A64" , "H");      put("A74" , "S");
        put("A55" , "H");      put("A65" , "H");      put("A75" , "S");
        put("A56" , "H");      put("A66" , "H");      put("A76" , "S");
        put("A57" , "H");      put("A67" , "H");      put("A77" , "S");
        put("A58" , "H");      put("A68" , "H");      put("A78" , "S");
        put("A59" , "H");      put("A69" , "H");      put("A79" , "H");
        put("A510", "H");      put("A610", "H");      put("A710", "H");
        put("A5A" , "H");      put("A6A" , "H");      put("A7A" , "H");

        put("A82" , "S");      put("A92" , "S");  
        put("A83" , "S");      put("A93" , "S");  
        put("A84" , "S");      put("A94" , "S");  
        put("A85" , "S");      put("A95" , "S");  
        put("A86" , "S");      put("A96" , "S");  
        put("A87" , "S");      put("A97" , "S");  
        put("A88" , "S");      put("A98" , "S");  
        put("A89" , "S");      put("A99" , "S");  
        put("A810", "S");      put("A910", "S"); 
        put("A8A" , "S");      put("A9A" , "S"); 

        put("222" , "H");      put("332" , "H");      put("442" , "H");
        put("223" , "H");      put("333" , "H");      put("443" , "H");
        put("224" , "P");      put("334" , "P");      put("444" , "H");
        put("225" , "P");      put("335" , "P");      put("445" , "H");
        put("226" , "P");      put("336" , "P");      put("446" , "H");
        put("227" , "P");      put("337" , "P");      put("447" , "H");
        put("228" , "H");      put("338" , "H");      put("448" , "H");
        put("229" , "H");      put("339" , "H");      put("449" , "H");
        put("2210", "H");      put("3310", "H");      put("4410", "H");
        put("22A" , "H");      put("33A" , "H");      put("44A" , "H");

        put("552" , "D");      put("662" , "H");      put("772" , "P");
        put("553" , "D");      put("663" , "P");      put("773" , "P");
        put("554" , "D");      put("664" , "P");      put("774" , "P");
        put("555" , "D");      put("665" , "P");      put("775" , "P");
        put("556" , "D");      put("666" , "P");      put("776" , "P");
        put("557" , "D");      put("667" , "H");      put("777" , "P");
        put("558" , "D");      put("668" , "H");      put("778" , "H");
        put("559" , "D");      put("669" , "H");      put("779" , "H");
        put("5510", "H");      put("6610", "H");      put("7710", "H");
        put("55A" , "H");      put("66A" , "H");      put("77A" , "H");

        put("882" , "P");      put("992" , "P");
        put("883" , "P");      put("993" , "P");  
        put("884" , "P");      put("994" , "P");  
        put("885" , "P");      put("995" , "P");  
        put("886" , "P");      put("996" , "P");  
        put("887" , "P");      put("997" , "S");  
        put("888" , "P");      put("998" , "P");  
        put("889" , "P");      put("999" , "P");  
        put("8810", "P");      put("9910", "S");
        put("88A" , "P");      put("99A" , "S");

        put("TT2" , "S");      put("AA2" , "P");
        put("TT3" , "S");      put("AA3" , "P");
        put("TT4" , "S");      put("AA4" , "P");
        put("TT5" , "S");      put("AA5" , "P");
        put("TT6" , "S");      put("AA6" , "P");
        put("TT7" , "S");      put("AA7" , "P");
        put("TT8" , "S");      put("AA8" , "P");
        put("TT9" , "S");      put("AA9" , "P");
        put("TT10", "S");      put("AA10", "P");
        put("TTA" , "S");      put("AAA" , "P");

    }};

    public static String getTurn(String player, String dealerUpcard){
        return data.get(player+dealerUpcard);
    }
}
