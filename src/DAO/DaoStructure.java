package DAO;

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
 * This class allows to get information from DB about company's structure, positions etc.
 * More concrete - see method's description.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class DaoStructure {
    private DataSource ds;
    private Connection con;
    private ResourceBundle resource = ResourceBundle.getBundle("queries");

    public DaoStructure (DataSource ds){
        this.ds = ds;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * This method checks if given set of data exists in DB.
     * @param structure given structure.
     * @return true is exists, false - if not.
     * @throws SQLException  - see SQLException.
     */
    public int isExist(Structure structure) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("IS_EXIST_STRUCTURE"));
        ps.setString(1, structure.getPosition());
        ps.setString(2, structure.getDepartment());
        ps.setDouble(3, structure.getSalary());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
        return rs.getInt("id");
        } else {
            return -1;
        }
    }

    /**
     * This method allows to get a structure by its id.
     * @param id given structure id.
     * @return structure entity with all its data.
     * @throws SQLException - see SQLException.
     */
    public Structure getStructure(int id) throws SQLException {
        Structure structure = new Structure();
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_STRUCTURE"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            structure.setId(rs.getInt("id"));
            structure.setCompanyTitle(rs.getString("title"));
            structure.setDepartment(rs.getString("department"));
            structure.setPosition(rs.getString("position"));
            structure.setJobDescription(rs.getString("jobDescription"));
            structure.setSalary(Double.parseDouble(rs.getString("salary")));
        }
        return structure;
    }

    /**
     * This method is adding a new structure in DB.
     * @param structure given structure to add.
     * @throws SQLException - see SQLException.
     */
    public void addNewStructure(Structure structure) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("ADD_NEW_STRUCTURE"));
        ps.setString(1, "myCompany");
        ps.setString(2, structure.getDepartment());
        ps.setString(3, structure.getPosition());
        ps.setString(4, structure.getJobDescription());
        ps.setDouble(5, structure.getSalary());
        ps.execute();
    }

    /**
     * This method returns structure Id in DB to fill empty fields of a new entity.
     * @param structure given structure.
     * @return id.
     * @throws SQLException - see SQLException.
     */
    public int getIDsForNewStructure (Structure structure) throws SQLException {
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_NEW_STRUCTURE_ID"));
        ps.setString(1, structure.getPosition());
        ps.setString(2, structure.getDepartment());
        ps.setDouble(3, structure.getSalary());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    /**
     * This method gets a list of subordinates of a specified superior.
     * @param structure given structure with superior's id.
     * @return list of subordinates positions.
     * @throws SQLException - see SQLException.
     */
    public List<Structure> getSubordinatePositions(Structure structure) throws SQLException {
        List<Structure> subpositions = new LinkedList<>();
        con = DaoFactory.getInstance().getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(resource.getString("GET_SUBPOSITIONS"));
        ps.setString(1, structure.getDepartment());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Structure struct = new Structure();
            struct.setId(rs.getInt("id"));
            struct.setPosition(rs.getString("position"));
            subpositions.add(struct);
        }
        return subpositions;
    }
}
