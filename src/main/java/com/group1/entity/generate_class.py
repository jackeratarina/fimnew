tables = {}
tables['Actor'] = []
tables['ActorInFilm'] = []
tables['CategoriesOfFilm'] = []
tables['Category'] = []
tables['CountriesOfFilm'] = []
tables['Country'] = []
tables['FILM'] = []
tables['Link'] = []
tables['ListFilm'] = []
tables['Playlist'] = []
tables['User'] = []
tables['Link'].append('describe')
tables['Link'].append('id_film')
tables['Link'].append('num')
tables['Link'].append('url')
tables['Link'].append('is_active')
tables['User'].append('email')
tables['User'].append('password')
tables['User'].append('role')
tables['User'].append('username')
tables['User'].append('is_active')
tables['FILM'].append('date')
tables['FILM'].append('describe')
tables['FILM'].append('duration')
tables['FILM'].append('image')
tables['FILM'].append('IMDB')
tables['FILM'].append('info')
tables['FILM'].append('name')
tables['FILM'].append('name2')
tables['FILM'].append('resolution')
tables['FILM'].append('status')
tables['FILM'].append('is_active')
tables['FILM'].append('created_date')
tables['FILM'].append('image_poster')
tables['CountriesOfFilm'].append('id_country')
tables['CountriesOfFilm'].append('id_film')
tables['ActorInFilm'].append('id_actor')
tables['ActorInFilm'].append('id_film')
tables['ActorInFilm'].append('name_in')
tables['CategoriesOfFilm'].append('id_category')
tables['CategoriesOfFilm'].append('id_film')
tables['Actor'].append('image')
tables['Actor'].append('is_active')
tables['Actor'].append('name')
tables['Country'].append('name')
tables['Country'].append('is_active')
tables['Category'].append('name')
tables['Category'].append('is_active')
tables['Playlist'].append('created_date')
tables['Playlist'].append('id_user')
tables['Playlist'].append('is_public')
tables['Playlist'].append('name')
tables['Playlist'].append('is_active')
tables['ListFilm'].append('created_date')
tables['ListFilm'].append('id_film')
tables['ListFilm'].append('id_playlist')

column = """
	{is_id}
	@Column(name="{column_name}", nullable=true)
	private {type} {column_name};
"""
getter = """
	public {type} get{column_name_}() {
		return {column_name};
	}
"""
setter = """
	public void set{column_name_}({type} {column_name}) {
		this.{column_name} = {column_name};
	}
"""
temp = """package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="{table_name}")
public class {table_name} implements Serializable{
	@Id
	@Column(name="id", nullable=false)
	private String id;
	
	{column_generator}

	
	{get_generator}

	{set_generator}

}"""
temp_declare = """
		private {type} {column_name};"""
temp_contr = """
		this.{column_name} = {column_name};"""
temp_model = """package com.group1.model;

public class {table_name}Model {
	{list_declare}
	
	public {table_name}Model() {

	}

	public {table_name}Model({list_param}) {
		super();
		{list_construct}
	}
	{get_generator}
	{set_generator}
	public static String getAllVar() {
		return "{all_var}";
	}
}
"""
for table in tables:
	col = tables[table]
	mytemp = temp.replace('{table_name}',table)
	mytemp_model = temp_model.replace('{table_name}',table)
	tmp_column = ""
	getter_column = ""
	setter_column = ""
	tmp_declare = ""
	tmp_listparam = ""
	tmp_constrct= ""
	for i in col:
		type="String"
		if(i=='duration'):
			type = 'float'
		if(i == 'is_public'):
			type = 'int'
		if(i=='created_date'):
			type = 'java.sql.Timestamp'
		if(i.find('id_')!=-1):
			mytemp = mytemp.replace('@Id','')
		tmp_listparam += type + ' ' + i + ','
		tmp_constrct += temp_contr.replace('{column_name}',i)
		tmp_declare += temp_declare.replace('{column_name}',i).replace('{type}',type)
		tmp_column += column.replace('{column_name}',i).replace('{type}',type).replace('{is_id}',['','@Id'][i.find('id_')!=-1]).replace('nullable=true',['nullable=true','nullable=false'][i.find('id_')!=-1])
		getter_column += getter.replace('{column_name}',i).replace('{type}',type).replace('{column_name_}',i[0].upper()+i[1:])
		setter_column += setter.replace('{column_name}',i).replace('{type}',type).replace('{column_name_}',i[0].upper()+i[1:])
	mytemp_model = mytemp_model.replace('{list_declare}', temp_declare.replace('{column_name}',"id").replace('{type}',"String")+tmp_declare)
	mytemp_model = mytemp_model.replace('{get_generator}',getter.replace('{column_name_}','Id').replace('{column_name}',"id").replace('{type}',"String")+getter_column)
	mytemp_model = mytemp_model.replace('{set_generator}',setter.replace('{column_name_}','Id').replace('{column_name}',"id").replace('{type}',"String")+setter_column)
	mytemp_model = mytemp_model.replace('{list_construct}',temp_contr.replace('{column_name}',"id").replace('{type}',"String")+tmp_constrct)
	mytemp_model = mytemp_model.replace('{list_param}','String id,'+tmp_listparam[:-1])
	mytemp_model = mytemp_model.replace('{all_var}', 'id,'+','.join(col))
	
	mytemp = mytemp.replace('{column_generator}',tmp_column)
	mytemp = mytemp.replace('{get_generator}',getter.replace('{column_name_}','Id').replace('{column_name}',"id").replace('{type}',"String")+getter_column)
	mytemp = mytemp.replace('{set_generator}',setter.replace('{column_name_}','Id').replace('{column_name}',"id").replace('{type}',"String")+setter_column)
	with open(table+'.java','w+') as f:
		f.write(mytemp)
	with open('../model/'+table+'Model.java','w+') as f1:
		f1.write(mytemp_model)