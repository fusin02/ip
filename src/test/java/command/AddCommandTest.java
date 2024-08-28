package command;

import exception.DudeException;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.TaskList;
import task.Deadline;
import task.Event;
import task.Todo;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommandTest {

    @Test
    public void addTodo() throws DudeException {
        TaskList tasks = new TaskList();
        AddCommand adder = new AddCommand(new Todo("read book"));
        adder.execute(tasks, new Ui(), new Storage("test.txt"));
        assert (tasks.getSize() == 1 && tasks.getTask(0).toString().equals("[T][ ] read book"));
    }

    @Test
    public void addDeadline() throws DudeException {
        TaskList tasks = new TaskList();
        AddCommand adder = new AddCommand(new Deadline("return book",
                    LocalDateTime.parse("2021-08-24 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        adder.execute(tasks, new Ui(), new Storage("test.txt"));
        assert (tasks.getSize() == 1 && tasks.getTask(0).toString().equals("[D][ ] return book (by: Aug 24 2021 12:00)"));
    }

    @Test
    public void addEvent() throws DudeException {
        TaskList tasks = new TaskList();
        AddCommand adder = new AddCommand(new Event("meeting",
                LocalDateTime.parse("2021-08-24 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2021-08-24 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        adder.execute(tasks, new Ui(), new Storage("test.txt"));
        assert (tasks.getSize() == 1 && tasks.getTask(0).toString().equals("[E][ ] meeting (from: Aug 24 2021 12:00 to: Aug 24 2021 12:30)"));
    }
}
