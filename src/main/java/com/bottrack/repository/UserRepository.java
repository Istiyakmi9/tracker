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
            query.registerParameter("_UserName", String.class, ParameterMode.IN).bindValue(user.getUserName());
            query.registerParameter("_Password", String.class, ParameterMode.IN).bindValue(user.getPassword());
            query.registerParameter("_FirstName", String.class, ParameterMode.IN).bindValue(user.getFirstName());
            query.registerParameter("_LastName", String.class, ParameterMode.IN).bindValue(user.getLastName());
            query.registerParameter("_Address", String.class, ParameterMode.IN).bindValue(user.getAddress());
            query.registerParameter("_Email", String.class, ParameterMode.IN).bindValue(user.getEmail());
            query.registerParameter("_MobileNumber", String.class, ParameterMode.IN).bindValue(user.getMobileNumber());
            query.registerParameter("_CompanyName", String.class, ParameterMode.IN).bindValue(user.getCompanyName());
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
            query.registerParameter("_UserName", String.class, ParameterMode.IN).bindValue(user.getUserName());
            query.registerParameter("_Password", String.class, ParameterMode.IN).bindValue(user.getPassword());
            query.registerParameter("_FirstName", String.class, ParameterMode.IN).bindValue(user.getFirstName());
            query.registerParameter("_LastName", String.class, ParameterMode.IN).bindValue(user.getLastName());
            query.registerParameter("_Address", String.class, ParameterMode.IN).bindValue(user.getAddress());
            query.registerParameter("_Email", String.class, ParameterMode.IN).bindValue(user.getEmail());
            query.registerParameter("_MobileNumber", String.class, ParameterMode.IN).bindValue(user.getMobileNumber());
            query.registerParameter("_CompanyName", String.class, ParameterMode.IN).bindValue(user.getCompanyName());
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
}
