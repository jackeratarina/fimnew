import {edit_film,delete_film,class_pics,set_active,show_info, table_body} from './constant.js';
import {Film, film_node} from './temp.js';
import {importData} from './table';
import $ from "jquery";
import {initEventTable} from './event';
// ES6 Modules or TypeScript
import Swal from 'sweetalert2';
// var nodes = new Film("00016704-bd2a-4b4f-99af-0473c43dfa93","16/4/2016","Trụ sở cảnh sát Nhật Bản bị đột kích bởi một gián điệp, những tài liệu mật của những cơ quan tình báo hàng đầu như MI6, CIA, FIB có nguy cơ bị đánh cắp. Tuy nhiên những nhân viên an ninh phụ trách An Ninh Quốc Gia do Amuro chỉ huy đã ngăn cản kịp thời. Ngày hôm sau, Conan cùng các thám tử nhí đến tham quan thủy cung ở Tokyo, tại đây Conan gặp một người phụ nữ xinh đẹp nhưng bị thương và đang ở một mình, cô gái kỳ lạ có đôi mắt mang hai màu khác biệt đang trong tình trạng mất trí nhớ. Cô gái có hành tung bí ẩn đang giữ những bí mật động trời mà băng nhóm áo đen đang ráo riết truy lùng… *Chú thích:– MI6 (Secret Intelligence Service (SIS), Military Intelligence, Section 6): Cục tình báo mật.– BND (Bundesnachrichtendienst, Federal Intelligence Service): Cơ quan tình báo Liên bang Đức.– CIA (Central Intelligence Agency): Cơ quan tình báo Trung ương Hoa Kì.– FBI (Federal Bureau of Investigation): Cục điều tra Liên bang Mỹ.Xem thêm thông tin mới tại: kenhsinhvien.net/conan","112","http://image.phimmoi.net/film/3518/poster.medium.jpg","NULL","http://www.phimmoi.net/phim/tham-tu-conan-movie-20-con-ac-mong-den-toi-3518/","Thám tử Conan Movie 20: Cơn Ác Mộng Đen Tối","Detective Conan Movie 20: The Darkest Nightmare","NULL","Hoàn t?t ","1","2016-11-10 14:30:07.000");
// importData(table_body,film_node(nodes,1));
initEventTable();