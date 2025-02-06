package com.example.Performance.Focus.Plan_1.cotroller;

import com.example.Performance.Focus.Plan_1.model.User;
import com.example.Performance.Focus.Plan_1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testRegisterUser() {
        Mockito.when(userService.registerUser(any(User.class)))
                .thenReturn(Mono.just(getUser()));

       userController.registerUser(getUser())
               .subscribe(user1 -> {
                   System.out.println(user1+"-----");
                   Assert.notNull(user1,"user");
               });

    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers())
                .thenReturn(Flux.just(getUser()));
        userController.getAllUsers()
                .subscribe(user-> {
                    Assertions.assertNotNull(user, "User should not be null");
                });
    }

    @Test
    public void testDeleteUser() {
        when(userService.deleteUser(any()))
                .thenReturn(Mono.empty());
        userController.deleteUser("12345")
                .subscribe(result -> {
                    Assert.notNull(getUser(),"user");
                });
    }
    @Test
    public void testUpdateUser(){
        when(userService.updateUser(any(), any()))
                .thenReturn(Mono.just(getUser()));
        userController.updateUser(getUser().getId(), getUser())
                .subscribe(updatedUser -> {
                    Assertions.assertNotNull(updatedUser, "User should not be null");
                });
    }
    private User getUser(){
        User user= new User();
        user.setId("id");
        user.setUserEmail("email");
        user.setUserName("username");
        return user;
    }
}