/*
 * Create a listener for Spring Data MongoDB lifecycle events, so
 * every time we save a new User, the sequence id will be set automatically.
 * 
 * */

package com.lele.empcrud.events;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import com.lele.empcrud.models.Employee;
import com.lele.empcrud.services.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class EmployeeModelListener extends AbstractMongoEventListener<Employee> {

	private SequenceGeneratorService sequenceGenerator;

	@Autowired
    public EmployeeModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
		if (event.getSource().getId() < 1) {
			event.getSource().setId(sequenceGenerator.generateSequence(Employee.SEQUENCE_NAME));
		}
	}

}
