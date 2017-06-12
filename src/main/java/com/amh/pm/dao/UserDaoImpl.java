package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

	@Override
	public void edit(User user) throws RollbackException {

		try {
			entityManager.merge(user);
		} catch (NoResultException e) {
			System.out.println(e);
		}

	}

	@Override
	public void delete(int id) {
		User user = findById(id);
		entityManager.remove(user);
		
	}

	@Override
	public List<User> userByName(String name, String password) {
		Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.name=? AND u.password=?");    
		q.setParameter(1, name);
		q.setParameter(2, password);
        List<User> userNameList = q.getResultList();
		return userNameList;
	}

	@Override
	public User findUserIdByName(String name) {		

		User u = null;
		try {
			Query q = entityManager.createQuery("select u from User u WHERE u.name=?");
			q.setParameter(1, name);
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		}
		return u;
	}

	@Override
	public List<User> findUserNameByOrgnId(int orgId) {
		Query q = entityManager.createQuery("select u from User u JOIN u.orgList orgmlist WHERE orgmlist.id=?");

		q.setParameter(1, orgId);
		List<User> userNameList = q.getResultList();
		return userNameList;
	}

}
