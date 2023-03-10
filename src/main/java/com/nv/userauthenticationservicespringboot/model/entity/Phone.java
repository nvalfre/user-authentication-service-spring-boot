/**
 * 
 */
package com.nv.userauthenticationservicespringboot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PHONES")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private String userId;

	private long number;

	@Column(name = "city_code")
	private int cityCode;

	@Column(name = "country_code")
	private String countryCode;

	public Phone(long number, int cityCode, String countryCode, String userId) {
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.userId = userId;
	}
}
