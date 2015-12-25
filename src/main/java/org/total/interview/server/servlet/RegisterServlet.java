package org.total.interview.server.servlet;

import org.apache.log4j.Logger;
import org.total.interview.server.model.RoleType;
import org.total.interview.server.model.User;
import org.total.interview.server.service.UserRoleService;
import org.total.interview.server.service.UserService;
import org.total.interview.server.util.PasswordManager;
import org.total.interview.server.util.PasswordManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    private static final UserService USER_SERVICE = new UserService();
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

    private static HttpSession SESSION;
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int OK = 200;

    private PasswordManager passwordManager = new PasswordManagerImpl();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            LOGGER.debug("Status: REQ_ENTRY, register begin.\n");

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = null;

            LOGGER.debug("Status: REQ_SUCCESS, login=" + login + "\n");

            User user = USER_SERVICE.findByName(login);

            if(user != null) {
                LOGGER.debug("Status: REQ_FAIL, user " + login + " already exists.\n");
                request.setAttribute("error", "User " + login + " already exists.\n");
                dispatcher = context.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
            } else {
                User userToRegister = new User(login, passwordManager.encode(password));
                try {
                    USER_SERVICE.persist(userToRegister);
                    USER_ROLE_SERVICE.assignRoleByUserNameAndRoleType(login, RoleType.GUEST);
                    LOGGER.debug("Status: REQ_SUCCESS, role \"" + RoleType.GUEST + "\" to user " + login + " assigned successful.\n");

                    SESSION = request.getSession(true);
                    SESSION.setAttribute("user", userToRegister);
                    response.setStatus(OK);

                    dispatcher = context.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    LOGGER.error("Status: REQ_FAIL, Error while performing register.\n");
                    response.setStatus(INTERNAL_SERVER_ERROR);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Status: REQ_FAIL, Error while performing register ", e);
            response.setStatus(INTERNAL_SERVER_ERROR);
        }
    }

}