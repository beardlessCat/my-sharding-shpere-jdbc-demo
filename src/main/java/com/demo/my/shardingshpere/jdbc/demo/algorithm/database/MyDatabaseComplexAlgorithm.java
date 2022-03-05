package com.demo.my.shardingshpere.jdbc.demo.algorithm.database;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 */
public class MyDatabaseComplexAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    /**
     *
     * @param collection
     * @param complexKeysShardingValue
     * @return 这一次要查找的数据节点（数据库）集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Integer> complexKeysShardingValue) {
        //获得device_type的值
        Collection<Integer> deviceTypeValues = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("device_type");
        Collection<String> databases = new ArrayList<>();
        for (Integer deviceTypeValue : deviceTypeValues) {
            String databaseName = "ds" + (deviceTypeValue % 2);
            databases.add(databaseName);
        }
        return databases;
    }
}
