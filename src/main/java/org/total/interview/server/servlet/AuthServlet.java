package org.total.interview.server.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by total on 11/30/15.
 */
public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthServlet.class);
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthServlet.doPost(): REQ_ENTRY\n");
        }

        PrintWriter out = response.getWriter();

        response.setContentType("text/plain");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            LOGGER.warn("AuthServlet.doPost(): invalid credentials");
            response.setStatus(UNAUTHORIZED);
            return;
        }

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AuthServlet.doPost(): login=" + login + " password=" + password + "\n");
            }
            response.setStatus(OK);
            out.println("login=" + login + " password=" + password + "\n");
        } catch (Exception e) {
            LOGGER.error(e, e);
        } finally {
            out.flush();
            out.close();
        }
    }

}