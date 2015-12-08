package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.Role;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
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
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

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

//        try {
//
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("RegisterServlet.doPost(): login=" + login + " password=****\n");
//            }
//
//            if (USER_SERVICE.findByName(login) != null) {
//                response.setStatus(OK);
//                out.println("User " + login + " exists already\n");
//                return;
//            }
//
//            User userToRegister = new User(login, passwordManager.encode(password));
//
//            try {
//                USER_SERVICE.persist(userToRegister);
//            } catch (Exception e) {
//                LOGGER.error(e, e);
//                response.setStatus(INTERNAL_SERVER_ERROR);
//                out.println("Exception while user addition\n");
//                return;
//            }
//
//            USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle("user", login);
//
//            response.setStatus(OK);
//            out.println("User " + login + " added successfully\n");
//
//        } catch (Exception e) {
//            LOGGER.error(e, e);
//        } finally {
//            out.flush();
//            out.close();
//        }
    }

}