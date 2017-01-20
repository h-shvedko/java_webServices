package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import dbService.DBExeption;
import dbService.DBService;
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
    private final DBService dbServise;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
        this.dbServise = accountService.getDbService();
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

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Integer userId = this.dbServise.getUserByName(login);

            if(userId != null){
                mes.concat("You are registered already!!!");
            } else {
                this.dbServise.addUser(login, pass);
//                UserProfile profile = new UserProfile(login, pass);
//                accountService.addSession(request.getSession().getId(), profile);
//                accountService.addNewUser(profile);
                mes.concat("You have been signuped))))");
            }
        } catch (DBExeption dbExeption) {
            dbExeption.printStackTrace();
        }

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
