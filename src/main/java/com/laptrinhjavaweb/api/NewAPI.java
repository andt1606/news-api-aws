package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.api.output.NewOutput;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class NewAPI {

	@Autowired
	private INewService newService;

	@GetMapping(value = "/new/{id}")
	public NewDTO showOne(@PathVariable("id") long id) {
		NewDTO newDTO = newService.getOneNew(id);

		return newDTO;
	}

	@GetMapping(value = "/news")
	public List<NewDTO> show(){
		List<NewDTO> results = newService.getAllNews();
		return results;
	}

	@GetMapping(value = "/new")
	public NewOutput showNew(@RequestParam("page") int page,
							 @RequestParam("limit") int limit) {
		NewOutput result = new NewOutput();
		result.setPage(page);               // truyền zo nó tự tính offset
		Pageable pageable = new PageRequest(page -1 ,limit);   //pageable là interface nên phải new 1 cái request, -1 để bên ngoài truyền vô lấy đúng vị trí vd: page =1 thì 2 item đầu tiên
		result.setLstResult(newService.findAll(pageable));
		result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit ));
		return result;
	}

//	@PostMapping(value = "/new")
//	public NewDTO createNew(@RequestBody NewDTO model) {
//		return newService.save(model);
//	}

	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestParam(required = false) Map<String,Object> params,
							@RequestParam("imageFile") MultipartFile file) throws IOException {
		return newService.creNew(params,file);
	}


	
//	@PutMapping(value = "/new/{id}")
//	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
//		model.setId(id);
//		return newService.save(model);
//	}

	@PutMapping(value = "/new/{id}")
	public NewDTO updateNew(@PathVariable("id") long id,
							@RequestParam("imageFile") MultipartFile file,
							@RequestParam(required = false) Map<String,Object> params) throws IOException {
		return newService.updateNew(id,params,file);
	}
	
	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}

	@DeleteMapping(value = "/new/{id}")
	public void deleteOneNew(@PathVariable("id") long id) {
		newService.delete(id);
	}


}
