/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sample.microservices.graphql.entity;

import java.math.BigDecimal;

public class SalaryInput {

	private String employeeId;

	private BigDecimal newSalary;

	public SalaryInput(String employeeId, BigDecimal newSalary) {
		this.employeeId = employeeId;
		this.newSalary = newSalary;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getNewSalary() {
		return newSalary;
	}

	public void setNewSalary(BigDecimal newSalary) {
		this.newSalary = newSalary;
	}

}
