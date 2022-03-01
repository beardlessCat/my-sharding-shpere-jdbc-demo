package com.demo.my.shardingshpere.jdbc.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.my.shardingshpere.jdbc.demo.entity.TbDevice;
import com.demo.my.shardingshpere.jdbc.demo.entity.TbDeviceInfo;
import com.demo.my.shardingshpere.jdbc.demo.entity.TbDeviceType;
import com.demo.my.shardingshpere.jdbc.demo.entity.TbUser;
import com.demo.my.shardingshpere.jdbc.demo.mapper.DeviceInfoMapper;
import com.demo.my.shardingshpere.jdbc.demo.mapper.DeviceMapper;
import com.demo.my.shardingshpere.jdbc.demo.mapper.DeviceTypeMapper;
import com.demo.my.shardingshpere.jdbc.demo.mapper.UserMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyShardingShpereJdbcDemoApplicationTests {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInitData(){
        for (int i = 0; i < 10; i++) {
            TbDevice device = new TbDevice();
            device.setDeviceId((long) i);
            device.setDeviceType(i);
            deviceMapper.insert(device);
        }
    }

    @Test
    void testQueryByDeviceId(){
        QueryWrapper<TbDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id",2L);
        List<TbDevice> deviceList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceList);
    }

    @Test
    void testQueryByRange(){
        QueryWrapper<TbDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("device_id",1,10);
        List<TbDevice> deviceList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceList);
    }



}
