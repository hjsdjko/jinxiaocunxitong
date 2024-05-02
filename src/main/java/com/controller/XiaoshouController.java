package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.XiaoshouEntity;

import com.service.XiaoshouService;
import com.entity.view.XiaoshouView;
import com.service.GoodsService;
import com.entity.GoodsEntity;
import com.service.KehuService;
import com.entity.KehuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 销售订单
 * 后端接口
 * @author
 * @email
 * @date 2021-04-22
*/
@RestController
@Controller
@RequestMapping("/xiaoshou")
public class XiaoshouController {
    private static final Logger logger = LoggerFactory.getLogger(XiaoshouController.class);

    @Autowired
    private XiaoshouService xiaoshouService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private KehuService kehuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = xiaoshouService.queryPage(params);

        //字典表数据转换
        List<XiaoshouView> list =(List<XiaoshouView>)page.getList();
        for(XiaoshouView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XiaoshouEntity xiaoshou = xiaoshouService.selectById(id);
        if(xiaoshou !=null){
            //entity转view
            XiaoshouView view = new XiaoshouView();
            BeanUtils.copyProperties( xiaoshou , view );//把实体数据重构到view中

            //级联表
            GoodsEntity goods = goodsService.selectById(xiaoshou.getGoodsId());
            if(goods != null){
                BeanUtils.copyProperties( goods , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setGoodsId(goods.getId());
            }
            //级联表
            KehuEntity kehu = kehuService.selectById(xiaoshou.getKehuId());
            if(kehu != null){
                BeanUtils.copyProperties( kehu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setKehuId(kehu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XiaoshouEntity xiaoshou, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaoshou:{}",this.getClass().getName(),xiaoshou.toString());
            Integer goodsId = xiaoshou.getGoodsId();
            if(goodsId== null){
                return R.error(511,"商品id为空");
            }
            GoodsEntity goodsEntity = goodsService.selectById(goodsId);
            if(goodsEntity == null || goodsEntity.getGoodsNumber() == null) {
                return R.error(511,"商品查询不到或者商品数量不正确");
            }
            Integer surplusNumber = goodsEntity.getGoodsNumber() - xiaoshou.getXiaoshouNumber();
            if(surplusNumber < 0){
                return R.error(511,"销售数量大于库存数量");
            }
            xiaoshou.setInsertTime(new Date());
            xiaoshou.setCreateTime(new Date());
            xiaoshouService.insert(xiaoshou);//新增销售记录
            goodsEntity.setGoodsNumber(surplusNumber);
            goodsService.updateById(goodsEntity);//更新库存数量
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaoshouEntity xiaoshou, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaoshou:{}",this.getClass().getName(),xiaoshou.toString());
        //根据字段查询是否有相同数据
        Wrapper<XiaoshouEntity> queryWrapper = new EntityWrapper<XiaoshouEntity>()
            .notIn("id",xiaoshou.getId())
            .andNew()
            .eq("goods_id", xiaoshou.getGoodsId())
            .eq("kehu_id", xiaoshou.getKehuId())
            .eq("xiaoshou_number", xiaoshou.getXiaoshouNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaoshouEntity xiaoshouEntity = xiaoshouService.selectOne(queryWrapper);
        if(xiaoshouEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xiaoshou.set
            //  }
            xiaoshouService.updateById(xiaoshou);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaoshouService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

