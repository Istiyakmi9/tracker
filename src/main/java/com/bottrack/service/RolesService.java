package com.bottrack.service;

import com.bottrack.model.Roles;
import com.bottrack.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;


    public String addRoleService(Roles roles) {
        var result = this.rolesRepository.addRoleRepository(roles);
        return result;
    }

    public String updateRoleService(Roles roles, int roleId) throws IOException {
        var result = "";
        if (roleId > 0){
            result = this.rolesRepository.updateRoleRepository(roles, roleId);
            if (result == null || result == "")
                throw new IOException("Unable to update Roles");
        }

        return result;
    }

    public ArrayList<Roles> getAllRolesService() {
        var result = this.rolesRepository.getAllRolesRepository();
        return result;
    }

    public ArrayList<Roles> getRolesByIdService(int roleId) {
       var result =  this.rolesRepository.getRolesByIdRepository(roleId);
        return result;
    }

    public String deleteRolesByRoleIdService(int roleId) {
        var result = this.rolesRepository.deleteRolesByRoleIdRepository(roleId);
        return result;
    }
}
