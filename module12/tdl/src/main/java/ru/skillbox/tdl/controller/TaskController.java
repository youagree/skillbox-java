package ru.skillbox.tdl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.tdl.entity.Task;
import ru.skillbox.tdl.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getListOfTasks(Model model){
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("task", tasks);
        return "index";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/edit")
    public String addTask(@ModelAttribute(value = "task") Task task) {
        taskService.saveOrUpdate(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(Model model, @PathVariable(value = "id") Long id) {
        Task task = taskService.getById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @GetMapping("/show/{id}")
    public String showOneTask(Model model, @PathVariable(value = "id") Long id) {
        Task task = taskService.getById(id);
        model.addAttribute("task", task);
        return "task-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable(value = "id") Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
