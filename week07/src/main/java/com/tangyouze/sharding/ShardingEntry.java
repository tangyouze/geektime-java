package com.tangyouze.sharding;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.core.strategy.keygen.UUIDShardingKeyGenerator;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingEntry {
    public static void main(String[] args) throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

// 配置主库
        BasicDataSource masterDataSource = new BasicDataSource();
        masterDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        masterDataSource.setUrl("jdbc:mysql://localhost:3309/ds_master");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("TNB7ymq");
        dataSourceMap.put("ds_master", masterDataSource);

// 配置第一个从库
        BasicDataSource slaveDataSource1 = new BasicDataSource();
        slaveDataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveDataSource1.setUrl("jdbc:mysql://localhost:3309/ds_slave0");
        slaveDataSource1.setUsername("root");
        slaveDataSource1.setPassword("TNB7ymq");
        dataSourceMap.put("ds_slave0", slaveDataSource1);

//        -----------------------------------
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave0", "ds_slave1"));
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new Properties());
        Connection conn = dataSource.getConnection();

// 插入数据
        ShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
        long orderId = (Long) keyGenerator.generateKey();
        long userId = 1027543L;
        ShardingKeyGenerator orderGenerator = new UUIDShardingKeyGenerator();
        String orderNum = (String) orderGenerator.generateKey();

        String insertSql = "insert into t_order(order_id, user_id, order_num) values(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertSql);
        ps.setLong(1, orderId);
        ps.setLong(2, userId);
        ps.setString(3, orderNum);
        int result = ps.executeUpdate();
        System.out.println("执行结果数：" + result);

////读取数据
//        String querySql = "select * from t_order";
//        PreparedStatement qryPs = conn.prepareStatement(querySql);
//        ResultSet resultSet = qryPs.executeQuery();
//        while (resultSet.next()){
//            String ud = resultSet.getString("user_id");
//            String om = resultSet.getString("order_num");
//            System.out.println(String.format("user_id = [%s], order_num = [%s]", ud, om));
//        }
//
//// 删除数据
//        String deleteSql = "delete from t_order where user_id = 1027543";
//        PreparedStatement dropPs = conn.prepareStatement(deleteSql);
//        int delResult = dropPs.executeUpdate();
//        System.out.println("删除结果数：" + delResult);

    }

}
