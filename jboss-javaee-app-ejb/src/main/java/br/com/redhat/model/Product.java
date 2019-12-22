package br.com.redhat.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@Entity
@Table(name = "products", uniqueConstraints={@UniqueConstraint(columnNames={"sku"})})
@JsonIgnoreProperties(value= {"producutOrders"})
public class Product extends DateCommon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@NotNull
	@Size(min = 1, max = 25)
	@Column
	private String name;

	@NotNull
	@NotEmpty
	@Column
	private String sku;

	@NotNull
	@Column
	private BigDecimal coast;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getCoast() {
		return coast;
	}

	public void setCoast(BigDecimal coast) {
		this.coast = coast;
	}

}
