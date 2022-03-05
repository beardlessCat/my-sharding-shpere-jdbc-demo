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
    //数据插入
    @Test
    void testInitData(){
        for (int i = 0; i < 10; i++) {
            TbDevice device = new TbDevice();
            device.setDeviceId((long) i);
            device.setDeviceType(i);
            deviceMapper.insert(device);
        }
    }
    //数据查询
    @Test
    void testQueryByDeviceId(){
        QueryWrapper<TbDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id",2L);
        List<TbDevice> deviceList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceList);
    }
    //范围查询
    @Test
    void testQueryByRange(){
        QueryWrapper<TbDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("device_id",1,10);
        List<TbDevice> deviceList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceList);
    }

    @Test
    void queryDeviceByRangeAndDeviceType(){
        QueryWrapper<TbDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("device_id",1,10);
        queryWrapper.eq("device_type",5);
        List<TbDevice> deviceList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceList);
    }

    @Test
    void testHint(){
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("tb_device",0);
        List<TbDevice> tbDevices = deviceMapper.selectList(null);
        tbDevices.forEach( tbDevice -> System.out.println(tbDevice));
    }

    @Test
    void testInsertDeviceInfo(){

        for (int i = 0; i < 10; i++) {
            TbDevice device = new TbDevice();
            device.setDeviceId((long) i);
            device.setDeviceType(i);
            deviceMapper.insert(device);

            TbDeviceInfo deviceInfo = new TbDeviceInfo();
            deviceInfo.setDeviceId((long) i);
            deviceInfo.setDeviceIntro(""+i);
            deviceInfoMapper.insert(deviceInfo);
        }


    }

    @Test
    void testJoinSelect(){

        List<TbDeviceInfo> deviceInfos = deviceInfoMapper.queryDeviceInfo();
        deviceInfos.forEach( tbDeviceInfo -> System.out.println(tbDeviceInfo));

    }

    @Test
    void testInsertDeviceType(){


        TbDeviceType deviceType = new TbDeviceType();
        deviceType.setTypeId(1);
        deviceType.setTypeName("人脸考勤");
        deviceTypeMapper.insert(deviceType);

        TbDeviceType deviceType1 = new TbDeviceType();
        deviceType1.setTypeId(2);
        deviceType1.setTypeName("人脸通道");
        deviceTypeMapper.insert(deviceType1);


    }

    @Test
    void testInsertUser(){
        for (int i = 0; i < 10; i++) {
            TbUser user = new TbUser();
            user.setName(""+i);
            userMapper.insert(user);
        }
    }

    @Test
    void testSelectUser(){
        List<TbUser> userList = userMapper.selectList(null);
        userList.forEach(tbUser -> System.out.println(tbUser));
    }

}
