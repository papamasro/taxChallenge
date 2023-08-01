package com.example.demo.repository;

import com.example.demo.model.entity.CallHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryPagRepository extends PagingAndSortingRepository<CallHistoryEntity, Long> {

    //  List<Taxes> findAllByTimestamp(String timestamp, Pageable pageable);

    Page<CallHistoryEntity> findAllByStatusCode(Integer code, Pageable pageable);

}