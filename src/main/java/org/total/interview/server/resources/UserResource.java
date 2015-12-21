package org.total.interview.server.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.*;
import org.total.interview.server.marshall.GenericContentHandler;
import org.total.interview.server.model.User;
import org.total.interview.server.service.UserService;

@Path("/users")
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class);
    private static final UserService USER_SERVICE = new UserService();
    private static final int OK = 200;

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response fetchUserById(@PathParam("userId") String userId) {
        GenericContentHandler handler = new GenericContentHandler();
        User user = USER_SERVICE.findById(Long.valueOf(userId));
        return Response.status(OK).entity(handler.marshal(user)).build();
    }

    @GET
    @Path("/userName/{userName}")
    @Produces(MediaType.APPLICATION_XML)
    public Response fetchUserByName(@PathParam("userName") String userName) {
        GenericContentHandler handler = new GenericContentHandler();
        User user = USER_SERVICE.findByName(userName);
        return Response.status(OK).entity(handler.marshal(user)).build();
    }

}
