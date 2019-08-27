package com.group1.dao;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.entity.Actor;
import com.group1.entity.Category;
import com.group1.entity.CountriesOfFilm;
import com.group1.entity.Country;
import com.group1.entity.FILM;
import com.group1.entity.Link;
import com.group1.model.CategoryModel;
import com.group1.model.CountryModel;
import com.group1.model.FILMModel;

@Repository
public class FILMDAO {
	@Autowired
	private EntityManager entityManager;
	
	public FILMDAO() {

	}
	
	public FILM findById(String id) {
		String sql = "select * from FILM  where id = ?1";
		FILM q = (FILM) entityManager.createNativeQuery(sql, FILM.class).setParameter(1, id).getSingleResult();
		return q;
	}
	public List<Country> getFilmCountry(String id){
//		String sql = "select new " +CountryModel.class.getName()+"(" +CountryModel.getAllVar()+") from " + Country.class.getName() + " t where where t.id in (select a from "+CountriesOfFilm.class.getClass()+" a where a.id = t.id and a.id_film = '"+id+"')";
		String sql = "select id, name from Country where id in (Select id_country from CountriesOfFilm where id_film = ?1)";
		List<Country> data = entityManager.createNativeQuery(sql, Country.class).setParameter(1, id).getResultList();
		return data;
	}
	public List<Category> getFilmCategories(String id){
		String sql = "select * from Category where id in (select id_category from CategoriesOfFilm where id_film = ?1)";
		List<Category> data = entityManager.createNativeQuery(sql, Category.class).setParameter(1, id).getResultList();
		return data;
	}
	public List<Actor> getFilmActors(String id){
		String sql = "select * from Actor where id in (select id_actor from ActorInFilm where id_film = ?1)";
		List<Actor> data = entityManager.createNativeQuery(sql, Actor.class).setParameter(1, id).getResultList();
		return data;
	}
	public List<Link> getLinkOfFilm(String id){
		String sql="select * from Link where id_film = ?1";
		Query q = entityManager.createNativeQuery(sql, Link.class);
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public List<FILM> listFILMInfo(int mount, int from) {
		String sql = "select top (?) * from FILM where id not in (select top (?) from FILM)";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, mount);
		q.setParameter(2, from+from);
		List<FILM> film = q.getResultList();
		return film;
	}
	public List<FILM> listFILMInfoPageWithInfo(int mount, int page, String country, String cate, String year, String actor, String search) {
		String sql = "select * from FILM where id in (select id_film from CategoriesOfFilm where id_category like :cate) and id in (select id_film from CountriesOfFilm where id_country like :country) and id in (select id_film from ActorInFilm where id_actor like :actor) and [date] like :year and (name like :search or name2 like :search) order by created_date OFFSET (:top) ROWS FETCH NEXT (:mount) ROWS ONLY";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter("mount", mount);
		q.setParameter("top", mount*page);
		q.setParameter("cate", cate);
		q.setParameter("country", country);
		q.setParameter("year", "%"+year);
		q.setParameter("actor", actor);
		q.setParameter("search", "%"+search.replace(" ", "%")+"%");
		
		List<FILM> film = q.getResultList();
		return film;
	}
	public List<FILM> listFILMInfoPage(int mount, int page) {
		String sql = "select * from FILM  order by created_date OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, mount*page);
		q.setParameter(2, mount);
		List<FILM> film = q.getResultList();
		return film;
	}
    public List<CategoryModel> listCategory() {
        String sql = "select new "+CategoryModel.class.getName()+"("+CategoryModel.getAllVar()+") from "+Category.class.getName()+"";
        TypedQuery<CategoryModel> q = entityManager.createQuery(sql, CategoryModel.class);
        List<CategoryModel> cate = q.getResultList();
        return cate;
    }

}
