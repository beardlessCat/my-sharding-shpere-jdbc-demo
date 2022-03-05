package com.demo.my.shardingshpere.jdbc.demo.algorithm.table;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 精确分片
 *
 * @param collection           具体的物理的数据库的集合
 * @param preciseShardingValue 分片的参数
 * @return 定位到的数据库
 */

public class MyTableStandardPreciseAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long value = preciseShardingValue.getValue();
        //tb_device_1
        String tableName = preciseShardingValue.getLogicTableName() + "_" + (value % 2);
        if (!collection.contains(tableName)) {
            throw new UnsupportedOperationException("表：" + tableName + ",不存在");
        }
        return tableName;
    }
}
