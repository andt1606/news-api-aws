package com.laptrinhjavaweb.api;


import com.laptrinhjavaweb.api.output.NewOutput;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INewService newService;


    @GetMapping(value = "/category/{id}/news")
    public List<NewDTO> getNewsByCat(@PathVariable("id") long id){
        List<NewDTO> results = categoryService.getNews(id);
        return results;
    }

//    @GetMapping(value = "/category/{id}/news")
//    public NewOutput getNewsByCatWithPage(@PathVariable("id") long id,
//                                          @RequestParam("page") int page,
//                                          @RequestParam("limit") int limit) {
//        NewOutput result = new NewOutput();
//        result.setPage(page);               // truyền zo nó tự tính offset
//        Pageable pageable = new PageRequest(page -1 ,limit);   //pageable là interface nên phải new 1 cái request, -1 để bên ngoài truyền vô lấy đúng vị trí vd: page =1 thì 2 item đầu tiên
//        result.setLstResult(newService.findAll(pageable));
//        result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit ));
//        return result;
//    }

    @GetMapping(value = "/categories")
    public List<CategoryDTO> show(){
        List<CategoryDTO> results = categoryService.getAllCategories();
        return results;
    }

    @GetMapping(value = "/category/{id}")
    public CategoryDTO showOne(@PathVariable("id") long id) {
        CategoryDTO categoryDTO = categoryService.getOneCategory(id);
        return categoryDTO;
    }


    @PostMapping(value = "/category")
    public CategoryDTO createNew(@RequestBody CategoryDTO model) {
        return categoryService.save(model);
    }

    @PutMapping(value = "/category/{id}")
    public CategoryDTO updateNew(@RequestBody CategoryDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return categoryService.update(model);
    }

    @DeleteMapping(value = "/category")
    public void deleteAllCategory(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }

    @DeleteMapping(value = "/category/{id}")
    public void deleteOneCategory(@PathVariable("id") long id) {
        categoryService.delete(id);
    }
}
