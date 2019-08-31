export var film_node = function(film, stt){return `<tr>
<td>${stt}</td>
<td>${film.name}</td>
<td>${film.name2}</td>
<td><div class="btn btn-primary btn-xs fa fa-eye show-info" data-info="${film.describe}"></div></td>
<td>${film.duration} phút</td>
<td><div class="btn btn-primary btn-xs fa fa-eye show_pics" data-href="${film.image}"></div><div class="btn btn-danger btn-xs fa fa-eye show_pics" data-href="${film.image_poster}"></div></td>
<td>${film.date}</td>
<td><div class="btn btn-default btn-xs fa fa-${film.is_active=='1'?'unlock':'lock'}"></td>
<td>
    <div class="btn btn-primary btn-xs fa fa-edit edit-film" data-id="${film.id}" data-toggle="tooltip" title="Chỉnh sửa"></div>
    <div class="btn btn-${film.is_active=='1'?'danger':'success'} btn-xs fa fa-toggle-${film.is_active=='1'?'off':'on'}" data-state="${film.is_active}" data-id="${film.id}"></div></div>
    <div class="btn btn-danger btn-xs fa fa-remove delete-film" data-id="${film.id}" data-toggle="tooltip" title="Xóa FILM"></div></div>
</td>
</tr>`;};
export class Film {
    constructor(id,date,describe,duration,image,IMDB,info,name,name2,resolution,status,is_active,created_date, image_poster){
        this.id = id;
		this.date = date;
		this.describe = describe;
		this.duration = duration;
		this.image = image;
		this.IMDB = IMDB;
		this.info = info;
		this.name = name;
		this.name2 = name2;
		this.resolution = resolution;
		this.status = status;
		this.is_active = is_active;
		this.created_date = created_date;
		this.image_poster = image_poster;
    }
}