package com.dao;

import com.entity.XiaoshouEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaoshouView;

/**
 * 销售订单 Dao 接口
 *
 * @author 
 * @since 2021-04-22
 */
public interface XiaoshouDao extends BaseMapper<XiaoshouEntity> {

   List<XiaoshouView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
