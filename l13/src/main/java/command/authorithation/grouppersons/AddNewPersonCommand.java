package command.authorithation.grouppersons;

import command.Command;
import command.CommandResult;
import command.authorithation.grouppersons.constants.GroupConstant;
import exception.IncorrectDataException;
import exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Person;
import org.apache.log4j.Logger;
import services.PersonService;
import util.pages.Page;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class AddNewPersonCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewPersonCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IncorrectDataException, ServletException {

        PersonService personService = new PersonService();
        Optional<String> newName = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWNAME));
        Optional<String> newPhone = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWPHONE));
        Optional<String> newEmail = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWEMAIL));

        if (isEmpty(newName.get()) || isEmpty(newPhone.get()) || isEmpty(newEmail.get())) {
            LOGGER.info("missing parameter for new person in addition mode");
            request.setAttribute(GroupConstant.ERROR_MESSAGE, GroupConstant.ERROR_MESSAGE_TEXT);
        } else {
            Person newperson = new Person(newName.get(), newPhone.get(), newEmail.get());
            personService.save(newperson);
        }
        List<Person> clients = personService.findAll();
        if (!clients.isEmpty()) {
            request.setAttribute(GroupConstant.LISTGROUP, clients);
        }
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}