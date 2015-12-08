package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.User;
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
import java.util.List;

/**
 * Created by total on 12/6/15.
 */
public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private PasswordManager passwordManager = new PasswordManagerImpl();

    private static final UserService USER_SERVICE = new UserService();

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
            out.println("Invalid credentials\n");
            return;
        }

//        try {
//
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("AuthServlet.doPost(): login=" + login + " password=****\n");
//            }
//
//            List<User> users = USER_SERVICE.findAll();
//
//            if (users == null || users.isEmpty()) {
//                LOGGER.error("AuthServlet.doPost(): User collection is empty\n");
//                response.setStatus(INTERNAL_SERVER_ERROR);
//                out.println("Internal Server Error\n");
//                return;
//            }
//
//            List<String> userNames = new ArrayList<String>();
//
//            for (User user : users) {
//                userNames.add(user.getUserName());
//            }
//
//            if (!userNames.contains(login)) {
//                response.setStatus(UNAUTHORIZED);
//                out.println("Invalid credentials\n");
//                return;
//            } else {
//                if (passwordManager.encode(password).equals(USER_SERVICE.findByName(login).getPassword())) {
//                    response.setStatus(OK);
//                    out.println("Hello " + login + "!\n");
//                } else {
//                    response.setStatus(UNAUTHORIZED);
//                    out.println("Invalid credentials\n");
//                    return;
//                }
//            }
//
//        } catch (Exception e) {
//            LOGGER.error(e, e);
//        } finally {
//            out.flush();
//            out.close();
//        }
    }
}
