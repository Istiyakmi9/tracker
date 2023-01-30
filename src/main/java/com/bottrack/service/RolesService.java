package com.bottrack.service;

import com.bottrack.model.Roles;
import com.bottrack.repository.RolesRepository;
import com.bottrack.serviceinterfaces.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RolesService implements IRolesService {

    @Autowired
    RolesRepository rolesRepository;


    public String addRoleService(Roles roles) {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        roles.setCreatedOn(date);
        var result = this.rolesRepository.save(roles);
        return "New Role has been added";
    }

    public ResponseEntity<Object> updateRoleService(Roles roles, int roleId) {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        roles.setCreatedOn(date);
        Optional<Roles> result = this.rolesRepository.findById(roleId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        roles.setRoleId(roleId);
        rolesRepository.save(roles);
        return ResponseEntity.noContent().build();
    }

    public ArrayList<Roles> getAllRolesService() {
        var result = this.rolesRepository.findAll();
        return (ArrayList<Roles>) result;
    }

    public Optional<Roles> getRolesByIdService(int roleId) {
       var result =  this.rolesRepository.findById(roleId);
        return result;
    }

    public String deleteRolesByRoleIdService(int roleId) {
        this.rolesRepository.deleteById(roleId);
        return "role record has been deleted";
    }
}
