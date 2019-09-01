var base64 = require('base-64');
export var film_node = function(film, stt){return `<tr>
<td>${stt}</td>
<td>${film.name}</td>
<td>${film.name2}</td>
<td><div class="btn btn-primary btn-xs fa fa-eye show-info" data-info="${film.describe}"></div></td>
<td>${film.duration} phút</td>
<td><div class="btn btn-primary btn-xs fa fa-eye show_pics" data-href="${film.image}"></div><div class="btn btn-danger btn-xs fa fa-eye show_pics" data-href="${film.image_poster}"></div></td>
<td>${film.date}</td>
<td><div class="btn btn-default btn-xs fa fa-${film.is_active=='1'?'unlock':'lock'}"></td>
<td data-info-film="${base64.encode(escape(JSON.stringify(film)))}">
    <div class="btn btn-primary btn-xs fa fa-edit edit-film" data-id="${film.id}" data-toggle="tooltip" title="Chỉnh sửa"></div>
    <div class="btn btn-success btn-xs fa fa-gears advance-settings" data-state="${film.is_active}" data-id="${film.id}"></div></div>
    <div class="btn btn-danger btn-xs fa fa-remove delete-film" data-id="${film.id}" data-toggle="tooltip" title="Xóa FILM"></div></div>
</td>
</tr>`;};
export class Film {
    constructor(e){
        this.id = e.id;
		this.date = e.date;
		this.describe = e.describe;
		this.duration = e.duration;
		this.image = e.image;
		this.IMDB = e.IMDB;
		this.info = e.info;
		this.name = e.name;
		this.name2 = e.name2;
		this.resolution = e.resolution;
		this.status = e.status;
		this.is_active = e.is_active;
		this.created_date = e.created_date;
		this.image_poster = e.image_poster;
    }
}
export var film_form = (e)=> { return `<div class="form-group">
<label for="film_id">Film ID</label>
<input data-mapping="id" class="form-control" id="film_id" disabled value="${e.id}">
</div>
<input data-mapping="update" class="form-control" value="${e.update}" id="film_update" type="hidden"/>
<div class="form-group">
<label for="film_name">Tên Tiếng Việt</label>
<input data-mapping="name" class="form-control" id="film_name" value="${e.name||''}">
</div>
<div class="form-group">
<label for="film_name2">Tên Tiếng Anh</label>
<input data-mapping="name2" class="form-control" id="film_name2" value="${e.name2||''}">
</div>
<div class="form-group">
<label for="film_describe">Miêu tả</label>
<textarea data-mapping="describe" class="form-control" id="film_describe" >${e.describe||''}</textarea>
</div>
<div class="form-group">
<label for="film_duration">Thời lượng</label>
<input data-mapping="duration" class="form-control" id="film_duration" value="${e.duration==0?'0':e.duration}" type="number" min="0" step="1">
</div>
<div class="form-group">
<label for="film_image">Hình ảnh</label>
<input data-mapping="image" placeholder="Đường dẫn ... ví dụ : https://xxx.com/abc.jpg" value="${e.image||''}" class="form-control" id="film_image">
</div>
<div class="form-group">
<label for="film_image_poster">Hình ảnh POSTER</label>
<input data-mapping="image_poster" placeholder="Đường dẫn ... ví dụ : https://xxx.com/abc.jpg"  value="${e.image_poster||''}" class="form-control" id="film_image_poster">
</div>
<div class="form-group">
<label for="film_active">Trạng thái</label>
<select data-mapping="is_active" class="form-control" id="film_active">
	<option value="0" ${e.is_active == 0?'selected':''}">Tắt</option>
	<option value="1" ${(e.is_active == 1 || e.is_active == undefined)?'selected':''}>Bật</option>
</select>
</div>
<div class="form-group">
<label for="film_date">Ngày công chiếu</label>
<input data-mapping="date" value="${e.date||''}" placeholder="Chọn ngày" type="text" id="film_date" class="form-control"/>
</div>`;}

export var advance_film = (e, id)=>{
	var select_cate = "";
	e.listCate.forEach((e)=>{
		select_cate += `<option value="${e.id}">${e.name}</option>`;
	});
	var select_country = "";
	e.countries.forEach((e)=>{
		select_country += `<option value="${e.id}">${e.name}</option>`;
	});
	var actor_list = "";
	e.filmActors.forEach((e)=>{
		actor_list += `<div class="btn btn-success btn-sm margin5 actor_remove" data-id="${e.id}">${e.name}</div>`;
	});
	var cate_list = "";
	e.filmCateroies.forEach((e)=>{
		cate_list += `<div class="btn btn-primary btn-sm margin5 cate_remove" data-id="${e.id}">${e.name}</div>`;
	});
	var country_list = "";
	e.filmCounties.forEach((e)=>{
		country_list += `<div class="btn btn-danger btn-sm margin5 country_remove" data-id="${e.id}">${e.name}</div>`;
	});
	
	return `<div class="form-group">
	<input type="hidden" id="film_info_modal" value="${id}"/>
	<div class="col-md-12">
		<h4 style="font-weight: bold">Thể loại</h4>
		<div class="panel panel-info">
			<div class="panel-heading search_panel">
				<div class="col-md-8">
					<input placeholder="Tìm Thể loại ở đây" class="form-control" id="category_input" list="category_list">
					<datalist id="category_list">
						${select_cate}
					</datalist>
				</div>
				<div class="col-md-4">
					<button class="btn btn-primary" id="add_cate">Thêm</button>
				</div>
			</div>
			<div class="panel-body">
				${cate_list}
			</div>
		</div>
	</div>
</div>
<div class="form-group">
		<div class="col-md-12">
			<h4 style="font-weight: bold">Diển viên</h4>
			<div class="panel panel-success">
				<div class="panel-heading search_panel">
					<div class="col-md-8">
						<input placeholder="Tìm Diển viên ở đây" class="form-control" id="actor_input" list="actor_list">
						<datalist id="actor_list">
						</datalist>
					</div>
					<div class="col-md-4">
						<button class="btn btn-success" id="add_actor">Thêm</button>
					</div>
				</div>
				<div class="panel-body">
					${actor_list}
				</div>
			</div>
		</div>
	</div>
	<div class="form-group">
			<div class="col-md-12">
				<h4 style="font-weight: bold">Quốc gia</h4>
				<div class="panel panel-danger">
					<div class="panel-heading search_panel">
						<div class="col-md-8">
							<input placeholder="Tìm Quốc gia ở đây" class="form-control" list="country_list" id="country_input">
							<datalist id="country_list">
								${select_country}
							</datalist>
						</div>
						<div class="col-md-4">
							<button class="btn btn-danger" id="add_country">Thêm</button>
						</div>
					</div>
					<div class="panel-body">
						${country_list}
					</div>
				</div>
			</div>
		</div>`;
}