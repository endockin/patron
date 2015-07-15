package com.endockin.patron.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endockin.patron.config.TestConfiguration;
import com.endockin.patron.model.Fleet;
import com.endockin.patron.model.User;
import com.endockin.patron.repository.FleetRepository;
import com.endockin.patron.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
@Transactional
public class FleetTest {
  private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FleetTest.class);

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private FleetRepository fleetRepo;

  @Test
  @Rollback(true)
  public void getFleet() {
    User u = new User();
    u.setEmail("endockin@endava.com");
    u.setPassword("mycoolP");
    User newU = userRepo.findByEmail(u.getEmail());
    LOG.info("user: " + newU);

    List<Fleet> fleets = fleetRepo.findByUser(newU);

    LOG.info("fleets: " + fleets);
  }

}
