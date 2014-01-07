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
    public JsonArray getAnswer(@QueryParam("hand") Integer hand, @QueryParam("player") String player, @QueryParam("dealer") String dealer) {
        System.out.println("Hand:" + hand);
        System.out.println("Player:" + player);
        System.out.println("Dealer:" + dealer);


        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("turn", Answer.getTurn(player, dealer)))
                .build();
    }

    @Path("getID")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getID(){
        Round test = new Round();
        System.out.print("ID" + test.getID());
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type", "id")
                        .add("id", test.getID()))
                .build();

    }

}
