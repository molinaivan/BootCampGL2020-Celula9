package ar.com.gl.shop.product.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true , nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@OneToMany(mappedBy = "category", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Product> products;

	public Category() {
		this.enabled = true;
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
		this.enabled = true;
	}

}
