package pers.caijx.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import pers.caijx.elasticsearch.dto.BankES;

public interface BankESRepository extends ElasticsearchCrudRepository<BankES,Long> {
}
