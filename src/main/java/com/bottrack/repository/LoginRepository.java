package com.bottrack.repository;

import com.bottrack.model.Login;
import com.bottrack.model.User;
import com.bottrack.repositoryinterfaces.ILoginRepository;
import com.bottrack.repositorymodel.UserDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LoginRepository implements ILoginRepository {

    private Transaction tx;

    @PersistenceContext
    private EntityManager entityManager;

    public String updateLoginByUserIdRepository(Login login, long userId) {
        int rowCount = 0;
        tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            this.tx = session.beginTransaction();
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

    public Login authenticateUserRepository(String emailOrMobile) {
        List<Login> loginDetails;
        tx = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            this.tx = session.beginTransaction();
            Query<Login> context = session.createSQLQuery("Call sp_authenticateuser_by_mobile_or_email(:mobile, :email)")
                    .addEntity(Login.class)
                    .setParameter("mobile", "9995577722")
                    .setParameter("email", null);

            loginDetails = context.list();

            this.tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return loginDetails.stream().findFirst().get();
    }
}
