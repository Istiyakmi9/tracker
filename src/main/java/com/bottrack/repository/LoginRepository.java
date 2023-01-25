package com.bottrack.repository;

import com.bottrack.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "select l from Login l where l.mobile = :mobile")
    Login getLoginByMobile(@Param("mobile") String mobile);

    @Query(value = "update Login set userName = :userName where mobile = :mobile")
    String updateLoginByUserIdRepository(@Param("userName") String userName, @Param("mobile") String mobile);

  /*  private Transaction tx;

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
        } catch(Exception e) {
            System.out.println(e.getMessage());
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return "Login data updated successfully";
    }

    public Login authenticateUserRepository(String emailOrMobile) throws Exception {
        Login loginDetails;
        tx = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            this.tx = session.beginTransaction();
            Query<Login> context = session.createSQLQuery("Call sp_authenticateuser_by_mobile_or_email(:mobile, :email)")
                    .addEntity(Login.class)
                    .setParameter("mobile", emailOrMobile)
                    .setParameter("email", emailOrMobile);

            loginDetails = context.uniqueResult();

            this.tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        if (loginDetails == null)
            throw new Exception("Invalid user name provided.");

        return loginDetails;
    }*/
}
