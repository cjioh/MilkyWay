package org.total.interview.server.resources;

import org.apache.log4j.Logger;
import org.total.interview.server.marshall.GenericContentHandler;
import org.total.interview.server.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class);
    private static final UserService USER_SERVICE = new UserService();
    private static final GenericContentHandler HANDLER = new GenericContentHandler();

    private static final String ERROR_RESPONSE = "<Response>Invalid credentials</Response>";
    private static final int OK = 200;
    private static final int UNAUTHORIZED = 401;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response fetchAllUsers(@HeaderParam("token") final String token) {

        LOGGER.debug("Status: REQ_ENTRY");

        if (token == null || token.isEmpty()) {
            LOGGER.warn("Status: REQ_FAIL");
            return Response.status(UNAUTHORIZED).entity(ERROR_RESPONSE).build();
        }

        return Response.status(OK).entity(HANDLER.marshal(USER_SERVICE.findAll(), "users")).build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response fetchUserById(@PathParam("userId") String userId,
                                  @HeaderParam("token") final String token) {

        LOGGER.debug("Status: REQ_ENTRY");

        if (token == null || token.isEmpty()) {
            LOGGER.warn("Status: REQ_FAIL");
            return Response.status(UNAUTHORIZED).entity(ERROR_RESPONSE).build();
        }

        return Response.status(OK).entity(HANDLER.marshal(USER_SERVICE.findById(Long.valueOf(userId)))).build();
    }

    @GET
    @Path("/userName/{userName}")
    @Produces(MediaType.APPLICATION_XML)
    public Response fetchUserByName(@PathParam("userName") String userName,
                                    @HeaderParam("token") final String token) {

        LOGGER.debug("Status: REQ_ENTRY");

        if (token == null || token.isEmpty()) {
            LOGGER.warn("Status: REQ_FAIL");
            return Response.status(UNAUTHORIZED).entity(ERROR_RESPONSE).build();
        }

        return Response.status(OK).entity(HANDLER.marshal(USER_SERVICE.findByName(userName))).build();
    }

}
