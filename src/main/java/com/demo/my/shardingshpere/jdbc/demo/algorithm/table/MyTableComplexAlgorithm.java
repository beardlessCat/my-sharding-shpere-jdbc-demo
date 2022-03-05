package com.demo.my.shardingshpere.jdbc.demo.algorithm.table;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 */
public class MyTableComplexAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Integer> complexKeysShardingValue) {
        Collection<Integer> deviceTypeValues = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("device_type");
        //存放指定表
        Collection<String> tables = new ArrayList<>();
        for (Integer deviceTypeValue : deviceTypeValues) {
            String tableName = complexKeysShardingValue.getLogicTableName() + "_" + (deviceTypeValue % 2);
            tables.add(tableName);
        }
        return tables;
    }
}
