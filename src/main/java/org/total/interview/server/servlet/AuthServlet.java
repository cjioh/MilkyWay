package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    private PasswordManager passwordManager = new PasswordManagerImpl();

    private static final UserService USER_SERVICE = new UserService();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Status: REQ_ENTRY, auth begin\n");
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

                if (passwordManager.encode(password).equals(USER_SERVICE.findByName(login).getPassword())) {
                    LOGGER.debug("Status: REQ_SUCCESS, auth successful\n");
                    response.setStatus(OK);
                    out.println("Hello " + login + "!\n");
                } else {
                    LOGGER.debug("Status: REQ_FAIL, invalid credentials, wrong password submited.\n");
                    response.setStatus(UNAUTHORIZED);
                    out.println("Invalid credentials, wrong password submited.\n");
                    return;
                }

            } else {
                LOGGER.warn("Status: REQ_FAIL, Invalid credentials, user " + login + " does not exist.\n");
                response.setStatus(UNAUTHORIZED);
                out.println("Invalid credentials, user " + login + " does not exist.\n");
                return;
            }

        } catch (Exception e) {
            LOGGER.error("Status: REQ_FAIL, Error while performing auth ", e);
            response.setStatus(INTERNAL_SERVER_ERROR);
            out.println("Internal Server Error, Error while performing auth\n");
        } finally {
            out.flush();
            out.close();
        }
    }
}
