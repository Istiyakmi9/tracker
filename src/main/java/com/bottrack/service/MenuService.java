package com.bottrack.service;

import com.bottrack.model.Menu;
import com.bottrack.repository.MenuRepository;
import com.bottrack.serviceinterfaces.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class MenuService implements IMenuService {

    @Autowired
    MenuRepository menuRepository;

    public String addMenuService(Menu menu) {
        var result = this.menuRepository.save(menu);
        return "New menu has been added";
    }

    public ResponseEntity<Object> updateMenuService(Menu menu, int menuId) {
        Optional<Menu> result = this.menuRepository.findById(menuId);
        if (result.isEmpty())
           return ResponseEntity.notFound().build();

        menu.setMenuId(menuId);
       menuRepository.save(menu);
        return ResponseEntity.noContent().build();
    }


    public ArrayList<Menu> getAllMenuService() {
        var result = this.menuRepository.findAll();
        return (ArrayList<Menu>) result;
    }

    public Optional<Menu> getMenuByMenuIdService(int menuId) {
        var result = this.menuRepository.findById(menuId);
        return result;
    }

    public String deleteMenuByMenuIdService(int menuId) {
        this.menuRepository.deleteById(menuId);
        return "menu record has been deleted";
    }
}
