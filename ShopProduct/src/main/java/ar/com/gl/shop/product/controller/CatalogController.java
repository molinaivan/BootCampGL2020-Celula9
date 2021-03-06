package ar.com.gl.shop.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.dto.CategoryDTO;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.service.impl.CategoryServiceImpl;
import ar.com.gl.shop.product.utils.CategoryDTOConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Catalog Controller")
public class CatalogController {
 
	private CategoryDTOConverter categoryDTOConverter;
		
	private CategoryServiceImpl categoryServiceImpl;

	public CatalogController(CategoryDTOConverter categoryDTOConverter, CategoryServiceImpl categoryServiceImpl) {
		this.categoryDTOConverter = categoryDTOConverter;
		this.categoryServiceImpl = categoryServiceImpl;
	}

	@ApiOperation(value = "return all cateogries", response = CategoryDTO.class, responseContainer = "List")
	@GetMapping(value="/categories")
	public ResponseEntity<List<CategoryDTO>> findAll(){
		
		return new ResponseEntity<>(categoryDTOConverter.toDTOList(categoryServiceImpl.findAll()), HttpStatus.OK);

	}
	
	@ApiOperation(value = "return category by name", response = CategoryDTO.class)
	@GetMapping(value="/categories/name/{name}")
	public ResponseEntity<CategoryDTO> findbyName(@PathVariable(name = "name") String name) {

		return new ResponseEntity<>(categoryDTOConverter.toDTO(categoryServiceImpl.getByName(name)), HttpStatus.OK);

	}

	@ApiOperation(value = "return category by ID", response = CategoryDTO.class)
	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable(name = "id") Long id) {

		return new ResponseEntity<>(categoryDTOConverter.toDTO(categoryServiceImpl.getById(id, true)), HttpStatus.OK);

	}

	@ApiOperation(value = "create category", response = CategoryDTO.class)
	@PostMapping(value = "/categories")
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<>(
				(categoryDTOConverter.toDTO(categoryServiceImpl.create(categoryDTOConverter.toEntity(categoryDTO)))),
				HttpStatus.CREATED);


	}

	@ApiOperation(value = "update description of category", response = CategoryDTO.class)
	@PatchMapping(value = "/categories/{id}/description")
	public ResponseEntity<CategoryDTO> patch(@PathVariable(name = "id") Long id,
			@RequestParam(name = "description") String description) {

		Category category = categoryServiceImpl.getById(id, true);

		category.setDescription(description);

		return new ResponseEntity<>(categoryDTOConverter.toDTO(categoryServiceImpl.update(category)), HttpStatus.OK);
	}

	@ApiOperation(value = "delete category")
	@DeleteMapping(value = "/categories/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		categoryServiceImpl.delete(id);
		return new ResponseEntity<>("Deleted Successful", HttpStatus.OK);
	}

}