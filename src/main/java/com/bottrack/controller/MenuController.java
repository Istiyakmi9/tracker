package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.Menu;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @PostMapping("/addMenu")
    public ResponseEntity<ApiResponse> addMenu(@RequestBody Menu menu){
        var result = this.menuService.addMenuService(menu);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateMenu/{menuId}")
    public ResponseEntity<ApiResponse> updateMenu(@RequestBody Menu menu, @PathVariable("menuId") int menuId) throws IOException {
        var result = this.menuService.updateMenuService(menu, menuId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllMenu")
    public ResponseEntity<ApiResponse> getAllMenu(){
        var result = this.menuService.getAllMenuService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getMenuByMenuId/{menuId}")
    public ResponseEntity<ApiResponse> getMenuByMenuId(@PathVariable ("menuId") int menuId){
        var result = this.menuService.getMenuByMenuIdService(menuId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteMenuByMenuId/{menuId}")
    public ResponseEntity<ApiResponse> deleteMenuByMenuId(@PathVariable ("menuId") int menuId){
        var result = this.menuService.deleteMenuByMenuIdService(menuId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }




}
