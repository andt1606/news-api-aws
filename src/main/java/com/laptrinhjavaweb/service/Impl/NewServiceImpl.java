package com.laptrinhjavaweb.service.Impl;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewServiceImpl implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        if(newDTO.getId()!=null){
            NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
            newEntity = newConverter.toEntity(newDTO,oldNewEntity);
        }else {
            newEntity = newConverter.toEntity(newDTO);
        }
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        //@ManyToOne nên ko có id mà là 1 đối tượng entity luôn nên set thì set entity vô
        newEntity.setCategory(categoryEntity);
        //lưu xuống thì hàm save trả về entity do đó ta override ngược lại
        newEntity = newRepository.save(newEntity);
        return newConverter.toDTO(newEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item: ids){
            newRepository.delete(item);
        }
    }

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> results = new ArrayList<>();        //truyền vô nó tự thêm limit cho mình
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();   //lấy findAll có pageable
        for (NewEntity item: entities){
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)newRepository.count();
    }

//    @Override
//    public NewDTO update(NewDTO newDTO) {
//        //update thì lấy lại dữ liệu cũ
//        NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
//        NewEntity newEntity = newConverter.toEntity(newDTO,oldNewEntity);
//        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
//        newEntity.setCategory(categoryEntity);
//        newEntity = newRepository.save(newEntity);
//        return newConverter.toDTO(newEntity);
//    }
}
