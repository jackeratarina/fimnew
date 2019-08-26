package com.group1.dao;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.entity.Category;
import com.group1.entity.CountriesOfFilm;
import com.group1.entity.Country;
import com.group1.entity.FILM;

import com.group1.model.CategoryModel;
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
//		String sql = "select new " +CountryModel.class.getName()+"(" +CountryModel.getAllVar()+") from " + Country.class.getName() + " t where where t.id in (select a from "+CountriesOfFilm.class.getClass()+" a where a.id = t.id and a.id_film = '"+id+"')";
		String sql = "select id, name from Country where id in (Select id_country from CountriesOfFilm where id_film = '"+id+"')";
		List<CountryModel> data = entityManager.createNativeQuery(sql, Country.class).getResultList();
		return data;
	}
	public List<FILMModel> listFILMInfo() {
		String sql = "select new "+FILMModel.class.getName()+"("+FILMModel.getAllVar()+") from "+FILM.class.getName()+"";
		TypedQuery<FILMModel> q = entityManager.createQuery(sql, FILMModel.class);
		List<FILMModel> film = q.getResultList();
		return film;
	}
    public List<CategoryModel> listCategory() {
        String sql = "select new "+CategoryModel.class.getName()+"("+CategoryModel.getAllVar()+") from "+Category.class.getName()+"";
        TypedQuery<CategoryModel> q = entityManager.createQuery(sql, CategoryModel.class);
        List<CategoryModel> cate = q.getResultList();
        return cate;
    }

}
