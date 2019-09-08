package pers.caijx.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pers.caijx.elasticsearch.dto.Bank;

public interface BankESRepository extends ElasticsearchRepository<Bank,Long> {
}