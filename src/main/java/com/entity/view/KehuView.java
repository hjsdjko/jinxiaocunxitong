package com.entity.view;

import com.entity.KehuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-22
 */
@TableName("kehu")
public class KehuView extends KehuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 客户性别的值
		*/
		private String sexValue;



	public KehuView() {

	}

	public KehuView(KehuEntity kehuEntity) {
		try {
			BeanUtils.copyProperties(this, kehuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
