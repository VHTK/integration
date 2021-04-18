package com.jinchi.stock.mapper;

import com.jinchi.stock.domain.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {
    int updateByOptimistic(Stock stock);

    Integer selectByName(@Param("name") String name);
}
