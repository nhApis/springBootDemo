package com.nhapis.argus.er.message.repository;

import com.github.pagehelper.Page;
import com.nhapis.argus.er.message.model.MessageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageRepository {


    /**
     * 添加单个消息
     */
    @Insert("INSERT INTO `ga_messages`(title,text,mail_receiver,status,send_type,message_type,send_time,user_id) VALUES (#{title},#{text},#{mail_receiver},#{status},#{send_type},#{message_type},#{send_time},#{user_id});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer save(MessageBean bean);

    /**
     * 批量添加消息
     */
    @Insert("<script>"
            + "INSERT INTO `ga_messages`(title,text,mail_receiver,status,send_type,message_type,send_time,user_id) VALUES "
            + "<foreach item = 'item' index = 'index' collection='list' separator=','>"
            + "(#{item.title},#{item.text},#{item.mail_receiver},#{item.status},#{item.send_type},#{item.message_type},#{item.send_time},#{item.user_id})"
            + "</foreach>"
            + "</script>")
    @Options(useGeneratedKeys = true)
    Integer saveList(List<MessageBean> beans);


    /**
     * 修改消息
     * @param bean
     * @return
     */
    @Update("<script>"
            + "UPDATE `ga_messages` "
            + "<set>"
            + "send_time = CURRENT_TIMESTAMP(), "
            + "<if test='title != null'>title = #{title}, </if>"
            + "<if test='text != null'>text = #{text}, </if>"
            + "<if test='mail_receiver != null'>mail_receiver = #{mail_receiver}, </if>"
            + "<if test='status != null'>status = #{status}, </if>"
            + "<if test='send_type != null'>send_type = #{send_type}, </if>"
            + "<if test='message_type != null'>message_type = #{message_type}, </if>"
            + "<if test='user_id != null'>user_id = #{user_id}, </if>"
            + "</set>"
            + "WHERE id = #{id};"
            + "</script>")
    Integer update(MessageBean bean);


    /**
     * 查询消息,带分页
     * @param params
     * @return
     */
    @Select("<script>"
            + "select id,title,text,mail_receiver,(CASE WHEN `status` = 0 THEN '未读' WHEN `status` = 1 THEN '已读' END) as status,(CASE WHEN `send_type` = 1 THEN '规则引擎' WHEN `send_type` = 2 THEN '态势预警' END) as send_type,(CASE WHEN `message_type` = 1 THEN '站内消息' WHEN `message_type` = 2 THEN '邮件消息' WHEN `message_type` = 3 THEN '站内和邮件' END) as message_type,send_time,user_id from ga_messages"
            + "<where>"
            + "user_id = #{pa.user_id}"
            + "<if test='pa.status != null'> AND status = #{pa.status} </if>"
            + "<if test='pa.startDtm != null'> AND send_time &gt;= #{pa.startDtm} </if>"
            + "<if test='pa.endDtm != null'> AND send_time &lt;= #{pa.endDtm} </if>"
            + "<if test='pa.send_type != null'> AND send_type = #{pa.send_type} </if>"
            + "<if test='pa.message_type != null'> AND message_type = #{pa.message_type} </if>"
            + "</where>"
            + "</script>")
    Page<MessageBean> getAll(@Param("pa") Map<String, Object> params);

    /**
     * 获取未读消息记录数
     * @param status
     * @return
     */
    @Select("<script>"
            + "select count(*) from ga_messages"
            + "<where>"
            + "<if test='status != null'> AND status = #{status} </if>"
            + "</where>"
            + "</script>")
    Integer getUnreadAll(@Param("status") String status);
}
