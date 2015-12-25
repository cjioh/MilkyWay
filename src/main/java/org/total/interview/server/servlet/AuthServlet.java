package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.User;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthServlet.class);
    private static HttpSession SESSION;

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    private PasswordManager passwordManager = new PasswordManagerImpl();
    private static final UserService USER_SERVICE = new UserService();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            LOGGER.debug("Status: REQ_ENTRY, auth begin\n");

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = null;

            LOGGER.debug("Status: REQ_SUCCESS, login=" + login + "\n");

            User user = USER_SERVICE.findByUserNameAndPassword(login, passwordManager.encode(password));

            if (user != null) {
                LOGGER.debug("Status: REQ_SUCCESS, auth successful\n");

                SESSION = request.getSession(true);
                SESSION.setAttribute("user", user);

                response.setStatus(OK);
                dispatcher = context.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } else {
                if(USER_SERVICE.findByName(login) != null) {
                    LOGGER.warn("Status: REQ_FAIL, invalid credentials.\n");
                    response.setStatus(UNAUTHORIZED);
                    request.setAttribute("error", "Invalid credentials");
                    dispatcher = context.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    LOGGER.warn("Status: REQ_FAIL, no user " + login + " found.\n");
                    response.setStatus(UNAUTHORIZED);
                    request.setAttribute("error", "no user " + login + " found.");
                    dispatcher = context.getRequestDispatcher("/register.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Status: REQ_FAIL, Error while performing auth ", e);
            response.setStatus(INTERNAL_SERVER_ERROR);
        }
    }
}
