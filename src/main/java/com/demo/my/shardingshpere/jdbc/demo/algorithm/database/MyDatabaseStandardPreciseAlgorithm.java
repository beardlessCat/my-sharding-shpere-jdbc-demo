package com.demo.my.shardingshpere.jdbc.demo.algorithm.database;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 
 */
public class MyDatabaseStandardPreciseAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * 精确分片
     *
     * @param collection           具体的物理的数据库的集合
     * @param preciseShardingValue 分片的参数
     * @return 定位到的数据库
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        //逻辑表的名称
        String logicTableName = preciseShardingValue.getLogicTableName();
        //分片键
        String columnName = preciseShardingValue.getColumnName();
        //分片键的值
        Long value = preciseShardingValue.getValue();
        String databaseName = "ds" + (value % 2);
        if(!collection.contains(databaseName)){
            throw new UnsupportedOperationException("数据源："+databaseName+"不存在。");
        }
        return databaseName;
    }
}
