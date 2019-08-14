package com.hlhealth.elasticsearch.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlhealth.elasticsearch.model.User;
import com.hlhealth.elasticsearch.service.UserService;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticSearchController {

	@Autowired
	private ElasticsearchTemplate template;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createIndex", method = RequestMethod.GET)
	public String createIndex() {
		boolean isSuccess = template.createIndex(User.class);
		return String.valueOf(isSuccess);
	}

	@RequestMapping(value = "/exists", method = RequestMethod.GET)
	public String exists() {
		boolean isExists = template.indexExists(User.class);
		return String.valueOf(isExists);
	}

	@RequestMapping(value = "/putdata", method = RequestMethod.POST)
	public String putData(@RequestBody String data) throws JsonProcessingException {
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setType("qukte");
		indexQuery.setIndexName("user");
		indexQuery.setSource(data);
		return template.index(indexQuery);
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) throws JsonProcessingException {
		user.setId(UUID.randomUUID().toString());
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public User search(@PathVariable String id) {
		GetQuery query = new GetQuery();
		query.setId(id);
		return template.queryForObject(query, User.class);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Iterable<User> query() {
		return userService.search();
	}
	
	@RequestMapping(value = "/search/highlight", method = RequestMethod.GET)
	public Iterable<User> queryHighlight() {
		return userService.searchHighlight();
	}
}
