package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
     }


    @Override
    public void createUsersTable() {
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS mytest.users" +
                    " (id int not null auto_increment, name VARCHAR(40), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS mytest.users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        //System.out.println("111");
        try {
            User user = new User(name, lastName,age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id1) {
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User where id =" + id1).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> resulUser = new ArrayList<>();
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        try {
            session.beginTransaction();
            resulUser= session.createQuery("FROM User")
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            return resulUser;
        }
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory ss = Util.getSessionFactory();
        Session session = ss.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
