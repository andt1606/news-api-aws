package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface INewService {

    NewDTO save(NewDTO newDTO);
//    NewDTO update(NewDTO newDTO);
    void delete (long[] ids);
    //để phân trang được trong spring data jpa thì cần khai báo 1 đối tượng Pageable
    List<NewDTO> findAll(Pageable pageable); //import org.springframework.data.domain.Pageable;
    int totalItem();

    List<NewDTO> getAllNews();
    NewDTO getOneNew(Long id);
    void delete(Long id);

    NewDTO creNew(Map<String, Object> params, MultipartFile file) throws IOException;
    NewDTO updateNew(Long id, Map<String, Object> params, MultipartFile file) throws IOException;
}
