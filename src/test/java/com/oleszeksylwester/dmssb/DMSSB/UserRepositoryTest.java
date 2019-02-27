package com.oleszeksylwester.dmssb.DMSSB;

import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    */
/*@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnUser(){
        //given
        Role role = new Role();
        role.setRole("ADMIN");
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User("U-2018-000001", "Sylwester", "Oleszek","soleszek", "s", "1", roles);
        entityManager.persist(user);
        entityManager.flush();

        //when
        User found = userRepository.findByUsername(user.getUsername());

        //then
        assertThat(found.getUsername())
                .isEqualTo(user.getUsername());
    }*//*

}
*/
