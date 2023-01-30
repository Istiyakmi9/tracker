package com.bottrack.serviceinterfaces;

import com.bottrack.model.Menu;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public interface IMenuService {

    public String addMenuService(Menu menu);
    public ResponseEntity<Object> updateMenuService(Menu menu, int menuId) throws IOException;
    public ArrayList<Menu> getAllMenuService();
    public Optional<Menu> getMenuByMenuIdService(int menuId);
    public String deleteMenuByMenuIdService(int menuId);

}
