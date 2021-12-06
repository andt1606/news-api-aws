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
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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

    @Override
    public List<NewDTO> getAllNews() {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll();
        for (NewEntity item: entities){
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }
        return results;
    }

    @Override
    public NewDTO getOneNew(Long id) {
        NewEntity entity = newRepository.findOne(id);
        NewDTO newDTO = newConverter.toDTO(entity);
        return newDTO;
    }

    @Override
    public void delete(Long id) {
        newRepository.delete(id);
    }

    @Override
    public NewDTO creNew(Map<String, Object> params, MultipartFile file) throws IOException {
        NewEntity newEntity = new NewEntity();
        NewDTO newDTO = new NewDTO();
        newDTO.setTitle(params.get("title").toString());
        newDTO.setShortDescription(params.get("shortDescription").toString());
        newDTO.setContent(params.get("content").toString());
        newDTO.setCategoryCode(params.get("categoryCode").toString());
        newDTO.setThumbnail(compressBytes(file.getBytes()));
        newEntity = newConverter.toEntity(newDTO);
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategory(categoryEntity);

        newEntity = newRepository.save(newEntity);

        return newConverter.toDTO(newEntity);
    }

    @Override
    public NewDTO updateNew(Long id, Map<String, Object> params, MultipartFile file) throws IOException {
        NewEntity newEntity = new NewEntity();
        NewDTO newDTO = new NewDTO();
        newDTO.setTitle(params.get("title").toString());
        newDTO.setShortDescription(params.get("shortDescription").toString());
        newDTO.setContent(params.get("content").toString());
        newDTO.setCategoryCode(params.get("categoryCode").toString());
        newDTO.setThumbnail(compressBytes(file.getBytes()));

        NewEntity oldNewEntity = newRepository.findOne(id);
        //gán cái mới nhận được cho cái cũ để cập nhật
        newEntity = newConverter.toEntity(newDTO,oldNewEntity);
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategory(categoryEntity);
        newEntity = newRepository.save(newEntity);


        return newConverter.toDTO(newEntity);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }


}
