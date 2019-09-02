package com.group1.dao;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.entity.Actor;
import com.group1.entity.ActorInFilm;
import com.group1.entity.CategoriesOfFilm;
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
		String sql = "select * from Country where id in (Select id_country from CountriesOfFilm where id_film = ?1)";
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
	
//	public List<FILM> listFILMInfo(int mount, int from) {
//		String sql = "select top (?) * from FILM where id not in (select top (?) from FILM)";
//		Query q = entityManager.createNativeQuery(sql, FILM.class);
//		q.setParameter(1, mount);
//		q.setParameter(2, from+from);
//		List<FILM> film = q.getResultList();
//		return film;
//	}
	public List<FILM> listFILMInfoPageWithInfo(int mount, int page, String country, String cate, String year, String actor, String search, boolean main) {
		String sql = "";
		if(main) {
			sql = "select * from FILM where is_active = 1 and id in (select id_film from CategoriesOfFilm where id_category like :cate) and id in (select id_film from CountriesOfFilm where id_country like :country) and id in (select id_film from ActorInFilm where id_actor like :actor) and [date] like :year and (LOWER(name) like :search or LOWER(name2) like :search) order by created_date DESC OFFSET (:top) ROWS FETCH NEXT (:mount) ROWS ONLY";
		}else {
			sql = "select * from FILM where id in (select id_film from CategoriesOfFilm where id_category like :cate) and id in (select id_film from CountriesOfFilm where id_country like :country) and id in (select id_film from ActorInFilm where id_actor like :actor) and [date] like :year and (LOWER(name) like :search or LOWER(name2) like :search) order by created_date DESC OFFSET (:top) ROWS FETCH NEXT (:mount) ROWS ONLY";
		}
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter("mount", mount);
		q.setParameter("top", mount*page);
		q.setParameter("cate", cate);
		q.setParameter("country", country);
		q.setParameter("year", "%"+year);
		q.setParameter("actor", actor);
		q.setParameter("search", "%"+search.toLowerCase().replace(" ", "%")+"%");
		
		List<FILM> film = q.getResultList();
		return film;
	}
	public List<FILM> listFILMWithSearch(int mount, int page, String search) {
		String sql = "select * from FILM where (LOWER(name) like :search or LOWER(name2) like :search) order by created_date DESC OFFSET :top ROWS FETCH NEXT :mount ROWS ONLY";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter("mount", mount);
		q.setParameter("top", mount*page);
		q.setParameter("search", "%"+search.toLowerCase().replace(" ", "%")+"%");
		
		List<FILM> film = q.getResultList();
		return film;
	}
	
	public FILM updateFilm(FILM film) {
		FILM status = entityManager.merge(film);
		return status;
	}
	public Link updateLink(Link link) {
		Link status = entityManager.merge(link);
		return status;
	}
	public void addLink(Link link) {
		entityManager.persist(link);
	}
	public int deleteLink(String id_link) {
		String sql = "delete from Link where id = ?1";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, id_link);
		int i = q.executeUpdate();
		return i;
	}
	
	public void saveFilm(FILM film) {
		entityManager.persist(film);
	}
	public int removeFilm(String id) {
		String sql = "delete from FILM where id = ?1";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, id);
		int i = q.executeUpdate();
		return i;
	}
	
	public List<FILM> listFILMInfoPage(int mount, int page, boolean main) {
		String sql = "";
		if(main == true) {
			sql = "select * from FILM where is_active = 1 order by created_date DESC OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY";
		}else {
			sql = "select * from FILM order by created_date DESC OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY";
		}
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, mount*page);
		q.setParameter(2, mount);
		List<FILM> film = q.getResultList();
		return film;
	}
    public List<Category> listCategory() {
        String sql = "select * from Category where is_active = '1'";
		List<Category> q = entityManager.createNativeQuery(sql, Category.class).getResultList();
		return q;
    }
    public List<Country> listCountry() {
        String sql = "select * from Country";
		List<Country> q = entityManager.createNativeQuery(sql, Country.class).getResultList();
		return q;
    }
    
    public List<Category> getlistCategory() {
        String sql = "select * from Category";
		List<Category> q = entityManager.createNativeQuery(sql, Category.class).getResultList();
		return q;
    }   
    
    public void activeCategory(String id) {
    	String sql = "update Category set is_active = 1 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    }
    
    public void disableCategory(String id) {
    	String sql = "update Category set is_active = 0 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    }
    
    public Category getCategory(String id) {
    	String sql = "select * from Category where id = ?1";
		Category q = (Category) entityManager.createNativeQuery(sql, Category.class).setParameter(1, id).getSingleResult();
		return q;
    }
    
    public void addCategory(String id, String name, String is_active) {
    	String sql = "insert into Category (id,name,is_active) values (?1,?2,?3)";
    	Query q = entityManager.createNativeQuery(sql);
    	q.setParameter(1, id);
    	q.setParameter(2, name);
    	q.setParameter(3, is_active);
    	q.executeUpdate();
    }
    
    public void updateCategory(String id,String name, String is_active) {
    	String sql = "update Category set name = ?1, is_active = ?2 where id = ?3";
    	Query q = entityManager.createNativeQuery(sql);
    	q.setParameter(1, name);
    	q.setParameter(2, is_active);
    	q.setParameter(3, id);
    	q.executeUpdate();
    }
    
    public List<Category> filterActiveCategory(String id) {
        String sql = "select * from Category where is_active = ?1";
		List<Category> q = entityManager.createNativeQuery(sql, Category.class).setParameter(1, id).getResultList();
		return q;
    }
    public List<Actor> getListActor() {
    	String sql = "select * from Actor";
    	List<Actor> q = entityManager.createNativeQuery(sql, Category.class).getResultList();
    	return q;
    }
    public void inactiveActor(String id) {
    	String sql = "update Actor set is_active = 0 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    	
    }
    public void activeActor(String id) {
    	String sql = "update Actor set is_active = 1 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    	
    }
    public void addActorForFilm(ActorInFilm aif) {
    	String sql = "insert into ActorInFilm (id, name_in, id_film, id_actor) values (?1, ?2, ?3, ?4)";
    	entityManager.createNativeQuery(sql).setParameter(1, aif.getId()).setParameter(2, aif.getName_in()).setParameter(3, aif.getId_film()).setParameter(4, aif.getId_actor()).executeUpdate();
    }
    public void addCategoryForFilm(CategoriesOfFilm cof) {
    	String sql = "insert into CategoriesOfFilm (id, id_film, id_category) values (?1, ?2, ?3)";
    	entityManager.createNativeQuery(sql).setParameter(1, cof.getId()).setParameter(2, cof.getId_film()).setParameter(3, cof.getId_category()).executeUpdate();
    }
    public void addCountryForFilm(CountriesOfFilm cof) {
    	String sql = "insert into CountriesOfFilm (id, id_film, id_country) values (?1, ?2, ?3)";
    	entityManager.createNativeQuery(sql).setParameter(1, cof.getId()).setParameter(2, cof.getId_film()).setParameter(3, cof.getId_country()).executeUpdate();
    }
    public void removeActorForFilm(ActorInFilm aif) {
    	String sql = "delete from ActorInFilm where id_film = ?1 and id_actor = ?2";
    	entityManager.createNativeQuery(sql).setParameter(1, aif.getId_film()).setParameter(2, aif.getId_actor()).executeUpdate();
    }
    public void removeCategoryForFilm(CategoriesOfFilm cof) {
    	String sql = "delete from CategoriesOfFilm where id_film = ?1 and id_category = ?2";
    	entityManager.createNativeQuery(sql).setParameter(1, cof.getId_film()).setParameter(2, cof.getId_category()).executeUpdate();
    }
    public void removeCountryForFilm(CountriesOfFilm cof) {
    	String sql = "delete from CountriesOfFilm where id_film = ?1 and id_country = ?2";
    	entityManager.createNativeQuery(sql).setParameter(1, cof.getId_film()).setParameter(2, cof.getId_country()).executeUpdate();
    }
    public int removeActorForFilm(String id) {
		String sql = "delete from FILM where id = ?1";
		Query q = entityManager.createNativeQuery(sql, FILM.class);
		q.setParameter(1, id);
		int i = q.executeUpdate();
		return i;
	}
    public Actor getActor(String id) {
    	String sql = "select * from Actor where id = ?1";
		Actor q = (Actor) entityManager.createNativeQuery(sql, Actor.class).setParameter(1, id).getSingleResult();
		return q;
    }
    public void updateActor(String id,String name) {
    	String sql = "update Actor set name = ?1 where id = ?2";
    	//entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    	Query q = entityManager.createNativeQuery(sql);
    	q.setParameter(1, name);
    	q.setParameter(2, id);
    	q.executeUpdate();
    }
    public List<Actor> searchActorByName(String name){
    	String sql = "select * from Actor where LOWER(name) like ?1";
    	Query q = entityManager.createNativeQuery(sql, Actor.class);
    	q.setParameter(1, "%" + name.toLowerCase() + "%");
    	return q.getResultList();
    }
    public List<Country> listAllCountry() {
		String sql = "select * from Country";
		Query c = entityManager.createNativeQuery(sql, Country.class);
		List<Country> list = c.getResultList();
		return list;
	}
    
    @Transactional
    public void disableCountry(String id) {
    	String sql = "update Country set is_active = 0 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    }
    
    @Transactional
    public void enableCountry(String id) {
    	String sql = "update Country set is_active = 1 where id = ?1";
    	entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    }
    
    @Transactional
    public void updateCountry(String id, String name) {
    	String sql = "update Country set name = ?2 where id = ?1";
    	Query q = entityManager.createNativeQuery(sql);
    	q.setParameter(1, id);
    	q.setParameter(2, name);
    	q.executeUpdate();
    }
    

    public void addCountry(String id, String name, String is_active) {
    	String sql = "insert into Country (id, name, is_active) values (?1, ?2, ?3)";
    	entityManager.createNativeQuery(sql).setParameter(1, id).setParameter(2, name).setParameter(3, is_active).executeUpdate();
    }
    
    public Country findCountry(String id) {
    	String sql = "select * from Country where id = ?1";
    	Country country = (Country) entityManager.createNativeQuery(sql, Country.class).setParameter(1, id).getSingleResult();
		return country;
	}
    
    public Country checkIdCountry(String id) {
    	Country country;
		
		try {
			country = (Country) entityManager.createQuery("from Country where id = ?1")
					.setParameter(1, id)
					.getSingleResult();
		} catch (NoResultException e) {
			country = null;
		}
		
		return country;
	}
}
