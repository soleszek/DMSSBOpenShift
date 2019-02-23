package com.oleszeksylwester.dmssb.DMSSB;

import com.oleszeksylwester.dmssb.DMSSB.config.StandaloneMvcTestViewResolver;
import com.oleszeksylwester.dmssb.DMSSB.controller.UserController;
import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        final UserController controller = new UserController();

        mockMvc =
                MockMvcBuilders.standaloneSetup(controller)
                        .setViewResolvers(new StandaloneMvcTestViewResolver())
                        .build();

        Role role = new Role();
        role.setRole("ADMIN");
        Set<Role> roles = new HashSet<>();

        User user = new User();
        user.setUser_id(1l);
        user.setName("U-2019-000001");
        user.setFirstName("Sylwester");
        user.setLastName("Oleszek");
        user.setPassword("Rzeszów1983@");
        user.setEnabled("1");
        user.setRoles(roles);
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "ADMIN")
    public void showDashboardTest() throws Exception{
        this.mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "ADMIN")
    public void showLoginTest() throws Exception{
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "ADMIN")
    public void showAdminTest() throws Exception{
        this.mockMvc.perform(get("/adminpanel"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminpanel"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "ADMIN")
    public void showRegistrationTest() throws Exception{
        this.mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(MockMvcResultMatchers.view().name("registration"))
                .andDo(print());
    }

    /*@Test
    @WithMockUser(username = "test", password = "test", roles = "ADMIN")
    public void registerUserTest() throws Exception{
        assertThat(this.userService).isNotNull();
        this.mockMvc.perform(get("/registerUser"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", is))
                .andExpect(view().name("registration"))
                .andExpect(MockMvcResultMatchers.view().name("registration"))
                .andDo(print());
    }*/



}
