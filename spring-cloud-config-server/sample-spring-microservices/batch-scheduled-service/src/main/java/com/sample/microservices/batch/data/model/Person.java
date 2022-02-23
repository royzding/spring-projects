package com.sample.microservices.batch.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")

@Getter
@Setter
public class Person  implements Serializable {
	
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="modified_by")
	private String modifiedBy = "Base-Dummy";

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", modifiedBy=" + modifiedBy + "]";
	}
	
}