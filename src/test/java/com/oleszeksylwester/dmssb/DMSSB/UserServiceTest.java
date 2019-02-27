package com.oleszeksylwester.dmssb.DMSSB;

import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    User test = new User();
    List<User> users = new ArrayList<>();

    @Before
    public void setup() {

        Role role = new Role();
        role.setRole("ADMIN");
        Set<Role> roles = new HashSet<>();

        test.setUser_id(1L);
        test.setName("U-2019-000001");
        test.setFirstName("Test");
        test.setLastName("User");
        test.setUsername("test");
        test.setPassword("Rzeszów1983@");
        test.setEnabled("1");
        test.setRoles(roles);

        users.add(test);
    }

    @Test
    public void whenUserLoginIsProvided_thenRetrieveUserObject() {
        Mockito.when(userRepository.findByUsername("test")).thenReturn(test);
        userService = new UserService(userRepository, null, null);
        User user = userService.findByUsername("test");
        Assert.assertEquals("Test", user.getFirstName());
    }

    @Test
    public void testFindAll(){
        Mockito.when(userRepository.findAll()).thenReturn(users);
        userService = new UserService(userRepository, null, null);
        List<User> allUsers = userService.findAll();
        Assert.assertEquals("test", allUsers.get(0).getUsername());
    }

*//*    @Test
    public void testsaveOrUpdate(){
        Mockito.when(userRepository.save(test)).thenReturn(test);
        userService = new UserServiceImpl(userRepository, null, null);
        User user = userService.;
        userRepository.
    }*//*

*//*    @public void whenUserIdIsProvided_thenRetriveUserObject(){
        Mockito.when(userRepository.findById(1L)).thenReturn(test);
    }*//*
}*/
