package ar.com.gl.shop.product.services;

import java.util.List;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Resources;

public interface CategoryService {
	
	public Category create(String name, String description);
	public List<Category> findAll();
	/**
	 * Este metodo sirve para buscar un elemento por id pasado por parametro,
	 * el segundo parametro es la manera de buscar, si se pasa true va a hacer una busqueda
	 * solamente en los elementos con el atributo enabled == true,
	 * si pasamos por parametro false, entonces busca en todos los elementos en memoria.
	 * 
	 * @param id
	 * @param bool
	 * @return Repository
	 */
	public Category findById(Long id, Boolean bool);
	public Category update(Category theCategory);
	public Category deleteById(Category theCategory);
	public Category forceDeleteById(Category theCategory);
	public List<Category> findAllDisabled();


}
