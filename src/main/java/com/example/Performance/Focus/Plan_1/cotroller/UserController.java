package com.example.Performance.Focus.Plan_1.cotroller;

import com.example.Performance.Focus.Plan_1.model.User;
import com.example.Performance.Focus.Plan_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public Mono<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/{id}")                                                                                
    public Mono<User> updateUser(@PathVariable String id,@RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")                                                                              
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
