import {link_var, search_table ,advance_node, advance_set, data_mapping, film_form_var,modal_var,my_modal,loading_icon, edit_film,delete_film,class_pics,set_active,show_info, table_body, page_value,mount_data,adding_film, film_url,forward_page, previous_page} from './constant.js';
import $ from "jquery";
// ES6 Modules or TypeScript
import {importData} from './table';
import Swal from 'sweetalert2';
import {Film, film_node, film_form,advance_film,film_eps} from './temp.js';
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
var loadFilmTable = (page, mount, search="")=>{
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
        data : {page : page, mount : mount, search : search},
        success:  filmHandler
    });
}
var updateTablePageAndMount = (e)=>{
    let mount = parseInt($(mount_data).val());
    let page = parseInt($(page_value).val());
    let search = $(search_table).val();
    loadFilmTable(page-1, mount,search);
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
                loadFilmTable(page-1, mount);
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
    $(search_table).on('change', ()=>{
        updateTablePageAndMount();
    })
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
var initEventAdvanceSettings = (film_id, my_node)=>{
    $(advance_node.actor_input).on('change', (e)=>{
        $.ajax({
            url : 'http://localhost:8080/admin/actor/search',
            method: 'GET',
            data : {name :$(e.target).val()},
            success:  (e)=>{
                var select_actor = "";
                e.forEach((e)=>{
                    select_actor += `<option value="${e.id}">${e.name}</option>`;
                });
                $(advance_node.actor_list).html(`<select class="form-control">${select_actor}</select>`);
            }
        });
    });
    $(advance_node.add_actor).on('click', (e)=>{
        var actor_id = $(advance_node.actor_input).val();
        Swal.fire({
            title: 'Nhập tên trong phim của diển viên - ' + actor_id,
            input: 'text',
            inputAttributes: {
              autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Thêm diển viên',
            showLoaderOnConfirm: true
          }).then((result) => {
            if (result.value) {
                $.ajax({
                    url : 'http://localhost:8080/admin/actor_film/create',
                    method: 'POST',
                    data : {
                        id : uuidv4(),
                        id_film : film_id,
                        id_actor : actor_id,
                        name_in : result.value
                    },
                    success:  (e)=>{
                        Swal.fire(
                            'Thêm thành công!',
                            'Diên viên đã thêm cho film thành công!.',
                            'success'
                        );
                        $(my_node).trigger('click');
                    },
                    error: (e)=>{
                        Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                        $(my_node).trigger('click');
                    }
                });
            }
          })
    });
    $(advance_node.add_cate).on('click', (e)=>{
        var id_category = $(advance_node.category_input).val();
        $.ajax({
            url : 'http://localhost:8080/admin/category_film/create',
            method: 'POST',
            data : {
                id : uuidv4(),
                id_film : film_id,
                id_category : id_category
            },
            success:  (e)=>{
                Swal.fire(
                    'Thêm thành công!',
                    'Thể loại đã thêm cho film thành công!.',
                    'success'
                );
                $(my_node).trigger('click');
            },
            error: (e)=>{
                Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                $(my_node).trigger('click');
            }
        });
    });
    $(advance_node.add_country).on('click', (e)=>{
        var id_country = $(advance_node.country_input).val();
        $.ajax({
            url : 'http://localhost:8080/admin/country_film/create',
            method: 'POST',
            data : {
                id : uuidv4(),
                id_film : film_id,
                id_country : id_country
            },
            success:  (e)=>{
                Swal.fire(
                    'Thêm thành công!',
                    'Quốc gia đã thêm cho film thành công!.',
                    'success'
                );
                $(my_node).trigger('click');
            },
            error: (e)=>{
                Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                $(my_node).trigger('click');
            }
        });
    });
    $(advance_node.country_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_country = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/country_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_country : id_country
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
    $(advance_node.cate_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_category = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/category_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_category : id_category
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
    $(advance_node.actor_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_actor = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/actor_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_actor : id_actor
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
}
var initEventLink = (e, id_film,node_)=>{
    $(link_var.add_link_click).on('click',()=>{
        $(link_var.id_eps).find('*').removeClass('btn-danger').addClass('btn-primary');
        $(link_var.id_eps).prepend($(`<div class="btn btn-danger margin5 eps_click" data-new="1">[Empty]</div>`));
        $(link_var.id_name_film).val('');
        $(link_var.id_cur_link).val('');
        
    })
    $(link_var.id_name_film).on('keydown click change',()=>{
        $(link_var.id_eps).find('.btn-danger').text($(link_var.id_name_film).val());
    })
    $(link_var.id_cur_link).on('keydown click change',()=>{
        
    });
    $(link_var.id_update_film).on('click',()=>{
        console.log('here');
        var selected_ep = $(link_var.id_eps).find('.btn-danger');
        var name_film = $(link_var.id_name_film).val();
        var film_url = $(link_var.id_cur_link).val();
        if(selected_ep.attr('data-info')){
            // update
            console.log('update');
            var link_info = JSON.parse(unescape(base64.decode(selected_ep.attr('data-info'))));
            link_info.num = name_film;
            link_info.url = film_url;
            $.ajax({
                url : 'http://localhost:8080/admin/link/update',
                method: 'POST',
                data : link_info,
                success:  (e)=>{
                    Swal.fire(
                        'Đã hoàn tất!',
                        'Link của Film đã được cập nhật thành công.',
                        'success'
                      )
                    $(node_).trigger('click');
                },
                error : (e)=>{
                    Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                }
            });
        }else{
            var link_id = selected_ep.attr('data-id');
            var link_info = {
                id: uuidv4(),
                id_film: id_film,
                num: name_film,
                url: film_url,
                is_active: "1"
            }
            $.ajax({
                url : 'http://localhost:8080/admin/link/add',
                method: 'POST',
                data : link_info,
                success:  (e)=>{
                    Swal.fire(
                        'Đã hoàn tất!',
                        'Link của Film đã được thêm thành công.',
                        'success'
                      )
                    $(node_).trigger('click');
                },
                error : (e)=>{
                    Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                }
            });
        }
    });
    $(link_var.eps_click).each(function(){
        var node = this;
        $(node).on('click',function(e){
            $(link_var.id_eps).find('*').removeClass('btn-danger').addClass('btn-primary');
            if($(link_var.id_eps).find(link_var.eps_click+ '[data-new]')!=0){
                $('#id_eps').find('.eps_click[data-new]').remove();
            }
            $(this).addClass('btn-danger');
            var selected_ep = $(link_var.id_eps).find('.btn-danger');
            if(selected_ep.attr('data-info')){
                var link_info = JSON.parse(unescape(base64.decode(selected_ep.attr('data-info'))));
                $(link_var.id_name_film).val(link_info.num);
                $(link_var.id_cur_link).val(link_info.url);
                $(link_var.id_review_iframe).attr('src',link_info.url)
            }else{
                $(link_var.id_name_film).val('');
                $(link_var.id_cur_link).val('');
                return;
            }
            
        });
    });
    $(link_var.id_delete_film).on('click',function(e){
        var selected_ep = $(link_var.id_eps).find('.btn-danger');
        if(!selected_ep.attr('data-new')){
            var link_info = JSON.parse(unescape(base64.decode(selected_ep.attr('data-info'))));
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/link/delete',
                        method: 'POST',
                        data : {id_link:link_info.id},
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Link đã được xóa thành công.',
                                'success'
                              )
                            $(node_).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        }else{
            $(link_var.id_eps).find('[data-info]')[0].click();
        }
        
        
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
var initEventAdvanceSettings = (film_id, my_node)=>{
    $(advance_node.actor_input).on('change', (e)=>{
        $.ajax({
            url : 'http://localhost:8080/admin/actor/search',
            method: 'GET',
            data : {name :$(e.target).val()},
            success:  (e)=>{
                var select_actor = "";
                e.forEach((e)=>{
                    select_actor += `<option value="${e.id}">${e.name}</option>`;
                });
                $(advance_node.actor_list).html(`<select class="form-control">${select_actor}</select>`);
            }
        });
    });
    $(advance_node.add_actor).on('click', (e)=>{
        var actor_id = $(advance_node.actor_input).val();
        Swal.fire({
            title: 'Nhập tên trong phim của diển viên - ' + actor_id,
            input: 'text',
            inputAttributes: {
              autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Thêm diển viên',
            showLoaderOnConfirm: true
          }).then((result) => {
            if (result.value) {
                $.ajax({
                    url : 'http://localhost:8080/admin/actor_film/create',
                    method: 'POST',
                    data : {
                        id : uuidv4(),
                        id_film : film_id,
                        id_actor : actor_id,
                        name_in : result.value
                    },
                    success:  (e)=>{
                        Swal.fire(
                            'Thêm thành công!',
                            'Diên viên đã thêm cho film thành công!.',
                            'success'
                        );
                        $(my_node).trigger('click');
                    },
                    error: (e)=>{
                        Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                        $(my_node).trigger('click');
                    }
                });
            }
          })
    });
    $(advance_node.add_cate).on('click', (e)=>{
        var id_category = $(advance_node.category_input).val();
        $.ajax({
            url : 'http://localhost:8080/admin/category_film/create',
            method: 'POST',
            data : {
                id : uuidv4(),
                id_film : film_id,
                id_category : id_category
            },
            success:  (e)=>{
                Swal.fire(
                    'Thêm thành công!',
                    'Thể loại đã thêm cho film thành công!.',
                    'success'
                );
                $(my_node).trigger('click');
            },
            error: (e)=>{
                Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                $(my_node).trigger('click');
            }
        });
    });
    $(advance_node.add_country).on('click', (e)=>{
        var id_country = $(advance_node.country_input).val();
        $.ajax({
            url : 'http://localhost:8080/admin/country_film/create',
            method: 'POST',
            data : {
                id : uuidv4(),
                id_film : film_id,
                id_country : id_country
            },
            success:  (e)=>{
                Swal.fire(
                    'Thêm thành công!',
                    'Quốc gia đã thêm cho film thành công!.',
                    'success'
                );
                $(my_node).trigger('click');
            },
            error: (e)=>{
                Swal.fire({title:'Error',text:'Có lỗi xảy ra khi lưu- server-error',type: 'error'});
                $(my_node).trigger('click');
            }
        });
    });
    $(advance_node.country_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_country = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/country_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_country : id_country
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
    $(advance_node.cate_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_category = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/category_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_category : id_category
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
    $(advance_node.actor_remove).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_actor = this.getAttribute("data-id");
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
                    $.ajax({
                        url : 'http://localhost:8080/admin/actor_film/remove',
                        method: 'POST',
                        data : {
                            id_film : film_id,
                            id_actor : id_actor
                        },
                        success:  (e)=>{
                            Swal.fire(
                                'Deleted!',
                                'Film đã được xóa thành công.',
                                'success'
                              )
                            $(my_node).trigger('click');
                        },
                        error : (e)=>{
                            Swal.fire({title:'Error',text:'Có lỗi xảy ra khi xóa- server-error',type: 'error'});
                        }
                    });
                  
                }
              })
        });
    });
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
                    initEventAdvanceSettings(film.id, node);
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
    });
    $(link_var.show_link).each(function () {
        var node = this;
        $(node).on('click',function(e){
            let id_film = this.getAttribute("data-id");
            $(modal_var.modal_title).text('Link film - ' + id_film);
            $.ajax({
                url : 'http://localhost:8080/admin/link',
                method: 'GET',
                data : {id_film : id_film},
                success:  (e)=>{
                    $(modal_var.modal_body).html(film_eps(e));
                    $(modal_var.modal_submit).css('display','none');
                    initEventLink(e, id_film,node);
                    $(my_modal).slideDown();
                },
                error : (e)=>{
                    Swal.fire({title:'Error',text:'Có lỗi xảy ra khi load dử liệu của video - server-error',type: 'error'});
                }
            });
        });
    });
}