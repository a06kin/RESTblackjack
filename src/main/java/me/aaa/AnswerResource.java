package me.aaa;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("answer")
public class AnswerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON) //return
    public JsonArray getIt(@QueryParam("type") String type, @QueryParam("value") String value) {
        System.out.println("Type:" + type);
        System.out.println("Value:" + value);
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type", "answer")
                        .add("value", "H"))
                .build();
    }
}
