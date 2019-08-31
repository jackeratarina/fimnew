import $ from "jquery";
export var importData = function(selector, data=null, append=null){
    if(data!==null){
        $(selector).html($(data));
    }else if(append !== null){
        $(selector).append($(append));
    }
    
}