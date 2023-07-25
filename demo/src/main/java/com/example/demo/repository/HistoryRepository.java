package com.example.demo.repository;

import com.example.demo.model.database.jpa.Taxes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends PagingAndSortingRepository<Taxes, Long> {

  //  List<Taxes> findAllByTimestamp(String timestamp, Pageable pageable);

}