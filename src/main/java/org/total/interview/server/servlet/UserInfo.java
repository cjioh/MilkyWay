package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UserInfo extends HttpServlet {

    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();

    private static final Logger LOGGER = Logger.getLogger(UserInfo.class);

    private static final int OK = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            LOGGER.debug("Status: REQ_ENTRY.\n");

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = null;

            Set<Role> roleSet = (Set<Role>)request.getAttribute("roles");

            if(roleSet.contains(ROLE_SERVICE.findByRoleType(RoleType.ADMIN))) {
                List<User> users = USER_SERVICE.findAll();
                request.setAttribute("users", users);
                dispatcher = context.getRequestDispatcher("/adminPage.jsp");
                dispatcher.forward(request, response);
            } else {
                User user = USER_SERVICE.findByName(request.getParameter("login"));
                request.setAttribute("user", user);
                dispatcher = context.getRequestDispatcher("/userPage.jsp");
                dispatcher.forward(request, response);
            }

            response.setStatus(OK);
        } catch (ServletException e) {
            response.setStatus(INTERNAL_SERVER_ERROR);
            LOGGER.error(e, e);
        } catch (IOException e) {
            response.setStatus(INTERNAL_SERVER_ERROR);
            LOGGER.error(e, e);
        }
    }

}
