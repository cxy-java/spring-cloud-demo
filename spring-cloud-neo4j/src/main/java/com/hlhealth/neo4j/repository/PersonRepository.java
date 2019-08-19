package com.hlhealth.neo4j.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import com.hlhealth.neo4j.model.Person;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

	@Query("MATCH (p:person{name:{name}}) RETURN p ")
	List<Person> findByName(@Param("name") String name);
}
