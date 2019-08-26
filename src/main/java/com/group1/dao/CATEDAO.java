package com.group1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.group1.entity.CategoriesOfFilm;
import com.group1.entity.Category;
import com.group1.entity.CountriesOfFilm;
import com.group1.entity.Country;
import com.group1.entity.FILM;
import com.group1.model.CategoryModel;
import com.group1.model.CountryModel;
import com.group1.model.FILMModel;

public class CATEDAO {
    @Autowired
    private EntityManager entityManager;
    
    public CATEDAO(){
        
    }
    public List<FILMModel> getFilmByCategory(String id){
        String sql = "select new " +FILMModel.class.getName()+"(" +FILMModel.getAllVar()+") from " + FILM.class.getName() + " t where where t.id in (select a from "+CategoriesOfFilm.class.getClass()+" a where a.id = t.id and a.id_film = '"+id+"')";
        TypedQuery<FILMModel> data = entityManager.createQuery(sql, FILMModel.class);
        return data.getResultList();
    }
    
}
