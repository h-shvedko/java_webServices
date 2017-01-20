package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hshvedko on 19.01.2017.
 */
public class UsersDAO {
    private final Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public Integer getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet userModel = (UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        Integer id = userModel.getId();
        return id;
    }

    public long insertUserByName(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }

    public Integer insertUser(String name, String password) throws HibernateException {
        return (Integer) session.save(new UsersDataSet(name, password));
    }
}
