package com.bottrack.controller;

import com.bottrack.model.Menu;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @PostMapping("/addMenu")
    public ResponseModal addMenu(@RequestBody Menu menu){
        var result = this.menuService.addMenuService(menu);
        return BuildOk(result);
    }

    @PutMapping("/updateMenu/{menuId}")
    public ResponseModal updateMenu(@RequestBody Menu menu, @PathVariable("menuId") int menuId) throws IOException {
        var result = this.menuService.updateMenuService(menu, menuId);
        return BuildOk(result);
    }

    @GetMapping("/getAllMenu")
    public ResponseModal getAllMenu(){
        var result = this.menuService.getAllMenuService();
        return BuildOk(result);
    }

    @GetMapping("/getMenuByMenuId/{menuId}")
    public ResponseModal getMenuByMenuId(@PathVariable ("menuId") int menuId){
        var result = this.menuService.getMenuByMenuIdService(menuId);
        return BuildOk(result);
    }

    @DeleteMapping("/deleteMenuByMenuId/{menuId}")
    public ResponseModal deleteMenuByMenuId(@PathVariable ("menuId") int menuId){
        var result = this.menuService.deleteMenuByMenuIdService(menuId);
        return BuildOk(result);
    }




}
