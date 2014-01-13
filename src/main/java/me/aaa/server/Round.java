package me.aaa.server;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

@Entity("round")
public class Round {
    @Id private ObjectId id;

    private Map<Integer,String> playerH;
    private Map<Integer,String> dealerH;
    private Map<Integer,String> turns;

    private String result;

    Round(){
        Main.ds.save(this);
        System.out.print("ID" + id.toString());
        playerH = new HashMap<>();
        dealerH = new HashMap<>();
        turns = new HashMap<>();

    }

    public String getID(){
        return id.toString();
    }

    public void addPlayerH(Integer turn, String hand){
        playerH.put(turn,hand);
    }

    public void addDealerH(Integer turn, String hand){
        dealerH.put(turn,hand);
    }

    public void addTurn(Integer turn, String answer){
        turns.put(turn,answer);
    }

    public void setResult(String r){
        result = r;
    }
}


