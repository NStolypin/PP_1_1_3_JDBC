package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDaoHibernateImpl implements UserDao {
    private String querySql;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserTable");
        emf.close();
        //Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //Transaction tx1 = session.beginTransaction();
        //session.;
        //tx1.commit();
        //session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        querySql = "DROP TABLE IF EXISTS USER_TABLE";
        session.createNativeQuery(querySql).executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
