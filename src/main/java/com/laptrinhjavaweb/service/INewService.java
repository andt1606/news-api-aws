package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {

    NewDTO save(NewDTO newDTO);
//    NewDTO update(NewDTO newDTO);
    void delete (long[] ids);
    //để phân trang được trong spring data jpa thì cần khai báo 1 đối tượng Pageable
    List<NewDTO> findAll(Pageable pageable); //import org.springframework.data.domain.Pageable;
    int totalItem();

    List<NewDTO> findAll();
}
