package me.aaa.server;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("action")
public class AnswerResource {

    @Path("getTurn")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAnswer(@QueryParam("ID") String ID, @QueryParam("hand") Integer turn, @QueryParam("player") String playerHand, @QueryParam("dealer") String dealerUpcard) {
        System.out.println("=====GETTURN=======");
        System.out.println("ID:" + ID);
        System.out.println("Turn:" + turn);
        System.out.println("Player:" + playerHand);
        System.out.println("Dealer:" + dealerUpcard);

        Round now = Main.session.get(ID);
        if (now != null){
            String answer = Answer.getTurn(playerHand, dealerUpcard);

            now.addDealerH(turn, dealerUpcard);
            now.addPlayerH(turn, playerHand);
            now.addTurn(turn, answer);

            if (answer != null){

                System.out.println("answer = " + answer);

                return Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("turn", answer))
                        .build();
            }else{
                return Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("error", "noAnswerFound"))
                        .build();
            }
        }else{
            return Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("error", "noIDFound"))
                    .build();
        }
    }

    @Path("getID")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getID(){
        System.out.println("=====GETID=======");
        Round now = new Round();
        Main.session.put(now.getID(),now);
        System.out.println("ID: " + now.getID());
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("id", now.getID()))
                .build();

    }

    @Path("end")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray endSession(@QueryParam("ID") String ID, @QueryParam("result") String result){
        System.out.println("=====END=======");
        Round now = Main.session.get(ID);
        if (now != null){
            System.out.println("test");
            now.setResult(result);
            System.out.println("test2");
            Main.ds.save(now);
            System.out.println("test3");
            Main.session.remove(ID);
            System.out.println("test4");
            System.out.print("END " + ID);
            return Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("ok", "ok"))
                    .build();
        }else{
            return Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("error", "noIDFound"))
                    .build();
        }
    }

}
