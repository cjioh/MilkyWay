package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.User;
import org.total.interview.server.service.RoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthServlet.class);

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    private PasswordManager passwordManager = new PasswordManagerImpl();

    private static final UserService USER_SERVICE = new UserService();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException {

        LOGGER.debug("Status: REQ_ENTRY, auth begin\n");

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
            LOGGER.debug("Status: REQ_SUCCESS, login=" + login + "\n");

            List<User> users = USER_SERVICE.findByUserNameAndPassword(login, passwordManager.encode(password));

            if (users.contains(USER_SERVICE.findByName(login))) {
                LOGGER.debug("Status: REQ_SUCCESS, auth successful\n");

                request.setAttribute("roles", users.get(0).getRoles());

                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/userinfo");
                dispatcher.forward(request, response);

                response.setStatus(OK);
            } else {
                LOGGER.warn("Status: REQ_FAIL, invalid credentials.\n");
                response.setStatus(UNAUTHORIZED);
                out.println("Invalid credentials.\n");
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
