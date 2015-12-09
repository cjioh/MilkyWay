package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.User;
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

public class RegisterServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    private static final UserService USER_SERVICE = new UserService();
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

    private PasswordManager passwordManager = new PasswordManagerImpl();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Status: REQ_ENTRY, register begin.\n");
        }

        PrintWriter out = response.getWriter();

        response.setContentType("text/plain");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            LOGGER.warn("Status: REQ_FAIL, invalid credentials.\n");
            response.setStatus(UNAUTHORIZED);
            out.println("Invalid credentials.\n");
            return;
        }

        try {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Status: REQ_SUCCESS, login=" + login + " password=****\n");
            }

            if (USER_SERVICE.findByName(login) != null) {
                LOGGER.debug("Status: REQ_FAIL, user " + login + " already exists.\n");
                response.setStatus(OK);
                out.println("User " + login + " already exists.\n");
                return;
            }

            User userToRegister = new User(login, passwordManager.encode(password));

            if (userToRegister == null) {
                LOGGER.error("Status: REQ_FAIL, Error while user creation.\n");
                response.setStatus(INTERNAL_SERVER_ERROR);
                out.println("Internal Server Error, Error while user creation.\n");
            }

            try {
                USER_SERVICE.persist(userToRegister);
            } catch (Exception e) {
                LOGGER.error("Status: REQ_FAIL, Error while performing register.\n");
                response.setStatus(INTERNAL_SERVER_ERROR);
                out.println("Internal Server Error, Error while performing register.\n");
                return;
            }

            try {
                USER_ROLE_SERVICE.assignRoleByUserNameAndRoleTitle(login, "guest");
                LOGGER.debug("Status: REQ_SUCCESS, role \"user\" to user " + login + " assigned successful.\n");
                response.setStatus(OK);
                out.println("User " + login + " added successfully\n");
            } catch (Exception e) {
                LOGGER.error("Status: REQ_FAIL, Error while performing register ", e);
                response.setStatus(INTERNAL_SERVER_ERROR);
                out.println("Internal Server Error, Error while performing register.\n");
            }

        } catch (Exception e) {
            LOGGER.error("Status: REQ_FAIL, Error while performing register ", e);
            response.setStatus(INTERNAL_SERVER_ERROR);
            out.println("Internal Server Error, Error while performing register.\n");
        } finally {
            out.flush();
            out.close();
        }
    }

}