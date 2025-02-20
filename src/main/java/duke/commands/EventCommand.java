package duke.commands;

import duke.exceptions.DukeDateException;
import duke.exceptions.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a class that creates Event Task and adds it to the list
 */
public class EventCommand extends Command {

    public EventCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Creates a new Event Task and adds it to the list
     *
     * @param parsedInput
     * @return Response to be displayed
     * @throws DukeException
     */
    public String addEvent(ArrayList<String> parsedInput) throws DukeException {
        try {
            Event newEvent = new Event(parsedInput.get(1), parsedInput.get(2), false);
            assert newEvent.isEvent() : "Task should be an Event!";
            taskList.addTask(newEvent);
            storage.saveTask(taskList);
            return ui.printAddedTask(newEvent, taskList);
        } catch (DukeDateException e) {
            return e.getMessage();
        }
    }
}
