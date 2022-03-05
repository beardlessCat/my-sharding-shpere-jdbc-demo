package com.demo.my.shardingshpere.jdbc.demo.algorithm.database;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * 
 */
public class MyDatabaseStandardRangeAlgorithm implements RangeShardingAlgorithm<Long> {
    /**
     * 直接范围两个数据源
     * @param collection 具体的物理数据库
     * @param rangeShardingValue 分片的信息
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        //直接范围两个数据源
        return collection;
    }
}
