package com.project;

import com.project.Sevices.UserService;
import com.project.domain.Role;
import com.project.domain.UserAccount;
import com.project.repositories.RoleRepository;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void editUserInfoTest() {
        UserAccount user = new UserAccount();
        user.setFirstName("firstName");
        user.setSurName("surName");
        userService.editUserInfo("newFirstName", "newSurName", user);
        Assert.assertTrue(user.getFirstName().equals("newFirstName"));
        Assert.assertTrue(user.getSurName().equals("newSurName"));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }


    @Test
    public void setAdminTest() {
        UserAccount user = new UserAccount();
        user.setId(1);

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(user.getId());

        Role role = new Role();
        role.setType("admin");
        Mockito.doReturn(role)
                .when(roleRepository)
                .findByType("admin");

        userService.setAdmin(user.getId());

        Assert.assertTrue(user.getRole().equals("admin"));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

}
