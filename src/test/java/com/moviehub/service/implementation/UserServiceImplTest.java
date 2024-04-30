package com.moviehub.service.implementation;

import com.moviehub.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    public static void setup() {
        System.setProperty("skipConsoleApplication", "true");
    }

    @Test
    public void testRegisterUser() {
        User user = userService.registerUser("firoze@gmail.com");
        assertNotNull(user);
        assertEquals("firoze@gmail.com", user.getEmail());
    }


}
