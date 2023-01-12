package com.bottrack.serviceinterfaces;

import com.bottrack.model.Roles;

import java.io.IOException;
import java.util.ArrayList;

public interface IRolesService {

    public String addRoleService(Roles roles);
    public String updateRoleService(Roles roles, int roleId) throws IOException;
    public ArrayList<Roles> getAllRolesService();
    public ArrayList<Roles> getRolesByIdService(int roleId);
    public String deleteRolesByRoleIdService(int roleId);

}
