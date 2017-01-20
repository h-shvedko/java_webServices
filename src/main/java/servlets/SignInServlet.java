package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBExeption;
import dbService.DBService;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
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
    private final DBService dbServise;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
        this.dbServise = this.accountService.getDbService();
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
        boolean profile = false;

        response.setContentType("text/html;charset=utf-8");
//        UserProfile profile = accountService.getUserByLogin(login);
        try {
            Integer id = dbServise.getUserByName(login);
            if(id != null){
                UsersDataSet userDataSet = dbServise.getUser(id);
                if(userDataSet.getPassword().equals(pass)){
                    profile = true;
                }
            }
        } catch (DBExeption dbExeption) {
            dbExeption.printStackTrace();
        }

        if (login == null || pass == null || profile == false) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            mes = "Unauthorized";
        }

        if (profile == true) {
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
