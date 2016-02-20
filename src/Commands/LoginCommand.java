package Commands;

import DAO.DaoEmployee;
import DAO.DaoFactory;
import DAO.DaoTask;
import DAO.DaoTaskArchive;
import DAO.Entities.Employee;
import DAO.Entities.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * This class permits to login as one of three type of employees using DB data through DAO level (DaoEmployee),
 * creates an entity of this employee and gives access to personal cabinet.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class LoginCommand implements Command {

    @Override
    public String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException {
        DaoTaskArchive daoTaskArchive = DaoFactory.getInstance().getDaoTaskArchive();
        List<Task> tasksToArchive = daoTaskArchive.findTasksToArchive();
        DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
        daoTask.setBonusesToTasks();
        boolean isExist;
        Employee employee = new Employee();
        if (!request.getParameter("login").isEmpty() && !request.getParameter("password").isEmpty()) {
            DaoEmployee daoEmployee = DaoFactory.getInstance().getDaoEmployee();
            isExist = daoEmployee.isExist(request.getParameter("login"), request.getParameter("password"));
            if (isExist) {
                employee.setLogin(request.getParameter("login"));
                employee.setPassword(request.getParameter("password"));
                daoEmployee.read(employee);
                request.getSession(true).setAttribute("employee", employee);
                request.setAttribute("name", employee.getName());
                request.setAttribute("department", employee.getStructure().getDepartment());
                request.setAttribute("taskList", employee.getTaskList());
            } else if (!isExist) {
                request.setAttribute("message", "You are not registered as an employee of our company.");
                return "/error.jsp";
            }
        } else {
            request.setAttribute("message", "You have entered empty login and / or password.");
            return "/error.jsp";
        }
        if (employee.isSuperior()){
            return "/cabinetSuperior.jsp";
        } else if (employee.isCeo()){
            request.setAttribute("tasksToArchive", tasksToArchive);
            return "/cabinetCeo.jsp";
        } else {
            return "/cabinetEmployee.jsp";
        }
    }

}
