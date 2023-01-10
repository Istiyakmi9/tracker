package com.bottrack.repository;

import com.bottrack.model.Login;
import com.bottrack.repositoryinterfaces.ILoginRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;

@Repository
public class LoginRepository implements ILoginRepository {


    public String updateLoginByUserIdRepository(Login login, long userId) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_Login_InsUpdate");
            query.registerParameter("_UserId", long.class, ParameterMode.IN).bindValue(login.getUserId());
            query.registerParameter("_UserName", String.class, ParameterMode.IN).bindValue(login.getUserName());
            query.registerParameter("_Mobile", String.class, ParameterMode.IN).bindValue(login.getMobile());
            query.registerParameter("_Email", String.class, ParameterMode.IN).bindValue(login.getEmail());
            query.registerParameter("_Password", String.class, ParameterMode.IN).bindValue(login.getPassword());
            query.registerParameter("_RoleId", int.class, ParameterMode.IN).bindValue(login.getRoleId());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(login.getAdminId());
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
        return "Login data updated successfully";
    }
}
