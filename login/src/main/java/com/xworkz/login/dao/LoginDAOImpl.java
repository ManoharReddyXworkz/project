package com.xworkz.login.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xworkz.login.dto.LoginDTO;

import io.quarkus.arc.runtime.BeanContainer.Factory;

@Repository
public class LoginDAOImpl implements LoginDAO{
	@Autowired
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Override
	public Boolean save(LoginDTO loginDTO) {
		manager = factory.createEntityManager();
		try {
			
			EntityTransaction transaction= manager.getTransaction();
			transaction.begin();
			manager.persist(loginDTO);
			transaction.commit();
		}
		catch (PersistenceException p) {
			p.printStackTrace();
		}
		finally {
			manager.close();
		}
		
		return true;
	}

	@Override
	public List<LoginDTO> FindByEmail(String newEmail) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery( "FindByEmail");
			query.setParameter("email", newEmail);
			List<LoginDTO> resultList = query.getResultList();
			System.out.println(resultList);

			if(resultList.isEmpty()) {
				return null;
			}
				else if(!resultList.isEmpty()) {
					return resultList;
				}
		}
		catch (PersistenceException e) {
			e.printStackTrace();
		}
		finally {
			manager.close();
		}
		return null;
	}

}
