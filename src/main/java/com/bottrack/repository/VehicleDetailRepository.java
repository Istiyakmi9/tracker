package com.bottrack.repository;


import com.bottrack.model.Roles;
import com.bottrack.model.VehicleDetail;
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
public class VehicleDetailRepository {


    public String addVehicleDetailRepository(VehicleDetail vehicleDetail) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_VehicleDetail_InsUpdate");
            query.registerParameter("_VehicleNo", long.class, ParameterMode.IN).bindValue(vehicleDetail.getVehicleNo());
            query.registerParameter("_Make", String.class, ParameterMode.IN).bindValue(vehicleDetail.getMake());
            query.registerParameter("_Model", String.class, ParameterMode.IN).bindValue(vehicleDetail.getModel());
            query.registerParameter("_Varient", String.class, ParameterMode.IN).bindValue(vehicleDetail.getVarient());
            query.registerParameter("_VehicleType", String.class, ParameterMode.IN).bindValue(vehicleDetail.getVehicleType());
            query.registerParameter("_Series", String.class, ParameterMode.IN).bindValue(vehicleDetail.getSeries());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(vehicleDetail.getAdminId());
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
        return "Data has been  added in VehicleDetail";

    }

    public String updateVehicleDetailByVehicleNoRepository(VehicleDetail vehicleDetail, long vehicleNo) {
        int rowCount = 0;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            ProcedureCall query = session.createStoredProcedureCall("sp_VehicleDetail_InsUpdate");
            query.registerParameter("_VehicleNo", long.class, ParameterMode.IN).bindValue(vehicleDetail.getVehicleNo());
            query.registerParameter("_Make", String.class, ParameterMode.IN).bindValue(vehicleDetail.getMake());
            query.registerParameter("_Model", String.class, ParameterMode.IN).bindValue(vehicleDetail.getModel());
            query.registerParameter("_Varient", String.class, ParameterMode.IN).bindValue(vehicleDetail.getVarient());
            query.registerParameter("_VehicleType", String.class, ParameterMode.IN).bindValue(vehicleDetail.getVehicleType());
            query.registerParameter("_Series", String.class, ParameterMode.IN).bindValue(vehicleDetail.getSeries());
            query.registerParameter("_AdminId", long.class, ParameterMode.IN).bindValue(vehicleDetail.getAdminId());
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
        return "VehicleDetail data has been updated";
    }

    public ArrayList<VehicleDetail> getAllVehicleDetailRepository() {
        List<VehicleDetail> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<VehicleDetail> query = session.createSQLQuery("Call sp_VehicleDetail_getAll").addEntity(VehicleDetail.class);
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
        return (ArrayList<VehicleDetail>) result;
    }

    public ArrayList<VehicleDetail> getVehicleDetailByVehicleNoRepository(long vehicleNo) {
        List<VehicleDetail> result = null;
        Transaction tx = null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<VehicleDetail> query = session.createSQLQuery("Call sp_VehicleDetail_getByVehicleNo(:vehicleNo)").addEntity(VehicleDetail.class);
            query.setParameter("vehicleNo", vehicleNo);
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
        return (ArrayList<VehicleDetail>) result;
    }

    public String deleteVehicleDetailByVehicleNoRepository(long vehicleNo) {
        int result = 0;
        Transaction tx =null;
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        System.out.println(factory);
        try(Session session = factory.openSession()){
            tx = session.beginTransaction();
            Query<VehicleDetail> query = session.createSQLQuery("Call sp_VehicleDetail_deleteByVehicleNo(:vehicleNo)").addEntity(VehicleDetail.class);
            query.setParameter("vehicleNo", vehicleNo);
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
        return "Data has been deleted from VehicleDetail";
    }
}
