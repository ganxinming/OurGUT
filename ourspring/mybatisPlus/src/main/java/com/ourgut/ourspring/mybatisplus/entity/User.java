package com.ourgut.ourspring.mybatisplus.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/5/27
 * @description
 *
 * @TableId(type = IdType.ID_WORKER)：默认，全局唯一ID
 *
 * @TableId(type = IdType.AUTO)：主键自增，使用该主键生成策略，数据库对应的字段一定是自增的！！
 *
 * @TableId(type = IdType.NONE)：未设置主键
 *
 * @TableId(type = IdType.INPUT)：手动输入
 *
 * @TableId(type = IdType.ID_WORKER_STR)：ID_WORKER的字符串形式
 *
 * @TableId(type = IdType.UUID)：全局唯一ID
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	@TableId(type = IdType.ID_WORKER)
	private Long id;
	private String name;
	private Integer age;
	private String email;

	@TableField(fill = FieldFill.INSERT)  // 在插入的时候，自动填充内容 搭配MetaObjectHandler使用
	private Date gmtCreate;
	@TableField(fill = FieldFill.INSERT_UPDATE)  // 在插入和更新的时候，自动填充内容
	private Date gmtModified;

	@Version  // 乐观锁的version注解，注意需要在插入时默认先设置1，之后的update操作才会进行version+1的操作
	private Integer version;

	@TableLogic  // 逻辑删除，注意需要在插入时默认先设置0,即不进行物理删除，仅把这个设置成另一个值，还需在config和yml进行配置
	private Integer deleted;
}
