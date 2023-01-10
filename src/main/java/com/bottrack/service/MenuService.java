package com.bottrack.service;

import com.bottrack.model.Menu;
import com.bottrack.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public String addMenuService(Menu menu) {
        var result = this.menuRepository.addMenuRepository(menu);
        return result;
    }

    public String updateMenuService(Menu menu, int menuId) throws IOException {
        var result = "";
        if (menuId > 0){
            result = this.menuRepository.updateMenuRepository(menu, menuId);
            if (result == null || result == "")
                throw new IOException("Unable to update Menu data");
        }
        return result;
    }


    public ArrayList<Menu> getAllMenuService() {
        var result = this.menuRepository.getAllMenuRepository();
        return result;
    }

    public ArrayList<Menu> getMenuByMenuIdService(int menuId) {
        var result = this.menuRepository.getMenuByMenuIdRepository(menuId);
        return result;
    }

    public String deleteMenuByMenuIdService(int menuId) {
        var result = this.menuRepository.deleteMenuByMenuIdRepository(menuId);
        return result;
    }
}
