
package rs.fon.ordinacija.baza;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.ordinacija.modeli.Pacijent;

@Repository
public class DBBroker {

    @Autowired
    private SessionFactory sessionFactory;
    
//    @Autowired
//    private EntityManager em;

    public DBBroker() {

    }

    public DBBroker(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Object> pretraga(Class model) {
        return sessionFactory.getCurrentSession().createCriteria(model).list();
    }

    @Transactional
    public Object ucitaj(Class model, int id) {
        try {
            return sessionFactory.getCurrentSession().get(model, id);
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        return null;
    }
    
   
    public Object ucitajPacijenta(Class model, int id) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("from Pacijent where id = :id")
                    .setParameter("id", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
        return null;
    }

    @Transactional
    public void sacuvaj(Object object) {
        sessionFactory.getCurrentSession().save(object);
        
    }

    @Transactional
    public void izmeni(Object object) {
        sessionFactory.getCurrentSession().merge(object);
    }

    @Transactional
    public void obrisi(Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }
   @Transactional
    public List<Pacijent> pretragaPacijenata(String pretraga) {
        String[] searchItems = pretraga.split(" ");
        List<Pacijent> list= new ArrayList<>();
//    try {
//        String sqlQuery = "SELECT * FROM  Pacijent  p WHERE";
//        sqlQuery += " ";
//        for (int i = 0; i < searchItems.length; i++) {
//            if (i != 0) {
//                sqlQuery += " OR ";
//            }
//            sqlQuery += "( " + "lower(p.ime) LIKE '"
//                    + StringUtils.lowerCase("%" + searchItems[i] + "%")
//                    + "' " + "OR lower(p.prezime) LIKE '"
//                    + StringUtils.lowerCase("%" + searchItems[i] + "%")
//                    + "' " + "OR lower(p.jmbg) LIKE '"
//                    + StringUtils.lowerCase("%" + searchItems[i] + "%")
//                    + "' " + ") ";
//        }
////        sqlQuery += " )";
//
//       
//     List<Object> listObject=   sessionFactory.getCurrentSession().createSQLQuery(sqlQuery).list(); 
//        for (int i =0; i<listObject.size(); i ++) {
//        	System.out.println(list.get(i));	
//		    Pacijent p = list.get(i);
//		    System.out.println(p);
//	}
//		return  list;

        try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Pacijent p  where upper(p.ime) like :pretraga")
					.setParameter("pretraga","%"+ pretraga.toUpperCase()+"%");
			list=query.list();
			
			System.out.println(list.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return list;
      
    }

}
