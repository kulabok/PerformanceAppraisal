package DAO;

import DAO.Entities.Level;
import DAO.Entities.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This method is designed to manipulate tasks DB data.
 * More concrete - see method's description.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class DaoTask {
    private DataSource ds;
    private Connection con;
    private ResourceBundle resource = ResourceBundle.getBundle("queries");

    public DaoTask (DataSource ds){
        this.ds = ds;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * This method checks if given task exist in DB.
     * @param task given task.
     * @return true is exists, false - if not.
     * @throws SQLException - see SQLException.
     */
    public boolean isExist(Task task) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("IS_EXIST_TASK"));
        ps.setInt(1, task.getId());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    /**
     * This method fills fields of task with given id.
     * @param task given task.
     * @return task with all data.
     * @throws SQLException - see SQLException.
     */
    public Task getTaskById(Task task) throws SQLException {
        boolean exist = isExist(task);
        if (exist){
            con = DaoFactory.getInstance().getDs().getConnection();
            PreparedStatement ps = con.prepareStatement(resource.getString("GET_TASKS_BY_ID"));
            ps.setInt(1, task.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                if (rs.getString("level").equalsIgnoreCase("employee"))task.setLevel(Level.EMPLOYEE);
                else if (rs.getString("level").equalsIgnoreCase("department"))task.setLevel(Level.DEPARTMENT);
                else if (rs.getString("level").equalsIgnoreCase("company"))task.setLevel(Level.COMPANY);
                task.setDescription(rs.getString("description"));
                task.setQuanPlan(rs.getInt("quanPlan"));
                if (rs.getInt("quanFact")!=0) task.setQuanFact(rs.getInt("quanFact"));
                if (rs.getDate("start")!=null) task.setStart(rs.getDate("start"));
                if (rs.getDate("end")!=null) task.setEnd(rs.getDate("end"));
                if (rs.getDouble("performance")!=0) task.setPerformance(rs.getDouble("performance"));
                if (rs.getDouble("bonus")!=0) task.setBonus(rs.getDouble("bonus"));
                if (rs.getInt("implementer")!=0) task.setImplementer(rs.getInt("implementer"));
                task.setWeight(rs.getInt("weight"));
                task.setRatio(rs.getDouble("ratio"));
            }
            if (con!=null){
                con.close();
            }return task;
        } else return null;
    }

    /**
     * This method gets the task list of given implementer.
     * @param implementer given implementer.
     * @return list of tasks.
     * @throws SQLException - see SQLException.
     */
    public List<Task> getTaskList(int implementer) throws SQLException {
        List<Task> taskList = new LinkedList<>();
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_TASKS_BY_IMPLEMENTER"));
        ps.setInt(1, implementer);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        if (rs.getString("level").equalsIgnoreCase(Level.EMPLOYEE.toString())){
            task.setLevel(Level.EMPLOYEE);
        } else if (rs.getString("level").equalsIgnoreCase(Level.DEPARTMENT.toString())){
            task.setLevel(Level.DEPARTMENT);
        }else if (rs.getString("level").equalsIgnoreCase(Level.COMPANY.toString())){
            task.setLevel(Level.COMPANY);
        }
        task.setDescription(rs.getString("description"));
        task.setQuanPlan(rs.getInt("quanPlan"));
        task.setQuanFact(rs.getInt("quanFact"));
        task.setStart(rs.getDate("start"));
        task.setEnd(rs.getDate("end"));
        task.setPerformance(rs.getDouble("performance"));
        task.setBonus(rs.getDouble("bonus"));
        task.setImplementer(rs.getInt("implementer"));
        task.setWeight(rs.getInt("weight"));
        task.setRatio(rs.getDouble("ratio"));
        taskList.add(task);
        }
        if (rs!=null){
            con.close();
        }
        return taskList;
    }

    /**
     * This method adds a new task to DB.
     * @param task given task to add.
     * @throws SQLException - see SQLException.
     */
    public boolean addNewTask(Task task) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("ADD_NEW_TASK"));
        ps.setString(1, task.getTitle());
        ps.setString(2, task.getLevel().toString());
        ps.setString(3, task.getDescription());
        ps.setInt(4, task.getQuanPlan());
        ps.setDate(5, (Date) task.getStart());
        ps.setDate(6, (Date) task.getEnd());
        ps.setInt(7, task.getImplementer());
        ps.setInt(8, task.getWeight());
        return ps.execute();
    }

    /**
     * This method adds fact progress in this task realization.
     * @param task given task.
     * @return true if progress was added, false - if not.
     * @throws SQLException - see SQLException.
     */
    public boolean addProgress (Task task) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("ADD_TASK_PROGRESS"));
        ps.setInt(1, task.getQuanFact());
        ps.setInt(2, task.getId());
        ps.execute();
        return true;
    }

    /**
     * This method adds a new rates for all the staff of this company.
     * @param rateCeo - new Ceo rate.
     * @param rateSup - new Superiors rate.
     * @param rateEmp - new Employees rate.
     * @return true if rates are added, false - if not.
     * @throws SQLException - see SQLException.
     */
    public boolean addRates(int rateCeo, int rateSup, int rateEmp) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("UPDATE_RATES"));
        ps.setInt(1, rateCeo);
        ps.setString(2, "company");
        ps.execute();
        ps = con.prepareStatement(resource.getString("UPDATE_RATES"));
        ps.setInt(1, rateSup);
        ps.setString(2, "department");
        ps.execute();
        ps = con.prepareStatement(resource.getString("UPDATE_RATES"));
        ps.setInt(1, rateEmp);
        ps.setString(2, "employee");
        ps.execute();
        return true;
    }

    /**
     * THis method calculates if bonuses are possible to give and, if possible, calculates bonuses and adds its records to DB.
     * @throws SQLException - see SQLException.
     */
    public void setBonusesToTasks() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        double bonusBaseCompany = 1000.00;
        double bonusBaseDepartment = 500.00;
        double bonusBaseEmployee = 200.00;
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_ALL_TASKS"));
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            if (rs.getString("level").equalsIgnoreCase(Level.EMPLOYEE.toString())){
                task.setLevel(Level.EMPLOYEE);
            } else if (rs.getString("level").equalsIgnoreCase(Level.DEPARTMENT.toString())){
                task.setLevel(Level.DEPARTMENT);
            }else if (rs.getString("level").equalsIgnoreCase(Level.COMPANY.toString())){
                task.setLevel(Level.COMPANY);
            }
            task.setDescription(rs.getString("description"));
            task.setQuanPlan(rs.getInt("quanPlan"));
            task.setQuanFact(rs.getInt("quanFact"));
            task.setStart(rs.getDate("start"));
            task.setEnd(rs.getDate("end"));
            task.setPerformance(rs.getDouble("performance"));
            task.setBonus(rs.getDouble("bonus"));
            task.setImplementer(rs.getInt("implementer"));
            task.setWeight(rs.getInt("weight"));
            task.setRatio(rs.getDouble("ratio"));
            taskList.add(task);
        }
        for (Task task : taskList) {
            double rate = task.getRatio() / 100;
            double performance = ((double) task.getQuanFact() / (double) task.getQuanPlan());
            task.setPerformance(performance);
            if (performance >= rate && task.getLevel().equals(Level.COMPANY)) {
                task.setBonus(bonusBaseCompany * performance * ((double) task.getWeight() / 100));
            } else if (performance >= rate && task.getLevel().equals(Level.DEPARTMENT)) {
                task.setBonus(bonusBaseDepartment * performance * ((double) task.getWeight() / 100));
            } else if (performance >= rate && task.getLevel().equals(Level.EMPLOYEE)) {
                task.setBonus(bonusBaseEmployee * performance * ((double) task.getWeight() / 100));
            }
        }
        addBonusesToDB(taskList);
    }

    /**
     * This is a private method which saves the result of bonuses calculation to DB.
     * @param taskList given tasklist with all needed data.
     * @throws SQLException - see SQLException.
     */
    private void addBonusesToDB (List<Task> taskList) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        for (Task task : taskList) {
            PreparedStatement ps = con.prepareStatement(resource.getString("ADD_BONUSES_TO_DB"));
            ps.setDouble(1, task.getPerformance());
            ps.setDouble(2, task.getBonus());
            ps.setDouble(3, task.getId());
            ps.execute();
        }
    }
}
