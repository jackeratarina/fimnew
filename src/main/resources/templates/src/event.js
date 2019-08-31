import {loading_icon, edit_film,delete_film,class_pics,set_active,show_info, table_body, page_value,mount_data,adding_film, film_url,forward_page, previous_page} from './constant.js';
import $ from "jquery";
// ES6 Modules or TypeScript
import {importData} from './table';
import Swal from 'sweetalert2';
import {Film, film_node} from './temp.js';

var filmHandler = (filmArr)=>{
    $(table_body).html('');
    filmArr.forEach((e, i)=>{
        let film = new Film(e.id, e.date, e.describe, e.duration, e.image, e.IMDB, e.info, e.name, e.name2, e.resolution, e.status, e.is_active, e.created_date, e.image_poster)
        importData(table_body, null, film_node(film,(parseInt($(page_value).val())-1)*parseInt($(mount_data).val())+i+1));
    })
    initEventNode();
}
var loadFilmTable = (page, mount)=>{
    if(page === NaN){
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Số trang của bạn không phải là số!',
          });
    }else if(mount === NaN){
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'Số lượng của bạn không phải là số!',
          });
    }
    importData(table_body,`<img style="width: 40px;height: 40px;margin-left: 400%;" src="${loading_icon}"/>`);
    $.ajax({
        url : film_url,
        method: 'GET',
        data : {page : page, mount : mount},
        success:  filmHandler
    });
}
var updateTablePageAndMount = (e)=>{
    let mount = parseInt($(mount_data).val());
    let page = parseInt($(page_value).val());
    loadFilmTable(page-1, mount);
}
export var initEventTable = () =>{
    $(document).ready(()=>{
        loadFilmTable(0, 10);
        $(mount_data).on('change', updateTablePageAndMount);
        $(page_value).on('change', updateTablePageAndMount);
        $(forward_page).on('click', (e)=>{
            let page = parseInt($(page_value).val());
            let mount = parseInt($(mount_data).val());
            if(page === NaN){
                $(page_value).val(1);
                loadFilmTable(0, 10);
            }else{
                page += 1;
                if(mount === NaN){
                    location.reload();
                }
                $(page_value).val(page);
                loadFilmTable(page, mount);
            }
        });
        $(previous_page).on('click', (e)=>{
            let page = parseInt($(page_value).val());
            let mount = parseInt($(mount_data).val());
            if(page === NaN){
                $(page_value).val(1);
                loadFilmTable(0, 10);
            }else{
                page -= 1;
                if(page <= 0){
                    return;
                }
                if(mount === NaN){
                    location.reload();  
                }
                $(page_value).val(page);
                loadFilmTable(page, mount);
            }
        });
        
    })
}
export var initEventNode = ()=>{
    $(class_pics).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let img = this.getAttribute("data-href");
            Swal.fire({
                imageUrl: img
            });
        });
    })
    $(edit_film).each(function () {
        var node = this;
        $(node).on('click',function(e){
            
        });
    })
    $(set_active).each(function () {
        var node = this;
        $(node).on('click',function(e){

        });
    })
    $(delete_film).each(function () {
        var node = this;
        $(node).on('click',function(e){

        });
    })
    $(show_info).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let data = this.getAttribute("data-info");
            Swal.fire({
                html:'<p>'+data+'</p>'
            });
        });
    })
}