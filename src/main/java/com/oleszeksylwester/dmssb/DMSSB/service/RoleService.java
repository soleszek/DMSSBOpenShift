package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRole(String role){
        return roleRepository.findByRole(role);
    }

    @Transactional
    public void SaveOrUpdate(Role role){
        roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public Role findById(Long id){
        return roleRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no role with this id"));
    }

    @Transactional(readOnly = true)
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }

    @Transactional
    public void delete(Role role){
        roleRepository.delete(role);
    }
}
