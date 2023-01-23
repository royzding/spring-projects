package com.sample.microservices.department.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sample.microservices.common.data.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This Entity is for testing only since day/month/year is the latest H2 DB's reserved words 
 */


@Entity
@Table(name="holiday_date")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HolidayDateEntity extends BaseEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	@NotNull
	@Column(name="`day`")
	private Integer day;

	@NotNull
	@Column(name="`month`")
	private Integer month;

	@NotNull
	@Column(name="`year`")
	private Integer year;
	
}
