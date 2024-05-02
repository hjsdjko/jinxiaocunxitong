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

import com.entity.RukuEntity;

import com.service.RukuService;
import com.entity.view.RukuView;
import com.service.GongyingshangService;
import com.entity.GongyingshangEntity;
import com.service.GoodsService;
import com.entity.GoodsEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 入库订单
 * 后端接口
 * @author
 * @email
 * @date 2021-04-22
*/
@RestController
@Controller
@RequestMapping("/ruku")
public class RukuController {
    private static final Logger logger = LoggerFactory.getLogger(RukuController.class);

    @Autowired
    private RukuService rukuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private GongyingshangService gongyingshangService;
    @Autowired
    private GoodsService goodsService;


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
        PageUtils page = rukuService.queryPage(params);

        //字典表数据转换
        List<RukuView> list =(List<RukuView>)page.getList();
        for(RukuView c:list){
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
        RukuEntity ruku = rukuService.selectById(id);
        if(ruku !=null){
            //entity转view
            RukuView view = new RukuView();
            BeanUtils.copyProperties( ruku , view );//把实体数据重构到view中

            //级联表
            GongyingshangEntity gongyingshang = gongyingshangService.selectById(ruku.getGongyingshangId());
            if(gongyingshang != null){
                BeanUtils.copyProperties( gongyingshang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setGongyingshangId(gongyingshang.getId());
            }
            //级联表
            GoodsEntity goods = goodsService.selectById(ruku.getGoodsId());
            if(goods != null){
                BeanUtils.copyProperties( goods , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setGoodsId(goods.getId());
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
    public R save(@RequestBody RukuEntity ruku, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ruku:{}",this.getClass().getName(),ruku.toString());
        Integer goodsId = ruku.getGoodsId();
        if(goodsId == null){
            return R.error(511,"入库商品id不能为空");
        }
        GoodsEntity goodsEntity = goodsService.selectById(goodsId);
        if(goodsEntity == null || goodsEntity.getGoodsNumber() == null){
            return R.error(511,"查不到入库商品或者数量不正确.");
        }
        goodsEntity.setGoodsNumber(goodsEntity.getGoodsNumber()+ruku.getRukuNumber());
        ruku.setInsertTime(new Date());
        ruku.setCreateTime(new Date());
        rukuService.insert(ruku);//新增入库订单
        goodsService.updateById(goodsEntity);//增加库存数量
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody RukuEntity ruku, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,ruku:{}",this.getClass().getName(),ruku.toString());
        //根据字段查询是否有相同数据
        Wrapper<RukuEntity> queryWrapper = new EntityWrapper<RukuEntity>()
            .notIn("id",ruku.getId())
            .andNew()
            .eq("goods_id", ruku.getGoodsId())
            .eq("gongyingshang_id", ruku.getGongyingshangId())
            .eq("ruku_number", ruku.getRukuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RukuEntity rukuEntity = rukuService.selectOne(queryWrapper);
        if(rukuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      ruku.set
            //  }
            rukuService.updateById(ruku);//根据id更新
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
        rukuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

