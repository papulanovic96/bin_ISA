package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.User;
import com.bin448.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class AirlineTesting {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void metoda() {
        User user = new User();
        user.setUsername("Jeja");
        user.setReservedTicket(null);
        user.setRequests(null);
        user.setFriends(null);
        user.setTelephone("0655053892");
        user.setEmail("jela@gmail.com");
        user.setCity("Kikinda");
        user.setLastName("Ljukovcan");
        user.setName("Jelena");
        user.setRole("ROLE_USER");
        user.setActive(true);
        user.setPassword("$2a$10$cLWLw6F9mUmkZ/yAlU8eK.MAqrTDCYCYkCXSULmR8YM42f7D3OaSO");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByUsername(user.getUsername());

        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }
}
