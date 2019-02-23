package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.RoleRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private NameFactory nameFactory;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, NameFactory nameFactory) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public void saveOrUpdate(User user, String role){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(role);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled("1");

        userRepository.save(user);

        Long userId = user.getUser_id();
        String name = nameFactory.createName(userId, ObjectTypes.USER.getObjectType());
        user.setName(name);

        userRepository.save(user);
    }

    @Transactional
    public User update(Long userId, String firstName, String lastName, String roleName){
        User user = findById(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(roleName);
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no user with this id"));
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> findCheckers(){

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .filter( e -> e.getRoles().stream().anyMatch(r -> r.getRole().equals("CONTRIBUTOR")))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<User> findApprovers(){

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .filter( e -> e.getRoles().stream().anyMatch(r -> r.getRole().equals("MANAGER")))
                .collect(Collectors.toList());
    }

    @Transactional
    public User changeUserStatus(Long userId){
        User user = findById(userId);

        String isEnabled = user.getEnabled();

        if(isEnabled.equals("1")){
            user.setEnabled("0");
        } else {
            user.setEnabled("1");
        }

        return user;
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }
}
