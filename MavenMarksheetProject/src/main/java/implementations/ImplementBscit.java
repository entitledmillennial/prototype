/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import configurations.Template;
import interfaces.InterfaceBscit;
import models.Bscit;
import java.util.List;
import javafx.scene.control.Alert;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhee
 */
@Repository
public class ImplementBscit implements InterfaceBscit{
    @Autowired
    ConfigSessionFactory csf;
    
    @Override
    public List<Bscit> select() {//Can be done with CriteriaBuilder.
        return csf.getSf().openSession().createCriteria(Bscit.class).list();
    }
    
    @Override
    public List<Bscit> select(String sem) {
        return csf.getSf().openSession().createCriteria(Bscit.class).add(Restrictions.eq("sem", sem)).list();
    }
     
    
    @Override
    public void saveOrUpdate(Bscit b) {
        if (b.getRollno()==null) {
            csf.create(b);
        }else{
            csf.update(b);
        }
    }
    
    @Override
    public void delete(Bscit b) {
        try {
        	/*
            Query q = csf.getSf().openSession().createQuery("delete from Bscit where rollno = :rollno");
            q.setParameter("rollno", b.getRollno());
            q.executeUpdate();*/
        	csf.delete(b);
            Template.dialog(Alert.AlertType.INFORMATION, "Success Delete");
        } catch (Exception e) {
            e.printStackTrace();
            Template.dialog(Alert.AlertType.INFORMATION, "Sorry, Can't Delete This Record");
        }
    }
   
}
