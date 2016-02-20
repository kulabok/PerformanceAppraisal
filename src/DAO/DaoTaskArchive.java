package DAO;

import DAO.Entities.Level;
import DAO.Entities.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is designed to manipulate TaskArchive DB data.
 * More concrete - see methods description.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class DaoTaskArchive {
    private DataSource ds;
    private Connection con;
    private ResourceBundle resource = ResourceBundle.getBundle("queries");

    public DaoTaskArchive (DataSource ds){
        this.ds = ds;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * This method searches tasks with finish date expired and returns a list of such tasks.
     * @return list of expired tasks.
     * @throws SQLException - see SQLException.
     */
    public List<Task> findTasksToArchive() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        List<Task> tasksToArchive = new ArrayList<>();
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
        Date currentDate = new Date(System.currentTimeMillis());
        for (Task task : taskList) {
            if (currentDate.compareTo(task.getEnd()) > 0) {
                tasksToArchive.add(task);
            }
        }
        return tasksToArchive;
    }
}
