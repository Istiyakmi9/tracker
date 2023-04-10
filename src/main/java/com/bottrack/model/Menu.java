package com.bottrack.model;


import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "MenuId")
    int menuId;

    @Column(name = "Category")
    String category;

    @Column(name = "SubCategory")
    String subCategory;

    @Column(name = "Icon")
    String icon;

    @Column(name = "Link")
    String link;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Menu(int menuId, String category, String subCategory, String icon, String link) {
        this.menuId = menuId;
        this.category = category;
        this.subCategory = subCategory;
        this.icon = icon;
        this.link = link;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
