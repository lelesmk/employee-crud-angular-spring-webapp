/*
 * Business logic (Service layer) for the database sequences.
 * 
 * We've created the required database_sequences and employee collections and models. 
 * Now, we'll create a service that'll generate the auto-incremented value that can 
 * be used as id for our entities.
 * 
 * */

package com.lele.empcrud.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.lele.empcrud.models.DatabaseSequence;

@Service
public class SequenceGeneratorService {

	private MongoOperations mongoOperations;

	@Autowired // Inject the MongoOperations object dependency implicitly
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	// method to generate the sequence value
	public long generateSequence(String seqName) {
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
