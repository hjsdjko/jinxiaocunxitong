package com.entity.view;

import com.entity.XiaoshouEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-22
 */
@TableName("xiaoshou")
public class XiaoshouView extends XiaoshouEntity implements Serializable {
    private static final long serialVersionUID = 1L;



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

		//级联表 kehu
			/**
			* 客户姓名
			*/
			private String kehuName;
			/**
			* 客户手机号
			*/
			private String kehuPhone;
			/**
			* 客户身份证号
			*/
			private String kehuIdNumber;
			/**
			* 客户头像
			*/
			private String kehuPhoto;
			/**
			* 客户性别
			*/
			private Integer sexTypes;
				/**
				* 客户性别的值
				*/
				private String sexValue;

	public XiaoshouView() {

	}

	public XiaoshouView(XiaoshouEntity xiaoshouEntity) {
		try {
			BeanUtils.copyProperties(this, xiaoshouEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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




				//级联表的get和set kehu
					/**
					* 获取： 客户姓名
					*/
					public String getKehuName() {
						return kehuName;
					}
					/**
					* 设置： 客户姓名
					*/
					public void setKehuName(String kehuName) {
						this.kehuName = kehuName;
					}
					/**
					* 获取： 客户手机号
					*/
					public String getKehuPhone() {
						return kehuPhone;
					}
					/**
					* 设置： 客户手机号
					*/
					public void setKehuPhone(String kehuPhone) {
						this.kehuPhone = kehuPhone;
					}
					/**
					* 获取： 客户身份证号
					*/
					public String getKehuIdNumber() {
						return kehuIdNumber;
					}
					/**
					* 设置： 客户身份证号
					*/
					public void setKehuIdNumber(String kehuIdNumber) {
						this.kehuIdNumber = kehuIdNumber;
					}
					/**
					* 获取： 客户头像
					*/
					public String getKehuPhoto() {
						return kehuPhoto;
					}
					/**
					* 设置： 客户头像
					*/
					public void setKehuPhoto(String kehuPhoto) {
						this.kehuPhoto = kehuPhoto;
					}
					/**
					* 获取： 客户性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 客户性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 客户性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 客户性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}












}
