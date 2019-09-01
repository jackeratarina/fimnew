import {advance_node, advance_set, data_mapping, film_form_var,modal_var,my_modal,loading_icon, edit_film,delete_film,class_pics,set_active,show_info, table_body, page_value,mount_data,adding_film, film_url,forward_page, previous_page} from './constant.js';
import $ from "jquery";
// ES6 Modules or TypeScript
import {importData} from './table';
import Swal from 'sweetalert2';
import {Film, film_node, film_form,advance_film} from './temp.js';
import datepicker from 'js-datepicker';
const uuidv4 = require('uuid/v4');
var base64 = require('base-64');
var moment = require('moment');

var filmHandler = (filmArr)=>{
    $(table_body).html('');
    filmArr.forEach((e, i)=>{
        let film = new Film(e);
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
        $(adding_film).on('click', (e)=>{
            tableFormInit();
            $(my_modal).slideDown();
        });
        $(modal_var.modal_close).on('click', (e)=>{
            $(my_modal).slideUp();
        });
        $(modal_var.modal_submit).on('click', (e)=>{
            var data_nodes = $(data_mapping);
            var film_node = {};
            for(var i = 0; i< data_nodes.length; i++){
                var dom_ = data_nodes[i];
                film_node[dom_.getAttribute('data-mapping')] = $(dom_).val();
            }
            var film_new = new Film(film_node);
            if(film_node.update == 1){
                film_new.created_date = moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
                $.ajax({
                    url : 'http://localhost:8080/admin/film/update',
                    method: 'POST',
                    data : film_new,
                    success:  (e)=>{
                        Swal.fire(
                            'Thành công',
                            'Bạn đã cập nhật FILM thành công!',
                            'success'
                          );
                        updateTablePageAndMount();
                        $(my_modal).slideUp();
                    },
                    error : (e)=>{
                        Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                    }
                });

            }else{
                film_new.created_date = moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
                $.ajax({
                    url : 'http://localhost:8080/admin/film/add',
                    method: 'POST',
                    data : film_new,
                    success:  (e)=>{
                        Swal.fire(
                            'Thành công',
                            'Bạn đã tạo mới FILM thành công!',
                            'success'
                          );
                        updateTablePageAndMount();
                        $(my_modal).slideUp();
                    },
                    error : (e)=>{
                        Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                    }
                });
            }
        })
    });
}
var tableFormInit = (data={})=>{
    $(modal_var.modal_submit).css('display','block');
    if(!data.id){
        data.id = uuidv4();
    }
    console.log(data);
    $(modal_var.modal_title).text('FILM');
    $(modal_var.modal_body).html(film_form(data));
    const picker = datepicker(film_form_var.film_date, {
        formatter: (input, date, instance) => {
          const value = date.toLocaleDateString('vi-VN');
          input.value = value // => '1/1/2099'
        },
        customDays: ['CH', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7']
      })
}
var initEventAdvanceSettings = ()=>{
    $(advance_node.actor_input).on('change', (e)=>{
        console.log($(e.target).val());
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
            try{
                var film = JSON.parse(unescape(base64.decode(this.parentNode.getAttribute("data-info-film"))));
                film.update = 1;
                tableFormInit(film);
                $(my_modal).slideDown();
            }catch(e){
                Swal.fire({title:'Error',text:'Không thể convert dử liệu',type: 'error'});
            }
        });
    })
    $(advance_set).each(function () {
        var node = this;
        $(node).on('click',function(e){
            var film = JSON.parse(unescape(base64.decode(this.parentNode.getAttribute("data-info-film"))));
            $.ajax({
                url : 'http://localhost:8080/admin/film/getinfo',
                method: 'GET',
                data : {id :film.id},
                success:  (e)=>{
                    var advance_f = advance_film(e, film.id);
                    $(modal_var.modal_title).text(film.name + " - " + film.name2);
                    $(modal_var.modal_body).html(advance_f);
                    initEventAdvanceSettings();
                    $(modal_var.modal_submit).css('display','none');
                    $(my_modal).slideDown();
                },
                error : (e)=>{
                    Swal.fire({title:'Error',text:'Có lỗi xảy ra khi load dữ liệu- server-error',type: 'error'});
                }
            });
        });
    })
    $(delete_film).each(function () {
        var node = this;
        $(node).on('click',function(e){
            try{
                var film = JSON.parse(unescape(base64.decode(this.parentNode.getAttribute("data-info-film"))));
                var film_node = new Film(film);
                Swal.fire({
                    title: 'Bạn có chắc không?',
                    text: "Bạn sẽ không thể hoàn tác khi xóa!",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Vâng, xóa đi!'
                  }).then((result) => {
                    if (result.value) {
                        delete film_node.created_date;
                        $.ajax({
                            url : 'http://localhost:8080/admin/film/delete',
                            method: 'POST',
                            data : film_node,
                            success:  (e)=>{
                                Swal.fire(
                                    'Deleted!',
                                    'Film đã được xóa thành công.',
                                    'success'
                                  )
                                updateTablePageAndMount();
                                $(my_modal).slideUp();
                            },
                            error : (e)=>{
                                Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                            }
                        });
                      
                    }
                  })
                
            }catch(e){
                Swal.fire({title:'Error',text:'Không thể convert dử liệu',type: 'error'});
            }
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