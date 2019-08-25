package com.group1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.entity.CountriesOfFilm;
import com.group1.entity.Country;
import com.group1.entity.FILM;
import com.group1.model.CountryModel;
import com.group1.model.FILMModel;

@Repository
public class FILMDAO {
	@Autowired
	private EntityManager entityManager;
	
	public FILMDAO() {

	}
	
	public FILMModel findById(String id) {
		String sql = "select new "+FILMModel.class.getName()+"("+FILMModel.getAllVar()+") from "+FILM.class.getName()+" t where t.id='"+id+"'";
		TypedQuery<FILMModel> q = entityManager.createQuery(sql, FILMModel.class);
		return q.getSingleResult();
	}
	public List<CountryModel> getFilmCountry(String id){
		String sql = "select new " +CountryModel.class.getName()+"(" +CountryModel.getAllVar()+") from " + Country.class.getName() + " t where where t.id in (select a from "+CountriesOfFilm.class.getClass()+" a where a.id = t.id and a.id_film = '"+id+"')";
		TypedQuery<CountryModel> data = entityManager.createQuery(sql, CountryModel.class);
		return data.getResultList();
	}
	public List<FILMModel> listFILMInfo() {
		String sql = "select new "+FILMModel.class.getName()+"("+FILMModel.getAllVar()+") from "+FILM.class.getName()+"";
		TypedQuery<FILMModel> q = entityManager.createQuery(sql, FILMModel.class);
		List<FILMModel> film = q.getResultList();
		return film;
	}
}