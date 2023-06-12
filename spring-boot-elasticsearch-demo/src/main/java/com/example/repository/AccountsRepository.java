package com.example.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.model.Accounts;

public interface AccountsRepository extends ElasticsearchRepository<Accounts, Long> {
	
	List<Accounts> findByLastname(String lastname);
	
	List<Accounts> findByAge(Integer age);

	@Query("{\"match\": { \"firstname\": \"huff dale\"}}")
	List<Accounts> findByFirstname(String firstname);

	@Query("{ \"multi_match\": { \"query\": \"?0\", \"fields\": [\"firstname\", \"address\"]}}")
	List<Accounts> findByMultiMatch(String query);

	@Query("{ \"match_phrase\": { \"address\": \"?0\" }}")
	List<Accounts> findByAddressForMatchPhrase(String phrase);

	// Wild Card
	@Query("{ \"wildcard\": { \"firstname\": { \"value\": \"h*ll\" }}}")
	List<Accounts> findByFirstnameWildCard(String query);

	// Compound Query
	@Query("{ \"bool\": { \"must\": { \"term\": {\"address\" : \"?0\"}}}}")
	List<Accounts> findByAddressTerm(String term);

	@Query("{ \"bool\": {\"must\": { \"term\" : {\"gender\": \"?0\"  }}}}")
	List<Accounts> findByCompoundQuery(String gender);

	@Query("{ \"bool\": {\"must\": { \"term\" : {\"address\": \"?0\" }}, \"must_not\": {\"term\" : { \"age\" : \"?1\"}}}}")
	List<Accounts> findByCompoundQuery2(String address, Integer age);

	@Query("{\"fuzzy\": {\"city\": \"?0\"}}")
	List<Accounts> findByCityFuzzy(String city);

	@Query("{\"range\": {\"balance\": {\"gte\": ?0}}}")
	List<Accounts> findByRangeDSL(Integer balance);

	@Query("{\n" +
			"    \"bool\": {\"must\": {\"range\" : {\"age\" : {\"gte\" : 30,\"lte\" : 40}}},\n" +
			"    \"must_not\": {\"term\": {\"state\" : \"il\"}}}\n" +
			"}")
	List<Accounts> findByRangeDSL2(Integer age1, Integer age2, String state);
}