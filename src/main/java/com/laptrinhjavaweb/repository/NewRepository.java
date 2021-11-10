package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
// tham số đầu là repo sẽ thao tác với bảng nào, thì bảng đó dc ánh xạ qua entity, tham số thứ 2 là kiểu dữ liệu
//của khóa chính của bảng đó
public interface NewRepository extends JpaRepository<NewEntity, Long> {
    // ko cần implement class vì nó viết sẵn trong JpaRepository rồi nên chỉ cần mỗi Interface class này thôi

}
