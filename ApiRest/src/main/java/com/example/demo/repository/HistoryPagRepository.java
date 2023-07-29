package com.example.demo.repository;

import com.example.demo.model.jpa.CallHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryPagRepository extends PagingAndSortingRepository<CallHistory, Long> {

  //  List<Taxes> findAllByTimestamp(String timestamp, Pageable pageable);

}