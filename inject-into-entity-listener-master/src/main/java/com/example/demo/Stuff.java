package com.example.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(StuffEntityListener.class)
public class Stuff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String filledByEntityListener;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilledByEntityListener() {
		return filledByEntityListener;
	}

	public void setFilledByEntityListener(String filledByEntityListener) {
		this.filledByEntityListener = filledByEntityListener;
	}
}
