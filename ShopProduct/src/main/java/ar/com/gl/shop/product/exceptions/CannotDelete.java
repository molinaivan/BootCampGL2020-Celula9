package ar.com.gl.shop.product.exceptions;

public class CannotDelete extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String message ;

	public String getMessage() {
		return message;
	}

	public CannotDelete(String message) {
		this.message = message;
	}
	
	public CannotDelete() {
		this.message = "Item no encontrado";
	}

}