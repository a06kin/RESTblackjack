package me.aaa;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("answer")
public class AnswerResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getIt() {
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type", "answer")
                        .add("value", "H"))
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void setIt(String data) {
        System.out.println(data);
    }
}
