package com.my.finalProject.web.command;

import com.my.finalProject.web.command.add.AddActCommand;
import com.my.finalProject.web.command.add.AddFacultiesCommand;
import com.my.finalProject.web.command.add.AddOrderCommand;
import com.my.finalProject.web.command.delete.DeleteFacultiesCommand;
import com.my.finalProject.web.command.find.*;
import com.my.finalProject.web.command.sort.*;
import com.my.finalProject.web.command.update.UpdateAccountCommand;
import com.my.finalProject.web.command.update.UpdateFacultiesCommand;
import com.my.finalProject.web.command.update.UpdateOrderCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOGGER = Logger.getLogger(CommandContainer.class);
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("noCommand", new NoCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("addFaculties", new AddFacultiesCommand());
        commands.put("addOrder", new AddOrderCommand());
        commands.put("deleteFaculties", new DeleteFacultiesCommand());
        commands.put("updateFaculties", new UpdateFacultiesCommand());
        commands.put("showAllAccount", new ShowAllAccountInfoCommand());
        commands.put("updateAccount", new UpdateAccountCommand());
        commands.put("findAccountCertificate", new FindAccountCertificateCommand());
        commands.put("findAccountOrders", new FindAccountOrdersCommand());
        commands.put("updateOrder", new UpdateOrderCommand());
        commands.put("addAct", new AddActCommand());
        commands.put("findAccountAct", new FindAccountActCommand());
        commands.put("confirmAdmissionFaculties", new ConfirmAdmissionFacultiesCommand());
        commands.put("findCurrentOrders", new FindCurrentAccountOrdersCommand());
        commands.put("findMyCertificate", new FindMyCertificateCommand());
        commands.put("sortByNameFaculty", new SortByNameFacultyCommand());
        commands.put("sortByNameFacultyReverse", new SortByNameFacultyReversCommand());
        commands.put("sortByAllPlaceFaculty", new SortByAllPlaceFacultyCommand());
        commands.put("sortByAllPlaceFacultyReverse", new SortByAllPlaceFacultyReversCommand());
        commands.put("sortByFreePlaceFaculties", new SortByFreePlaceFacultyCommand());
        commands.put("sortByFreePlaceFacultiesReverse", new SortByFreePlaceFacultiesReversCommand());
        commands.put("sortByPayPlaceFaculties", new SortByPayPlaceFacultiesCommand());
        commands.put("sortByPayPlaceFacultiesReverse", new SortByPayPlaceFacultyReversCommand());
        commands.put("uploadCertificate", new UploadCertificateCommand());
        LOGGER.debug("all command initialized");
        LOGGER.trace("command count " + commands.size());
    }

    public static Command getCommands(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOGGER.trace("Command not found " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
