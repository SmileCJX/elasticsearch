package pers.caijx.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.elasticsearch.dto.Bank;
import pers.caijx.elasticsearch.repository.BankESRepository;

@RestController
public class BankController {

    @Autowired
    private BankESRepository repositoryES;

    @GetMapping("/create")
    public String create(@RequestParam("id") Long id,
                         @RequestParam("account_number") String account_number,
                         @RequestParam("balance") String balance) {
        Bank bankES = new Bank();
        bankES.setId(id);
        bankES.setAccount_number(account_number);
        bankES.setBalance(balance);
        return repositoryES.save(bankES).toString();
    }
}
