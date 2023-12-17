package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.Roles;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/roles")
public class RolesController extends BaseController {

    @Autowired
    RolesService rolesService;

    @PostMapping("/addRole")
    public ResponseEntity<ApiResponse> addRole(@RequestBody Roles roles){
        var result = this.rolesService.addRoleService(roles);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateRole/{roleId}")
    public ResponseEntity<ApiResponse> updateRole(@RequestBody Roles roles, @PathVariable("roleId") int roleId) throws IOException {
        var result = this.rolesService.updateRoleService(roles, roleId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<ApiResponse> getAllRoles(){
        var result = this.rolesService.getAllRolesService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getRolesByRoleId/{roleId}")
    public ResponseEntity<ApiResponse> getRolesByRoleId(@PathVariable("roleId") int roleId){
        var result = this.rolesService.getRolesByIdService(roleId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteRolesByRoleId/{roleId}")
    public ResponseEntity<ApiResponse> deleteRolesByRoleId(@PathVariable("roleId") int roleId){
        var result = this.rolesService.deleteRolesByRoleIdService(roleId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
