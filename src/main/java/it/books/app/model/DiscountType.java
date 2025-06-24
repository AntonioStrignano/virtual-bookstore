package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "discout_types")
public class DiscountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// name
	@NotBlank(message = "Discout type name missing.")
	@Column(name = "type_name", nullable = false, unique = true, columnDefinition = "tinytext")
	private String typeName;

	// discount rule
	@NotBlank(message = "Discount type rule missing.")
	@Column(name = "discount_rule", nullable = false, columnDefinition = "mediumtext")
	private String discountRule;

	// FKs
	// discounts OtM (mapped by "discountType" )
	@OneToMany(mappedBy = "discountType")
	private List<Discount> discounts;

	// ----------------------------
	// ----- GETTERS & SETTERS ----
	// ----------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDiscountRule() {
		return discountRule;
	}

	public void setDiscountRule(String discountRule) {
		this.discountRule = discountRule;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

}
