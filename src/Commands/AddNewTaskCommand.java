package Commands;


import DAO.DaoFactory;
import DAO.DaoTask;
import DAO.Entities.Employee;
import DAO.Entities.Level;
import DAO.Entities.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class is responding to 'add new task' request from Ceo or Superior's cabinet.
 * Creates a new Task entity and transfers it to a DAO level (DaoTask) to create a new task record in DB.
 * @author Viktor Bolshakov on 01.02.16.
 */
public class AddNewTaskCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (request.getParameter("title").length()!=0 && request.getParameter("level").length()!=0 &&
            request.getParameter("description").length()!=0 && request.getParameter("quanPlan").length()!=0 &&
            request.getParameter("start").length()!=0 && request.getParameter("end").length()!=0 &&
            request.getParameter("weight").length()!=0 && request.getParameter("implementer").length()!=0) {
            DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
            HttpSession session = request.getSession(true);
            Employee employee = (Employee) session.getAttribute("employee");
            if (employee.isCeo()){
                Task task = new Task();
                task.setTitle(request.getParameter("title"));
                if (request.getParameter("level").equalsIgnoreCase("company")) task.setLevel(Level.COMPANY);
                else if (request.getParameter("level").equalsIgnoreCase("department")) task.setLevel(Level.DEPARTMENT);
                else {
                    request.setAttribute("message", "You have typed objective level for employee.");
                    return "/error.jsp";
                }
                task.setDescription(request.getParameter("description"));
                task.setQuanPlan(Integer.parseInt(request.getParameter("quanPlan")));
                LocalDate start = LocalDate.parse(request.getParameter("start"));
                task.setStart(Date.valueOf(start));
                LocalDate end = LocalDate.parse(request.getParameter("end"));
                task.setEnd(Date.valueOf(end));
                task.setWeight(Integer.parseInt(request.getParameter("weight")));
                task.setImplementer(Integer.parseInt(request.getParameter("implementer")));
                daoTask.addNewTask(task);
                request.setAttribute("message", "New task is added.");
                return "/success.jsp";
            } else if (employee.isSuperior()){
                Task task = new Task();
                task.setTitle(request.getParameter("title"));
                if (request.getParameter("level").equalsIgnoreCase("department")) task.setLevel(Level.DEPARTMENT);
                else if (request.getParameter("level").equalsIgnoreCase("employee")) task.setLevel(Level.EMPLOYEE);
                task.setDescription(request.getParameter("description"));
                task.setQuanPlan(Integer.parseInt(request.getParameter("quanPlan")));
                task.setStart(Date.valueOf(request.getParameter("start")));
                task.setEnd(Date.valueOf(request.getParameter("end")));
                task.setWeight(Integer.parseInt(request.getParameter("weight")));
                task.setImplementer(Integer.parseInt(request.getParameter("implementer")));
                daoTask.addNewTask(task);
                request.setAttribute("message", "New task is added.");
                return "/success.jsp";
            }else {
                request.setAttribute("message", "You have entered wrong data.");
                return "/error.jsp";
            }
        } else {
            request.setAttribute("message", "You have entered wrong data or have left empty fields.");
            return "/error.jsp";
        }
    }
}
