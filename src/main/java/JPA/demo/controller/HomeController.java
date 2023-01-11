package JPA.demo.controller;


import JPA.demo.entity.Course;
import JPA.demo.entity.Operators;
import JPA.demo.repository.CourseRepository;
import JPA.demo.repository.OperatorRepository;
import JPA.demo.repository.TaskRepository;
import JPA.demo.service.CourseService;
import JPA.demo.service.OperatorService;
import JPA.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import JPA.demo.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
public class HomeController {


    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private OperatorService operatorService;

    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/home";
    }
    @PostMapping(value = "/add")
    public String addASd (Task task) {
        taskService.addTask(task);
        return "redirect:/home";
    }
    @GetMapping(value = "/details/{id}")
    public String details(Model model, Task task) {

        model.addAttribute("task", taskService.getTaskById(task.getId()));

        model.addAttribute("operators", operatorService.getOperators());
        return "/details";
    }
    @PostMapping(value = "/delete")
    public String delete(Task task) {
       taskService.deleteTask(task);
       return "redirect:/home";
    }
    @GetMapping(value = "/checked")
    public String checked(Model model) {
        model.addAttribute("tasks", taskService.getCheckedTasks());
        return "/checked";
    }
    @GetMapping(value = "/unchecked")
    public String unchecked(Model model) {
        model.addAttribute("tasks", taskService.getUncheckedTasks());
        return "/unchecked";
    }
    @GetMapping(value = "/addtask")
    public String addTask(Model model) {
        model.addAttribute("courses",  courseService.addCourse());
        return "/addtask";
    }
    @PostMapping(value = "/update")
    public String update (  @RequestParam(name = "id") int id,
                            @RequestParam(name = "yes") ArrayList<Integer>idOfOperators) {
        taskService.updateTask(id,idOfOperators);
     return "redirect:/details/"+id;
    }
    @PostMapping(value = "/remove")
    public String remove (  @RequestParam(name = "id") int id,
                            @RequestParam(name = "OperatorId") int idOfOperator) {
    taskService.removeTaskOperator(id, idOfOperator);
        return "redirect:/details/"+id;
    }
    @GetMapping(value = "/search")
    public String search ( @RequestParam(name = "key", required = false) String key,
                           @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                           Model model){
        model.addAttribute("tasks", taskService.searchTask(key, order));
        return "/search";
    }
}
