package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hshvedko on 17.01.2017.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String mes = "";
        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");
        UserProfile profile = accountService.getUserByLogin(login);

        if (login == null || pass == null || profile == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            mes = "Unauthorized";
        }

        if (profile != null && profile.getPass().equals(pass) && profile.getLogin().equals(login)) {
            response.setStatus(HttpServletResponse.SC_OK);
            mes = "Authorized: " + login;
        }
        Map<String, Object> pageVariables = SignUpServlet.createPageVariablesMap(mes);
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("signin.html", pageVariables));
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}
