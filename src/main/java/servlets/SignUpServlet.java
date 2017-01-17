package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hshvedko on 17.01.2017.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
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

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if(profile == null){
            profile = new UserProfile(login, pass);
            accountService.addSession(request.getSession().getId(), profile);
            accountService.addNewUser(profile);
        }

        String mes = "You have been signuped))))";
        Map<String, Object> pageVariables = createPageVariablesMap(mes);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
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

    public static Map<String, Object> createPageVariablesMap(String message) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("answer", message);

        return pageVariables;
    }
}
