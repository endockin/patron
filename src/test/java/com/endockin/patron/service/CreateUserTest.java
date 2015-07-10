package com.endockin.patron.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endockin.patron.config.TestConfiguration;
import com.endockin.patron.model.User;
import com.endockin.patron.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
@Transactional
public class CreateUserTest {

  @Autowired
  private UserRepository userRepo;

  @Test
  @Rollback(false)
  public void createUser() {
    User user = new User();
    user.setEmail("endockin@endava.com");
    user.setFirstName("Endockin");
    user.setLastName("Do");
    user.setPassword("mycoolP");

    userRepo.save(user);
  }
}
