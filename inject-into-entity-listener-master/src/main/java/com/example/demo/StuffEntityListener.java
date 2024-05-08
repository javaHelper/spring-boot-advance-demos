package com.example.demo;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class StuffEntityListener {
	@Autowired
	private ObjectFactory<StuffService> stuffServiceProvider;

	@PrePersist
	public void beforePersist(Object target) {
		if (target instanceof Stuff) {
			Stuff stuff = (Stuff) target;
			stuff.setFilledByEntityListener(stuffServiceProvider.getObject().getTextToBeFilledWith());
		}
	}
}
