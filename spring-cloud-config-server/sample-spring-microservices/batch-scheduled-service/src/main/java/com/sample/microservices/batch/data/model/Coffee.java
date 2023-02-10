package com.sample.microservices.batch.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coffee")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coffee_id")
	private Long id;
	
	@Column(name="brand")
    private String brand;
	
	@Column(name="origin")
    private String origin;
	
	@Column(name="characteristics")
    private String characteristics;
}
