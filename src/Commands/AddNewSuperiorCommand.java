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
 * This class is responding to a 'create new superior' request from Ceo's cabinet. Creates a new entity of Employee
 * and transfer it to DAO level (DaoEmployee) to create new Employee record in DB.
 * @author Viktor Bolshakov on 31.01.16.
 */
public class AddNewSuperiorCommand implements Command{
    @Override
    public String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        Employee employee = (Employee) session.getAttribute("employee");
        DaoEmployee daoEmployee;
        Employee newEmployee;
        boolean isTrue = false;
        if (request.getParameter("name").length()!=0 && request.getParameter("lastname").length()!=0 &&
                request.getParameter("mobile").length()!=0 && request.getParameter("position").length()!=0 &&
                request.getParameter("department").length()!=0 && request.getParameter("jobDescription").length()!=0 &&
                request.getParameter("salary").length()!=0 && request.getParameter("login").length()!=0 &&
                request.getParameter("password").length()!=0) {
            daoEmployee = DaoFactory.getInstance().getDaoEmployee();
            newEmployee = new Employee();
            newEmployee.setName(request.getParameter("name"));
            newEmployee.setLastname(request.getParameter("lastname"));
            newEmployee.setMobile(request.getParameter("mobile"));
            Structure structure = new Structure();
            structure.setPosition(request.getParameter("position"));
            structure.setDepartment(request.getParameter("department"));
            structure.setJobDescription(request.getParameter("jobDescription"));
            structure.setSalary(Double.parseDouble(request.getParameter("salary")));
            newEmployee.setStructure(structure);
            newEmployee.setLogin(request.getParameter("login"));
            newEmployee.setPassword(request.getParameter("password"));
            newEmployee.setSuperior(true);
            isTrue = daoEmployee.addNewEmployee(newEmployee);
        }
        if (isTrue) {
            request.setAttribute("message", "New Superior is added.");
            return "/success.jsp";
        } else {
            request.setAttribute("message", "Superior didn't added. May be you left some fields empty?");
            return "/error.jsp";
        }
    }
}
