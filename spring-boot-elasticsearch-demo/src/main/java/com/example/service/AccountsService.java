package com.example.service;

import com.example.model.Accounts;
import com.example.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepository repository;

    public List<Accounts> findAllAccounts() {
        return (List<Accounts>) repository.findAll();
    }

    public List<Accounts> findByLastName(String lastname) {
        return repository.findByLastname(lastname);
    }

    public List<Accounts> findByAge(Integer age) {
        return repository.findByAge(age);
    }

    public List<Accounts> findByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    public List<Accounts> findForFirstnameAddress(String query) {
        return repository.findByMultiMatch(query);
    }

    public List<Accounts> findByAddressForMatchPhrase(String phrase) {
        return repository.findByAddressForMatchPhrase(phrase);
    }

    public List<Accounts> findByWildCardFirstname(String pattern) {
        return repository.findByFirstnameWildCard(pattern);
    }

    public List<Accounts> findByAddressTerm(String pattern) {
        return repository.findByAddressTerm(pattern);
    }

    public List<Accounts> findByCompoundQuery1(String gender) {
        return repository.findByCompoundQuery(gender);
    }

    public List<Accounts> findByCompoundQuery2(String address, Integer age) {
        return repository.findByCompoundQuery2(address, age);
    }

    public List<Accounts> findByCityFuzzy(String city){
        return repository.findByCityFuzzy(city);
    }

    public List<Accounts> findByRangeDSL(Integer balance){
        return repository.findByRangeDSL(balance);
    }

    public List<Accounts> findByRangeDSL2(Integer age1, Integer age2, String state){
        return repository.findByRangeDSL2(age1, age2, state);
    }
}