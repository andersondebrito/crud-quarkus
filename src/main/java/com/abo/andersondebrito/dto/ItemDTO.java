package com.abo.andersondebrito.dto;

import java.math.BigDecimal;

public class ItemDTO {
	
	private String name;
	private BigDecimal value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
