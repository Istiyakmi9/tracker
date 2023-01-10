package com.bottrack.repositoryinterfaces;

import com.bottrack.model.Roles;

import java.util.ArrayList;

public interface IRolesRepository {

    public String addRoleRepository(Roles roles);
    public String updateRoleRepository(Roles roles, int roleId);
    public ArrayList<Roles> getAllRolesRepository();
    public ArrayList<Roles> getRolesByIdRepository(int roleId);
    public String deleteRolesByRoleIdRepository(int roleId);
}
