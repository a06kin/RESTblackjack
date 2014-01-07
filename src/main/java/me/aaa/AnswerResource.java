package me.aaa;

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
        System.out.println("ID:" + ID);
        System.out.println("Turn:" + turn);
        System.out.println("Player:" + playerHand);
        System.out.println("Dealer:" + dealerUpcard);

        Round now = Main.session.get(ID);
        String answer = Answer.getTurn(playerHand, dealerUpcard);

        now.addDealerH(turn, dealerUpcard);
        now.addPlayerH(turn, playerHand);
        now.addTurn(turn, answer);

        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("turn", answer))
                .build();
    }

    @Path("getID")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getID(){
        Round now = new Round();
        Main.session.put(now.getID(),now);
        System.out.print("ID" + now.getID());
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("id", now.getID()))
                .build();

    }

    @Path("end")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void endSession(@QueryParam("ID") String ID, @QueryParam("result") String result){
        Round now = Main.session.get(ID);
        now.setResult(result);
        Main.ds.save(now);
        Main.session.remove(ID);
        System.out.print("END " + ID);
    }

}
