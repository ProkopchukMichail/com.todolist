package controller;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import entities.Todo;
import repositories.TodoRepository;
import repositories.UserRepository;
import java.util.Collection;

/**
 * Created by andrey on 10.11.15.
 */
@RestController
@Transactional("TransactionManager")
@RequestMapping("/{login}/controller")
public class Controller {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Collection<Todo> getTodoList(@PathVariable String login){
        return todoRepository.findByUserLogin(login);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public void addTodo(@PathVariable String login, @RequestBody Todo todo){
        User user = userRepository.findByLogin(login).orElseGet(() -> {
            User user1 = new User();
            user1.setLogin(login);
            userRepository.save(user1);
            return user1;
        });
        todo.setUser(user);
        todoRepository.save(todo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Transactional
    public void deleteTodo(Todo todo){
        todoRepository.delete(todo);
    }
}