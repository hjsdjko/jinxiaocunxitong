package com.entity.vo;

import com.entity.GongyingshangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 供应商
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-22
 */
@TableName("gongyingshang")
public class GongyingshangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 供应商名
     */

    @TableField(value = "gongyingshang_name")
    private String gongyingshangName;


    /**
     * 供应商类型
     */

    @TableField(value = "gongyingshang_types")
    private Integer gongyingshangTypes;


    /**
     * 供应商详情
     */

    @TableField(value = "gongyingshang_content")
    private String gongyingshangContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：供应商名
	 */
    public String getGongyingshangName() {
        return gongyingshangName;
    }


    /**
	 * 获取：供应商名
	 */

    public void setGongyingshangName(String gongyingshangName) {
        this.gongyingshangName = gongyingshangName;
    }
    /**
	 * 设置：供应商类型
	 */
    public Integer getGongyingshangTypes() {
        return gongyingshangTypes;
    }


    /**
	 * 获取：供应商类型
	 */

    public void setGongyingshangTypes(Integer gongyingshangTypes) {
        this.gongyingshangTypes = gongyingshangTypes;
    }
    /**
	 * 设置：供应商详情
	 */
    public String getGongyingshangContent() {
        return gongyingshangContent;
    }


    /**
	 * 获取：供应商详情
	 */

    public void setGongyingshangContent(String gongyingshangContent) {
        this.gongyingshangContent = gongyingshangContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
