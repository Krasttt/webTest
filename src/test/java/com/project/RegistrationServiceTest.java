package com.project;

import com.project.Sevices.RegistrationService;
import com.project.domain.UserAccount;
import com.project.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RegistrationServiceTest {
    @Autowired
    private RegistrationService registrationService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void addUserTest() throws Exception{
        UserAccount user = new UserAccount();
        user.setPassword("password");
        boolean isUserCreated = registrationService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(user.isActive());

        Mockito.verify(userRepository,Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFailTest(){
        UserAccount user = new UserAccount();
        user.setUsername("John");
        Mockito.doReturn(new UserAccount())
                .when(userRepository)
                .findByUsername("John");
        boolean isUserCreated = registrationService.addUser(user);

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }
}
