package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

//để có thể nhúng vô các chỗ khác, nó ko cụ thể vào cái nào cả nên muốn nhúng vào chỗ khác thì @Component
//sử dụng dependency injection đối với một class nào đó thì khai báo @Component
@Component
public class NewConverter {
    public NewEntity toEntity(NewDTO dto){
        NewEntity entity = new NewEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());

        return entity;
    }

    public NewDTO toDTO(NewEntity entity){
        NewDTO dto = new NewDTO();
        if(entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setShortDescription(entity.getShortDescription());
        dto.setThumbnail(entity.getThumbnail());
        dto.setCategoryCode(entity.getCategory().getCode());

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }

    public NewEntity toEntity(NewDTO dto, NewEntity entity){
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());

        return entity;
    }

}
