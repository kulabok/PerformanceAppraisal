package Commands;

import DAO.DaoEmployee;
import DAO.DaoFactory;
import DAO.Entities.Employee;
import DAO.Entities.Structure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This class is responding to a 'create new Employee' request from Superior's cabinet. Creates a new entity of Employee
 * and transfer it to DAO level (DaoEmployee) to create new Employee record in DB.
 * @author Viktor Bolshakov on 01.02.16.
 */
public class AddNewEmployeeCommand implements Command {
    @Override
    public String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        Employee employee = (Employee) session.getAttribute("employee");
        DaoEmployee daoEmployee;
        Employee newEmployee;
        boolean isAdded;
        if (request.getParameter("name").length()!=0 && request.getParameter("lastname").length()!=0 &&
                request.getParameter("mobile").length()!=0 && request.getParameter("position").length()!=0 &&
                request.getParameter("jobDescription").length()!=0 && request.getParameter("salary").length()!=0 &&
                request.getParameter("login").length()!=0 && request.getParameter("password").length()!=0) {
            daoEmployee = DaoFactory.getInstance().getDaoEmployee();
            newEmployee = new Employee();
            newEmployee.setName(request.getParameter("name"));
            newEmployee.setLastname(request.getParameter("lastname"));
            newEmployee.setMobile(request.getParameter("mobile"));
            Structure structure = new Structure();
            structure.setPosition(request.getParameter("position"));
            structure.setDepartment(employee.getStructure().getDepartment());
            structure.setJobDescription(request.getParameter("jobDescription"));
            structure.setSalary(Double.parseDouble(request.getParameter("salary")));
            newEmployee.setStructure(structure);
            newEmployee.setLogin(request.getParameter("login"));
            newEmployee.setPassword(request.getParameter("password"));
            isAdded = daoEmployee.addNewEmployee(newEmployee);
        } else{
            request.setAttribute("message", "Employee didn't added. Possible problem empty fields.");
            return "/error.jsp";
        }
        if (isAdded) {
            request.setAttribute("message", "New Employee is added.");
            return "/success.jsp";
        } else {
            request.setAttribute("message", "Employee didn't added.");
            return "/error.jsp";
        }
    }
}
