package com.example.controller;

import com.example.model.Accounts;
import com.example.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountsService service;

    @GetMapping("/accounts")
    public List<Accounts> findAllAccounts() {
        return service.findAllAccounts();
    }

    @GetMapping("/accounts/{lastname}")
    public List<Accounts> findByLastname(@PathVariable String lastname) {
        return service.findByLastName(lastname);
    }

    @GetMapping("/accounts/age/{age}")
    public List<Accounts> findByAge(@PathVariable Integer age) {
        return service.findByAge(age);
    }

    @GetMapping("/accounts/firstname/{firstname}")
    public List<Accounts> findByAge(@PathVariable String firstname) {
        return service.findByFirstname(firstname);
    }

    @GetMapping("/accounts/multi-match/{query}")
    public List<Accounts> findForFirstnameAddress(@PathVariable String query) {
        return service.findForFirstnameAddress(query);
    }

    @GetMapping("/accounts/match-phrase/{phrase}")
    public List<Accounts> findByAddressForMatchPhrase(@PathVariable String phrase) {
        return service.findByAddressForMatchPhrase(phrase);
    }

    @GetMapping("/accounts/wild-card/{phrase}")
    public List<Accounts> findByFirstnameWildcard(@PathVariable String phrase) {
        return service.findByWildCardFirstname(phrase);
    }

    @GetMapping("/accounts/term/{phrase}")
    public List<Accounts> findByAddressTerm(@PathVariable String phrase) {
        return service.findByAddressTerm(phrase);
    }

    @GetMapping("/accounts/compound-query1/{gender}")
    public List<Accounts> findByCompoundQuery1(@PathVariable String gender) {
        return service.findByCompoundQuery1(gender);
    }

    @GetMapping("/accounts/compound-query2/{address}/{age}")
    public List<Accounts> findByCompoundQuery2(@PathVariable String address, @PathVariable Integer age) {
        return service.findByCompoundQuery2(address, age);
    }

    @GetMapping("/accounts/fuzzy/{city}")
    public List<Accounts> findByCityFuzzy(@PathVariable String city) {
        return service.findByCityFuzzy(city);
    }

    @GetMapping("/accounts/range-dsl/{balance}")
    public List<Accounts> findByRangeDSL(@PathVariable Integer balance) {
        return service.findByRangeDSL(balance);
    }

    @GetMapping("/accounts/range-dsl2/{age1}/{age2}/{state}")
    public List<Accounts> findByRangeDSL2(@PathVariable Integer age1, @PathVariable Integer age2, @PathVariable String state) {
        return service.findByRangeDSL2(age1, age2, state);
    }
}