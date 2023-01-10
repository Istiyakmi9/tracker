package com.bottrack.repositoryinterfaces;

import com.bottrack.model.Menu;

import java.util.ArrayList;

public interface IMenuRepository {

    public String addMenuRepository(Menu menu);
    public String updateMenuRepository(Menu menu, int menuId);
    public ArrayList<Menu> getAllMenuRepository();
    public ArrayList<Menu> getMenuByMenuIdRepository(int menuId);
    public String deleteMenuByMenuIdRepository(int menuId);

}
