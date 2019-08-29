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
public class CATEDAO {
    @Autowired
    private EntityManager entityManager;
    
    public CATEDAO(){
        
    }
    
    public List<Category> getlistCategory() {
        String sql = "select * from Category";
		List<Category> q = entityManager.createNativeQuery(sql, Category.class).getResultList();
		return q;
    }
}
