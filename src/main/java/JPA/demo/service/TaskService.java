package JPA.demo.service;

import JPA.demo.entity.Operators;
import JPA.demo.repository.OperatorRepository;
import JPA.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import JPA.demo.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private OperatorRepository operatorRepository;


    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }
    public Task removeTaskOperator(int id, int idOfOperator) {
        Task task = taskRepository.findById(id).orElse(null);
        List<Operators> operators = task.getOperators();
        operators.remove(operatorRepository.findById(idOfOperator).orElse(null));
        task.setOperators(operators);
        return taskRepository.save(task);
    }
    public List<Task> searchTask(String key, String order) {
        List<Task> task = null;
        if(order!= null) {
            if(order.equals("desc")) {
                task = taskRepository.getTaskByDesc("%"+ key.toLowerCase() + "%");
            }
            else{
                task = taskRepository.getTaskByAsc("%" + key.toLowerCase() + "%");
            }
        }
        return task;
    }

    public Task updateTask(int id,ArrayList<Integer>idOfOperators) {
        Task task = taskRepository.findById(id).orElse(null);
        task.setHandled(true);
        ArrayList<Operators> ids=new ArrayList<>();
        for (Integer op:idOfOperators) {
            ids.add(operatorRepository.findById(op).orElse(null));
        }
        task.setOperators(ids);
        return taskRepository.save(task);
    }
    public List<Task> getUncheckedTasks (){
        return taskRepository.findTaskByHandledIsFalse();
    }
    public List<Task> getCheckedTasks () {
        return taskRepository.findTaskByHandledIsTrue();
    }
    public void deleteTask(Task task){
         taskRepository.delete(task);
    }
    public Task addTask(Task task) {
       return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
