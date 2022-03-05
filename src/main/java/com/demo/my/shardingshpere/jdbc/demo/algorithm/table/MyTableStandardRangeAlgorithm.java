package com.demo.my.shardingshpere.jdbc.demo.algorithm.table;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 */
public class MyTableStandardRangeAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        //数据来自于两张表
        String logicTableName = rangeShardingValue.getLogicTableName();
        return Arrays.asList(logicTableName + "_0", logicTableName + "_1");
    }
}
