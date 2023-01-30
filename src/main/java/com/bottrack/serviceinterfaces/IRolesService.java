package com.bottrack.serviceinterfaces;

import com.bottrack.model.Roles;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public interface IRolesService {

    public String addRoleService(Roles roles);
    public ResponseEntity<Object> updateRoleService(Roles roles, int roleId);
    public ArrayList<Roles> getAllRolesService();
    public Optional<Roles> getRolesByIdService(int roleId);
    public String deleteRolesByRoleIdService(int roleId);

}
