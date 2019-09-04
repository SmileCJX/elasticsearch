package pers.caijx.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.elasticsearch.dto.BankES;
import pers.caijx.elasticsearch.repository.BankESRepository;

@RestController
public class BankController {

    @Autowired
    private BankESRepository bankESRepository;

    @GetMapping("/create")
    public String create(@RequestParam("id") Long id,
                         @RequestParam("account_number") String account_number,
                         @RequestParam("balance") String balance) {
        BankES bankES = new BankES();
        bankES.setId(id);
        bankES.setAccount_number(account_number);
        bankES.setBalance(balance);
        return bankESRepository.save(bankES).toString();
    }
}
