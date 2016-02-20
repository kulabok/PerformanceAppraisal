package Commands;

import DAO.DaoFactory;
import DAO.DaoTask;
import DAO.Entities.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This class is responding to 'add rates' request from Ceo's cabinet and transfer data to DAO level (DaoTask)
 * to set rates for all company's staff.
 * @author Viktor Bolshakov on 01.02.16.
 */
public class AddRatesCommand implements Command {

    @Override
    public String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        Employee employee = (Employee) session.getAttribute("employee");
        DaoTask daoTask;
        boolean isTrue;
        if (request.getParameter("rateCeo").length()!=0 && request.getParameter("rateSup")!=null && request.getParameter("rateEmp").length()!=0) {
        int rateCeo = Integer.parseInt(request.getParameter("rateCeo"));
        int rateSup = Integer.parseInt(request.getParameter("rateSup"));
        int rateEmp = Integer.parseInt(request.getParameter("rateEmp"));
        if (employee.isCeo()) {
            daoTask = DaoFactory.getInstance().getDaoTask();
            } else {
            request.setAttribute("message", "You have entered wrong data or have left empty fields.");
            return "/error.jsp";
            }
            isTrue = daoTask.addRates(rateCeo, rateSup, rateEmp);
            if (isTrue){
                request.setAttribute("message", "Rates have been updated. Employees will be happier");
                return "/success.jsp";
            } else{
                request.setAttribute("message", "Rates haven't been updated. Employees will become philosophers");
                return "/error.jsp";
            }
        } else {
            request.setAttribute("message", "Rates haven't been updated. May be you left some fields empty");
            return "/error.jsp";
        }
    }
}
