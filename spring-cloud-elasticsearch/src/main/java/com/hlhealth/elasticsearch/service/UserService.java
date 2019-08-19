package com.hlhealth.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.hlhealth.elasticsearch.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ElasticsearchTemplate template;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Iterable<User> search() {
		QueryBuilder bulider = new MatchQueryBuilder("desc", "魔头");
		Iterable<User> list = userRepository.search(bulider);
		return list;
	}

	public Iterable<User> searchHighlight() {
		String preTag = "<font color='#dd4b39'>";// google的色值
		String postTag = "</font>";
		QueryBuilder match = new MatchQueryBuilder("desc", "明");
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(match)
				.withHighlightFields(new HighlightBuilder.Field("desc").preTags(preTag).postTags(postTag)).build();
		AggregatedPage<User> page = template.queryForPage(searchQuery, User.class, new SearchResultMapper() {
			@SuppressWarnings("unchecked")
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				SearchHits hits = response.getHits();
				List<User> list = new ArrayList<User>();
				for (SearchHit searchHit : hits) {
					if (hits.getHits().length <= 0) {
						return null;
					}
					Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
					User user = new User();
					HighlightField field = searchHit.getHighlightFields().get("desc");
					user.setId((String) sourceAsMap.get("id"));
					user.setAge((Integer) sourceAsMap.get("age"));
					user.setUsername((String) sourceAsMap.get("username"));
					user.setDesc((String) sourceAsMap.get("desc"));
					if (field != null) {
						user.setDesc(field.fragments()[0].toString());
					}
					list.add(user);
				}

				return new AggregatedPageImpl<T>((List<T>) list);
			}
		});
		return page.getContent();
	}

}
