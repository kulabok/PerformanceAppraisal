package Controllers;

import Commands.Command;
import Commands.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This controller processes all incoming requests and transfers it to a CommandFactory.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class MainController extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Command command =  CommandFactory.getInstance().getCommand(request);
        String path = command.execute(request, response);
        RequestDispatcher rd=request.getRequestDispatcher(path);
        try {
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            //LOGGER
        }
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
