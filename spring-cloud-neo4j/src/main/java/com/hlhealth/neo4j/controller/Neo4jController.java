package com.hlhealth.neo4j.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hlhealth.neo4j.model.Person;
import com.hlhealth.neo4j.repository.PersonRepository;

@RestController
@RequestMapping("/neo4j")
public class Neo4jController {

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Person create(@RequestBody Map<String, Object> param) {
		Person person = new Person();
		person.setName((String) param.get("name"));
		Person relation = new Person();
		relation.setName((String) param.get("relationname"));
		Set<Person> relations = new HashSet<Person>();
		relations.add(relation);
		person.setRelations(relations);
		person = personRepository.save(person, 5);
		return person;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		personRepository.deleteById(id);
		return "success";
	}

	@RequestMapping(value = "getbyname", method = RequestMethod.GET)
	public List<Person> getByName(String name) {
		return personRepository.findByName(name);
	}
}
