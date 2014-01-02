package me.aaa;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("answer")
public class AnswerResource {

    @GET
    @Produces("application/json")
    public MyJaxbBean getIt() {
        return new MyJaxbBean("answer", "H");
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void setIt(String data) {
        System.out.println(data);
    }
}
