package DAO;

import DAO.Entities.Employee;
import DAO.Entities.Structure;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class contains methods to manipulate employees data in DB.
 * See more concrete in method's description.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class DaoEmployee {
    private DataSource ds;
    private Connection con;
    private ResourceBundle resource = ResourceBundle.getBundle("queries");

    public DaoEmployee (DataSource ds){
        this.ds = ds;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * This method checks if given employee exist in DB.
     * @param employee - input entity to check.
     * @return true if exist, false - if not.
     * @throws SQLException - see SQLException.
     */
    public boolean isExist(String login, String password) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("IS_EXIST_EMPLOYEE"));
        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    /**
     * This method fill all empty fields of given Employee entity.
     * @param employee input entity which has some empty fields.
     * @return Employee entity with all data.
     * @throws SQLException - see SQLException.
     */
    public Employee read (Employee employee) throws SQLException {
        boolean exist = isExist(employee.getLogin(), employee.getPassword());
        if (exist){
            PreparedStatement ps = con.prepareStatement(resource.getString("TUNE_EMPLOYEE"));
            ps.setString(1, employee.getLogin());
            ps.setString(2, employee.getPassword());
            ResultSet rs = ps.executeQuery();
            DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
            DaoStructure daoStructure = DaoFactory.getInstance().getDaoStructure();
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setLastname(rs.getString("lastname"));
                employee.setMobile(rs.getString("mobile"));
                employee.setSuperior(rs.getBoolean("isSuperior"));
                employee.setCeo(rs.getBoolean("isCeo"));
            }
            employee.setStructure(daoStructure.getStructure(employee.getId()));
            employee.setTaskList(daoTask.getTaskList(employee.getId()));
               return employee;
        } else return null;
    }

    /**
     * This method adds a new employee record in DB.
     * @param employee given employee entity.
     * @return true if added.
     * @throws SQLException - see SQLException.
     */
    public boolean addNewEmployee (Employee employee) throws SQLException {
        DaoStructure daoStructure = DaoFactory.getInstance().getDaoStructure();
        int structureId;
        if (daoStructure.isExist(employee.getStructure())==-1){
            daoStructure.addNewStructure(employee.getStructure());
            structureId = daoStructure.getIDsForNewStructure(employee.getStructure());
        } else {
            structureId = daoStructure.isExist(employee.getStructure());
        }
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("ADD_NEW_SUPERIOR"));
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getLastname());
        ps.setString(3, employee.getMobile());
        ps.setInt(4, structureId);
        ps.setString(5, employee.getLogin());
        ps.setString(6, employee.getPassword());
        ps.setBoolean(7, employee.isSuperior());
        ps.setBoolean(8, employee.isCeo());
        ps.execute();
        return true;
    }

    /**
     * This method gets a list of employees by given structures list.
     * Is used to get the subordinates of given superior.
     * @param list of structures with positions of the department.
     * @return list of employees in given department.
     * @throws SQLException - see SQLException.
     */
    public List<Employee> getSubordinatesByStructureId (List<Structure> list) throws SQLException {
        List<Employee> employeesList = new LinkedList<>();
        for (Structure structure : list) {
            con = DaoFactory.getInstance().getDs().getConnection();
            PreparedStatement ps = con.prepareStatement(resource.getString("SELECT_EMPLOYEE_BY_STRUCTURE_ID"));
            ps.setInt(1, structure.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setLastname(rs.getString("lastname"));
                employeesList.add(employee);
            }
        }
        return employeesList;
    }

    /**
     * This method gets all employees from DB.
     * @return list of employees.
     * @throws SQLException - see SQLException.
     */
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employeesList = new LinkedList<>();
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_ALL_EMPLOYEES"));
        ResultSet rs = ps.executeQuery();
        DaoTask daoTask = DaoFactory.getInstance().getDaoTask();
        DaoStructure daoStructure = DaoFactory.getInstance().getDaoStructure();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setLastname(rs.getString("lastname"));
            employee.setMobile(rs.getString("mobile"));
            employee.setSuperior(rs.getBoolean("isSuperior"));
            employee.setCeo(rs.getBoolean("isCeo"));
            employee.setStructure(daoStructure.getStructure(employee.getId()));
            employee.setTaskList(daoTask.getTaskList(employee.getId()));
            employeesList.add(employee);
        }return employeesList;
    }
}
