package com.bottrack.repository;
import ch.qos.logback.core.CoreConstants;
import com.bottrack.repositoryinterfaces.IUserRepository;
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
public class UserRepository implements IUserRepository {

    public String addUserRepository(User user) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_User_InsUpdate");
            query.registerParameter("_UserId", long.class, ParameterMode.IN).bindValue(user.getUserId());
            query.registerParameter("_FirstName", String.class, ParameterMode.IN).bindValue(user.getFirstName());
            query.registerParameter("_LastName", String.class, ParameterMode.IN).bindValue(user.getLastName());
            query.registerParameter("_DOB", Date.class, ParameterMode.IN).bindValue(user.getDob());
            query.registerParameter("_Mobile", String.class, ParameterMode.IN).bindValue(user.getMobile());
            query.registerParameter("_Email", String.class, ParameterMode.IN).bindValue(user.getEmail());
            query.registerParameter("_FirstAddress", String.class, ParameterMode.IN).bindValue(user.getFirstAddress());
            query.registerParameter("_SecondAddress", String.class, ParameterMode.IN).bindValue(user.getSecondAddress());
            query.registerParameter("_State", String.class, ParameterMode.IN).bindValue(user.getState());
            query.registerParameter("_City", String.class, ParameterMode.IN).bindValue(user.getCity());
            query.registerParameter("_Country", String.class, ParameterMode.IN).bindValue(user.getCountry());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(user.getAdminId());
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
        return "user added successfully";
    }


    public String updateUserRepository(User user, long userId) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_User_InsUpdate");
            query.registerParameter("_UserId", long.class, ParameterMode.IN).bindValue(user.getUserId());
            query.registerParameter("_FirstName", String.class, ParameterMode.IN).bindValue(user.getFirstName());
            query.registerParameter("_LastName", String.class, ParameterMode.IN).bindValue(user.getLastName());
            query.registerParameter("_DOB", Date.class, ParameterMode.IN).bindValue(user.getDob());
            query.registerParameter("_Mobile", String.class, ParameterMode.IN).bindValue(user.getMobile());
            query.registerParameter("_Email", String.class, ParameterMode.IN).bindValue(user.getEmail());
            query.registerParameter("_FirstAddress", String.class, ParameterMode.IN).bindValue(user.getFirstAddress());
            query.registerParameter("_SecondAddress", String.class, ParameterMode.IN).bindValue(user.getSecondAddress());
            query.registerParameter("_State", String.class, ParameterMode.IN).bindValue(user.getState());
            query.registerParameter("_City", String.class, ParameterMode.IN).bindValue(user.getCity());
            query.registerParameter("_Country", String.class, ParameterMode.IN).bindValue(user.getCountry());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(user.getAdminId());
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
        return "Data updated successfully in User";
    }

    public ArrayList<User> getAllUserRepository() {
        List<User> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<User> query = session.createSQLQuery("Call sp_User_getAll").addEntity(User.class);
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
        return (ArrayList<User>) result;
    }

    public ArrayList<User> getByUserIdRepository(long userId) {
        List<User> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<User> query = session.createSQLQuery("Call sp_User_getByUserId(:userId)").addEntity(User.class);
            query.setParameter("userId", userId);
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
        return (ArrayList<User>) result;
    }

    public String deleteByUserIdRepository(long userId) {
        int result = 0;
        Transaction tx =null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()){
            tx = session.beginTransaction();
            Query<User> query = session.createSQLQuery("Call sp_User_deleteByUserId(:userId)").addEntity(User.class);
            query.setParameter("userId", userId);
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
        return "User Data deleted";
    }
}
