package br.edu.ifsp.dsw.company.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Integer id;
	private String description;
	private Double price;
	private String client;
	private String address;
	private User user;
}
