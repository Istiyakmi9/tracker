package com.bottrack.repository;

import com.bottrack.model.Roles;
import com.bottrack.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RolesRepository {

    public String addRoleRepository(Roles roles) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_Roles_InsUpdate");
            query.registerParameter("_RoleId", int.class, ParameterMode.IN).bindValue(roles.getRoleId());
            query.registerParameter("_RoleName", String.class, ParameterMode.IN).bindValue(roles.getRoleName());
            query.registerParameter("_Description", String.class, ParameterMode.IN).bindValue(roles.getDescription());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(roles.getAdminId());
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
        return "Roles added successfully";
    }

    public String updateRoleRepository(Roles roles, long roleId) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_Roles_InsUpdate");
            query.registerParameter("_RoleId", int.class, ParameterMode.IN).bindValue(roles.getRoleId());
            query.registerParameter("_RoleName", String.class, ParameterMode.IN).bindValue(roles.getRoleName());
            query.registerParameter("_Description", String.class, ParameterMode.IN).bindValue(roles.getDescription());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(roles.getAdminId());
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
        return "Roles data updated successfully";
    }

    public ArrayList<Roles> getAllRolesRepository() {
        List<Roles> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<Roles> query = session.createSQLQuery("Call sp_Roles_getAll").addEntity(Roles.class);
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
        return (ArrayList<Roles>) result;
    }


    public ArrayList<Roles> getRolesByIdRepository(int roleId) {
        List<Roles> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<Roles> query = session.createSQLQuery("Call sp_Roles_getByRoleId(:roleId)").addEntity(Roles.class);
            query.setParameter("roleId", roleId);
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
        return (ArrayList<Roles>) result;
    }


    public String deleteRolesByRoleIdRepository(int roleId) {
        int result = 0;
        Transaction tx =null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()){
            tx = session.beginTransaction();
            Query<Roles> query = session.createSQLQuery("Call sp_Roles_deletetByRoleId(:roleId)").addEntity(Roles.class);
            query.setParameter("roleId", roleId);
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
        return "Roles data has been deleted";
    }
}
