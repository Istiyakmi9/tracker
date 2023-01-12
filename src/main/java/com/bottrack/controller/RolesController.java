package com.bottrack.controller;

import com.bottrack.model.Roles;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/roles")
public class RolesController extends BaseController {

    @Autowired
    RolesService rolesService;

    @PostMapping("/addRole")
    public ResponseModal addRole(@RequestBody Roles roles){
        var result = this.rolesService.addRoleService(roles);
        return BuildOk(result);
    }

    @PutMapping("/updateRole/{roleId}")
    public ResponseModal updateRole(@RequestBody Roles roles, @PathVariable("roleId") int roleId) throws IOException {
        var result = this.rolesService.updateRoleService(roles, roleId);
        return BuildOk(result);
    }

    @GetMapping("/getAllRoles")
    public ResponseModal getAllRoles(){
        var result = this.rolesService.getAllRolesService();
        return BuildOk(result);
    }

    @GetMapping("/getRolesByRoleId/{roleId}")
    public ResponseModal getRolesByRoleId(@PathVariable("roleId") int roleId){
        var result = this.rolesService.getRolesByIdService(roleId);
        return BuildOk(result);
    }

    @DeleteMapping("/deleteRolesByRoleId/{roleId}")
    public ResponseModal deleteRolesByRoleId(@PathVariable("roleId") int roleId){
        var result = this.rolesService.deleteRolesByRoleIdService(roleId);
        return BuildOk(result);
    }
}
