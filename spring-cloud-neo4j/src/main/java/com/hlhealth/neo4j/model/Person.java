package com.hlhealth.neo4j.model;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "person")
public class Person {
	
	@Id
	@GeneratedValue
	private Long  id;
	
	private String name;
	
	@Relationship(type = "relations", direction = Relationship.UNDIRECTED)
	private Set<Person> relations;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Person> getRelations() {
		return relations;
	}

	public void setRelations(Set<Person> relations) {
		this.relations = relations;
	}

}
