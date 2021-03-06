package PresentationLayer;

import FunctionLayer.DataRetrievalException;
import FunctionLayer.LoginSampleException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("help", new Help());
        commands.put("customer", new Customer());
        commands.put("employee", new Employee());
        commands.put("orderInput", new OrderInput());
        commands.put("orderConfirmation", new OrderConfirmation());
        commands.put("orders", new Orders());
        commands.put("emporders", new EmpOrders());
        commands.put("orderSent", new OrderSent());
        commands.put("pieceList", new PieceList());
        
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginSampleException, DataRetrievalException;

}
