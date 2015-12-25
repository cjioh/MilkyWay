package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserInfo extends HttpServlet {

    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();
    private static final Logger LOGGER = Logger.getLogger(UserInfo.class);

    private static final int OK = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            LOGGER.debug("Status: REQ_ENTRY.\n");

            User user = (User) request.getSession().getAttribute("user");

            List<User> users = null;

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = null;

            if (user.getRoles().contains(ROLE_SERVICE.findByRoleType(RoleType.ADMIN))) {
                users = USER_SERVICE.findAll();
            } else {
                users = new ArrayList<User>();
                users.add(user);
            }
            LOGGER.info(users);

            response.setStatus(OK);

            request.setAttribute("users", users);
            dispatcher = context.getRequestDispatcher("/management.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.setStatus(INTERNAL_SERVER_ERROR);
            LOGGER.error(e, e);
        }
    }

}
