package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by total on 11/30/15.
 */
public class RegisterServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private PasswordManager passwordManager = new PasswordManagerImpl();

    private static final UserService USER_SERVICE = new UserService();
    private static final RoleService ROLE_SERVICE = new RoleService();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RegisterServlet.doPost(): REQ_ENTRY\n");
        }

        PrintWriter out = response.getWriter();

        response.setContentType("text/plain");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            LOGGER.warn("RegisterServlet.doPost(): invalid credentials");
            response.setStatus(UNAUTHORIZED);
            out.println("Invalid credentials\n");
            return;
        }

        try {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RegisterServlet.doPost(): login=" + login + " password=****\n");
            }

            List<User> users = USER_SERVICE.findAll();

            if (users == null || users.isEmpty()) {
                LOGGER.error("RegisterServlet.doPost(): User collection is empty\n");
                response.setStatus(INTERNAL_SERVER_ERROR);
                out.println("Internal Server Error\n");
                return;
            }

            List<String> userNames = new ArrayList<String>();

            for (User user : users) {
                userNames.add(user.getUserName());
            }

            if (!userNames.contains(login)) {
                Set<Role> roles = new HashSet<Role>();
                Role role = ROLE_SERVICE.findByRoleTitle("user");

                if (role == null) {
                    LOGGER.error("Role in null\n");
                    response.setStatus(INTERNAL_SERVER_ERROR);
                    out.println("Internal Server Error\n");
                    return;
                }
                roles.add(role);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Assigned Role Title: " + role.getRoleTitle());
                }
                User user = new User(login, passwordManager.encode(password));

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("User for addition: " + user);
                }
                user.setRoles(roles);

                try {
                    USER_SERVICE.persist(user);
                } catch (Exception e) {
                    LOGGER.error(e, e);
                    response.setStatus(INTERNAL_SERVER_ERROR);
                    out.println("Exception while user addition\n");
                    return;
                }

                response.setStatus(OK);
                out.println("User " + login + " added successfully\n");
            } else {
                response.setStatus(OK);
                out.println("User " + login + " exists already\n");
            }

        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            out.flush();
            out.close();
        }
    }

}