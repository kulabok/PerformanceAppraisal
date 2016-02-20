package Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Interface to inherit.
 * Contains one abstract method to implement.
 * @author Viktor Bolshakov on 30.01.16.
 */
public interface Command {
    public String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
