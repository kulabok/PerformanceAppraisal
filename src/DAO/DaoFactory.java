package DAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * This class allows to get DaoEntities with DB access through ConnectionPool.
 * Designed as a singleton.
 * @author Viktor Bolshakov on 30.01.16.
 */
public final class DaoFactory {
    private static DaoFactory instance = null;
    private DataSource ds;
    {
        try {
            InitialContext initialContext = new InitialContext();
            ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/PerformanceAppraisal");
        } catch (NamingException e) {
            //logger.error( e);
        }
    }

    private DaoFactory(){ }

    public static synchronized DaoFactory getInstance(){
        if (instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }

    public DaoEmployee getDaoEmployee() throws SQLException {
        DaoEmployee daoEmployee = new DaoEmployee(ds);
        daoEmployee.setConnection (ds.getConnection());
        return daoEmployee;
    }

    public DaoStructure getDaoStructure() throws SQLException {
        DaoStructure daoStructure = new DaoStructure(ds);
        daoStructure.setConnection (ds.getConnection());
        return daoStructure;
    }

    public DaoTask getDaoTask() throws SQLException {
        DaoTask daoTask = new DaoTask(ds);
        daoTask.setConnection (ds.getConnection());
        return daoTask;
    }

    public DaoTaskArchive getDaoTaskArchive() throws SQLException {
        DaoTaskArchive daoTaskArchive = new DaoTaskArchive(ds);
        daoTaskArchive.setConnection (ds.getConnection());
        return daoTaskArchive;
    }

    public DataSource getDs() {
        return ds;
    }
}
