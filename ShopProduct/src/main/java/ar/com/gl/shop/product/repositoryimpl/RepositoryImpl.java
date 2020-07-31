package ar.com.gl.shop.product.repositoryimpl;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.Repository;

public class RepositoryImpl implements Repository {

	private static List<Product> listaProductos;
	private static List<Category> listaCategorias;
	private static List<Stock> listaStock;

	@Override
	public Category saveCategory(Category category) {
		listaCategorias.add(category);
		return category;
	}

	@Override
	public List<Category> findAllCategory() {
		return listaCategorias;
	}

	@Override
	public Stock saveStock(Stock stock) {
		listaStock.add(stock);
		return stock;
	}

	@Override
	public List<Stock> findAllStock() {
		return listaStock;
	}

	@Override
	public Product saveProduct(Product product) {
		listaProductos.add(product);
		return product;
	}

	@Override
	public List<Product> findAllProduct() {
		return listaProductos;
	}

	@Override
	public void deleteCategory(Category category) {
		listaCategorias.remove(category);

	}

	@Override
	public void deleteProduct(Product product) {
		listaProductos.remove(product);

	}

	@Override
	public void deleteStock(Stock stock) {
		listaStock.remove(stock);
	}

	@Override
	public Category findCategoryById(Long id) {
		for (Category category : listaCategorias) {
			if (category.getId().equals(id)) {
				return category;
			} else {
				return null;
			}
		}
		return null;
	}

}