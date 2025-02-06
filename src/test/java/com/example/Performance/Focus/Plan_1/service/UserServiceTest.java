package com.example.Performance.Focus.Plan_1.service;
import com.example.Performance.Focus.Plan_1.model.User;
import com.example.Performance.Focus.Plan_1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        when(userRepository.save(Mockito.any())).thenReturn(Mono.just(getUser()));

        userService.registerUser(getUser())
                .subscribe(
                        Assertions::assertNotNull
                );
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll())
                .thenReturn(Flux.just(getUser()));
        userService.getAllUsers()
                .subscribe(
                        Assertions::assertNotNull
                );
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyString()))
                .thenReturn(Mono.just(getUser()));
        when(userRepository.save(any()))
                .thenReturn(Mono.just(getUser()));
        userService.updateUser(getUser().getId(), getUser())
                .subscribe(
                        Assertions::assertNotNull
                );
    }
    @Test
    public void testUpdateUserError() {
        when(userRepository.findById(anyString()))
                .thenReturn(Mono.empty());
        userService.updateUser(getUser().getId(), getUser())
                .subscribe(result -> {
                    Assertions.fail("Expected NotFoundException, but got a successful result");
                }, error -> {
                    System.out.println("no error");
                });
    }
    @Test
    public void testDeleteUser(){
        when(userRepository.findById(anyString()))
                .thenReturn(Mono.just(getUser()));
        when(userService.deleteUser(anyString()))
                .thenReturn(Mono.empty());
        userService.deleteUser(getUser().getId())
                .subscribe(
                        Assertions::assertNotNull
                );
    }
    @Test
    public void testDeleteUserError(){
        when(userRepository.findById(anyString()))
                .thenReturn(Mono.empty());
        userService.deleteUser(getUser().getId())
                .subscribe(
                        Assertions::assertNotNull
                );
    }

    private User getUser(){
        User user= new User();
        user.setId("id");
        user.setUserEmail("email");
        user.setUserName("username");
        user.setPassword("saurabh");
        return user;
    }

}