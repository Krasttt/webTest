package com.project;

import com.project.Sevices.impl.RegistrationServiceImpl;
import com.project.controllers.MainController;
import com.project.domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class LoginTest {
    @Autowired
    private MainController mainController;

    @Autowired
    private RegistrationServiceImpl registrationServiceImpl;
    @Before
    public void init(){
        UserAccount user = new UserAccount();
        user.setUsername("testAdmin");
        user.setFirstName("testAdmin");
        user.setSurName("testAdmin");
        user.setPassword("testPassword");
        registrationServiceImpl.addUser(user);

    }
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to Tester")));

    }

    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("testAdmin").password("testPassword"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(formLogin().user("error").password("error"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
