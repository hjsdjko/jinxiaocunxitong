package com.entity.view;

import com.entity.RukuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 入库订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-22
 */
@TableName("ruku")
public class RukuView extends RukuEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 gongyingshang
			/**
			* 供应商名
			*/
			private String gongyingshangName;
			/**
			* 供应商类型
			*/
			private Integer gongyingshangTypes;
				/**
				* 供应商类型的值
				*/
				private String gongyingshangValue;
			/**
			* 供应商详情
			*/
			private String gongyingshangContent;

		//级联表 goods
			/**
			* 商品名字
			*/
			private String goodsName;
			/**
			* 商品种类
			*/
			private Integer goodsTypes;
				/**
				* 商品种类的值
				*/
				private String goodsValue;
			/**
			* 商品数量
			*/
			private Integer goodsNumber;
			/**
			* 商品图片
			*/
			private String goodsPhoto;
			/**
			* 单位
			*/
			private String danwei;
			/**
			* 单价
			*/
			private Double danjia;
			/**
			* 商品详情
			*/
			private String goodsContent;

	public RukuView() {

	}

	public RukuView(RukuEntity rukuEntity) {
		try {
			BeanUtils.copyProperties(this, rukuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}














				//级联表的get和set gongyingshang
					/**
					* 获取： 供应商名
					*/
					public String getGongyingshangName() {
						return gongyingshangName;
					}
					/**
					* 设置： 供应商名
					*/
					public void setGongyingshangName(String gongyingshangName) {
						this.gongyingshangName = gongyingshangName;
					}
					/**
					* 获取： 供应商类型
					*/
					public Integer getGongyingshangTypes() {
						return gongyingshangTypes;
					}
					/**
					* 设置： 供应商类型
					*/
					public void setGongyingshangTypes(Integer gongyingshangTypes) {
						this.gongyingshangTypes = gongyingshangTypes;
					}


						/**
						* 获取： 供应商类型的值
						*/
						public String getGongyingshangValue() {
							return gongyingshangValue;
						}
						/**
						* 设置： 供应商类型的值
						*/
						public void setGongyingshangValue(String gongyingshangValue) {
							this.gongyingshangValue = gongyingshangValue;
						}
					/**
					* 获取： 供应商详情
					*/
					public String getGongyingshangContent() {
						return gongyingshangContent;
					}
					/**
					* 设置： 供应商详情
					*/
					public void setGongyingshangContent(String gongyingshangContent) {
						this.gongyingshangContent = gongyingshangContent;
					}


				//级联表的get和set goods
					/**
					* 获取： 商品名字
					*/
					public String getGoodsName() {
						return goodsName;
					}
					/**
					* 设置： 商品名字
					*/
					public void setGoodsName(String goodsName) {
						this.goodsName = goodsName;
					}
					/**
					* 获取： 商品种类
					*/
					public Integer getGoodsTypes() {
						return goodsTypes;
					}
					/**
					* 设置： 商品种类
					*/
					public void setGoodsTypes(Integer goodsTypes) {
						this.goodsTypes = goodsTypes;
					}


						/**
						* 获取： 商品种类的值
						*/
						public String getGoodsValue() {
							return goodsValue;
						}
						/**
						* 设置： 商品种类的值
						*/
						public void setGoodsValue(String goodsValue) {
							this.goodsValue = goodsValue;
						}
					/**
					* 获取： 商品数量
					*/
					public Integer getGoodsNumber() {
						return goodsNumber;
					}
					/**
					* 设置： 商品数量
					*/
					public void setGoodsNumber(Integer goodsNumber) {
						this.goodsNumber = goodsNumber;
					}
					/**
					* 获取： 商品图片
					*/
					public String getGoodsPhoto() {
						return goodsPhoto;
					}
					/**
					* 设置： 商品图片
					*/
					public void setGoodsPhoto(String goodsPhoto) {
						this.goodsPhoto = goodsPhoto;
					}
					/**
					* 获取： 单位
					*/
					public String getDanwei() {
						return danwei;
					}
					/**
					* 设置： 单位
					*/
					public void setDanwei(String danwei) {
						this.danwei = danwei;
					}
					/**
					* 获取： 单价
					*/
					public Double getDanjia() {
						return danjia;
					}
					/**
					* 设置： 单价
					*/
					public void setDanjia(Double danjia) {
						this.danjia = danjia;
					}
					/**
					* 获取： 商品详情
					*/
					public String getGoodsContent() {
						return goodsContent;
					}
					/**
					* 设置： 商品详情
					*/
					public void setGoodsContent(String goodsContent) {
						this.goodsContent = goodsContent;
					}
















}
