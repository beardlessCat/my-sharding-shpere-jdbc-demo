package com.demo.my.shardingshpere.jdbc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.my.shardingshpere.jdbc.demo.entity.TbDeviceInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 */
public interface DeviceInfoMapper extends BaseMapper<TbDeviceInfo> {

    @Select("select a.id,a.device_id,a.device_intro,b.device_type from tb_device_info a left join tb_device b on a.device_id = b.device_id ")
    public List<TbDeviceInfo> queryDeviceInfo();
}
