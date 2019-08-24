package com.group1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.entity.FILM;
import com.group1.model.FILMModel;

@Repository
public class FILMDAO {
	@Autowired
	private EntityManager entityManager;
	
	public FILMDAO() {

	}
	
	public FILM findById(Long id) {
		return this.entityManager.find(FILM.class, id);
	}
	
	public List<FILMModel> listFILMInfo() {
		String sql = "select top 100 * from FILM";
		Query q = entityManager.createNativeQuery(sql);
		List<FILMModel> film = q.getResultList();
		return film;
	}
}
