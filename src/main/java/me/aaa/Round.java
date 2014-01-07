package me.aaa;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.Map;

@Entity("round")
public class Round {
    @Id private ObjectId id;

    private Map<Integer,String> hands;
    private Map<Integer,String> turns;

    Round(){
        Main.ds.save(this);
        System.out.print("ID" + id.toString());
    }

    public String getID(){
        return id.toString();
    }
}


