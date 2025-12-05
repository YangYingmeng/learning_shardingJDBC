
package com.yym.sharding.strategy;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;


public class CustomDBRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    /**
     * @param dataSourceNames 数据源集合
     *                        在分库时值为所有分片库的集合 databaseNames
     *                        分表时为对应分片库中所有分片表的集合 tablesNames
     * @param shardingValue   分片属性，包括
     *                        logicTableName 为逻辑表，
     *                        columnName 分片健（字段），
     *                        value 为从 SQL 中解析出的分片健的值
     * @return
     */

    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames, RangeShardingValue<Long> shardingValue) {
        Set<String> result = new LinkedHashSet<>();

        //between 开始值
        Long lower = shardingValue.getValueRange().lowerEndpoint();

        //between 结束值
        Long upper = shardingValue.getValueRange().upperEndpoint();

        for (long i = lower; i <= upper; i++) {
            for (String datasource : dataSourceNames) {
                String value = i % dataSourceNames.size() + "";
                if (datasource.endsWith(value)) {
                    result.add(datasource);
                }
            }

        }
        return result;
    }
}

