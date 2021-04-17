package com.jinchi.order.config;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class TableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String tb_name = preciseShardingValue.getLogicTableName() + "_";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(preciseShardingValue.getValue());
            String year = String.format("%tY", date);
            String mon = String.format("%tm", date);
            String dat = String.format("%td", date);
            tb_name = tb_name + year + mon + dat;
            System.out.println("tb_name:" + tb_name);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (String each : collection) {
            System.out.println("tb:" + each);
            if (each.equals(tb_name)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}