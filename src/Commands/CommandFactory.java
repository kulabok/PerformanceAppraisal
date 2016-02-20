package Commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This class processes incoming from MainController data and chooses which Command have to start according to request.
 * Class made as a singleton.
 * @author Viktor Bolshakov on 30.01.16.
 */
public final class CommandFactory {
    private static CommandFactory instance = null;
    private Map<String, Command> commandMap = new HashMap<>();
    {
        commandMap.put("Login", new LoginCommand());
        commandMap.put("addNewSuperior", new AddNewSuperiorCommand());
        commandMap.put("addNewTask", new AddNewTaskCommand());
        commandMap.put("addRates", new AddRatesCommand());
        commandMap.put("addProgress", new AddProgressCommand());
        commandMap.put("addNewEmployee", new AddNewEmployeeCommand());
    }

    private CommandFactory(){}

    public Command getCommand (HttpServletRequest request){
        String value = request.getParameter("ok");
        return commandMap.get(value);
    }

    public static synchronized CommandFactory getInstance(){
        if (instance == null){
            instance = new CommandFactory();
        }
        return instance;
    }
}
