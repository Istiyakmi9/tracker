package com.bottrack.serviceinterfaces;

import com.bottrack.model.Menu;

import java.io.IOException;
import java.util.ArrayList;

public interface IMenuService {

    public String addMenuService(Menu menu);
    public String updateMenuService(Menu menu, int menuId) throws IOException;
    public ArrayList<Menu> getAllMenuService();
    public ArrayList<Menu> getMenuByMenuIdService(int menuId);
    public String deleteMenuByMenuIdService(int menuId);

}
