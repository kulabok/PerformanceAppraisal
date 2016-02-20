package Commands;

import DAO.DaoEmployee;
import DAO.DaoFactory;
import DAO.DaoStructure;
import DAO.DaoTask;
import DAO.Entities.Employee;
import DAO.Entities.Structure;
import DAO.Entities.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is responding to a request from any cabinet and is adding a progress into a specified by id task.
 * @author Viktor Bolshakov on 01.02.16.
 */
public class AddProgressCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        Employee employee = (Employee) session.getAttribute("employee");
        boolean isAdded = false;
        if (employee.isCeo()){
            Task task = new Task();
            task.setId(Integer.parseInt(request.getParameter("taskId")));
            task.setQuanFact(Integer.parseInt(request.getParameter("quanFact")));
            DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
            isAdded = daoTask.addProgress(task);
        } else if (employee.isSuperior()){
            Task task = new Task();
            DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
            task.setId(Integer.parseInt(request.getParameter("taskId")));
            daoTask.getTaskById(task);
            task.setQuanFact(Integer.parseInt(request.getParameter("quanFact")));
            DaoStructure daoStructure = DaoFactory.getInstance().getDaoStructure();
            DaoEmployee daoEmployee = DaoFactory.getInstance().getDaoEmployee();
            List<Structure> subordinatesList = daoStructure.getSubordinatePositions(employee.getStructure());
            List<Employee> employeesList = daoEmployee.getSubordinatesByStructureId(subordinatesList);
            for (Employee employeeFromList : employeesList) {
                if (task.getImplementer() == employeeFromList.getId()) {
                    isAdded = daoTask.addProgress(task);
                }
            }
        } else {
            Task task = new Task();
            task.setId(Integer.parseInt(request.getParameter("taskId")));
            DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
            daoTask.getTaskById(task);
            task.setQuanFact(Integer.parseInt(request.getParameter("quanFact")));
            if (task.getImplementer() == employee.getId()){
                isAdded = daoTask.addProgress(task);
                System.out.println(daoTask.addProgress(task));
            }
        }
        System.out.println(isAdded);
        if (isAdded){
            request.setAttribute("message", "Your task was successfully updated.");
            return "/success.jsp";
        } else {
            request.setAttribute("message", "Update of your task was failed.");
            return "/error.jsp";
        }
    }
}
