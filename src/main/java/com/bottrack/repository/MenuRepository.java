package com.bottrack.repository;

import com.bottrack.model.Menu;
import com.bottrack.model.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuRepository {


    public String addMenuRepository(Menu menu) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_Menu_InsUpdate");
            query.registerParameter("_MenuId", int.class, ParameterMode.IN).bindValue(menu.getMenuId());
            query.registerParameter("_Category", String.class, ParameterMode.IN).bindValue(menu.getCategory());
            query.registerParameter("_SubCategory", String.class, ParameterMode.IN).bindValue(menu.getSubCategory());
            query.registerParameter("_Icon", String.class, ParameterMode.IN).bindValue(menu.getIcon());
            query.registerParameter("_Link", String.class, ParameterMode.IN).bindValue(menu.getLink());
            query.registerParameter("_ProcessingResult", String.class, ParameterMode.OUT);
            String result = query.getOutputParameterValue("_ProcessingResult").toString();
            rowCount = query.executeUpdate();
            System.out.println("Status" + result);
            tx.commit();
            session.close();
        }   catch(Exception e){
            System.out.println(e.getMessage());
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return "Menu data has been added successfully";
    }

    public String updateMenuRepository(Menu menu, long menuId) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_Menu_InsUpdate");
            query.registerParameter("_MenuId", int.class, ParameterMode.IN).bindValue(menu.getMenuId());
            query.registerParameter("_Category", String.class, ParameterMode.IN).bindValue(menu.getCategory());
            query.registerParameter("_SubCategory", String.class, ParameterMode.IN).bindValue(menu.getSubCategory());
            query.registerParameter("_Icon", String.class, ParameterMode.IN).bindValue(menu.getIcon());
            query.registerParameter("_Link", String.class, ParameterMode.IN).bindValue(menu.getLink());
            query.registerParameter("_ProcessingResult", String.class, ParameterMode.OUT);
            String result = query.getOutputParameterValue("_ProcessingResult").toString();
            rowCount = query.executeUpdate();
            System.out.println("Status" + result);
            tx.commit();
            session.close();
        }   catch(Exception e){
            System.out.println(e.getMessage());
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return "Menu data has been updated";
    }


    public ArrayList<Menu> getAllMenuRepository() {
        List<Menu> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<Menu> query = session.createSQLQuery("Call sp_Menu_getAll").addEntity(Menu.class);
            result = query.list();
            System.out.println(result);
            tx.commit();
            session.close();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            if (tx != null){
                tx.rollback();
            }
        }
        return (ArrayList<Menu>) result;
    }

    public ArrayList<Menu> getMenuByMenuIdRepository(int menuId) {
        List<Menu> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<Menu> query = session.createSQLQuery("Call sp_Menu_getByMenuId(:menuId)").addEntity(Menu.class);
            query.setParameter("menuId", menuId);
            result = query.list();
            System.out.println(result);
            tx.commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            if (tx != null){
                tx.rollback();
            }
        }
        return (ArrayList<Menu>) result;
    }

    public String deleteMenuByMenuIdRepository(int menuId) {
        int result = 0;
        Transaction tx =null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()){
            tx = session.beginTransaction();
            Query<Menu> query = session.createSQLQuery("Call sp_Menu_deleteByMenuId(:menuId)").addEntity(Menu.class);
            query.setParameter("menuId", menuId);
            result = query.executeUpdate();
            System.out.println("Row affected" + result);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(tx!=null)
            {
                tx.rollback();
            }
        }
        return "Menu data has been deleted";
    }
}
