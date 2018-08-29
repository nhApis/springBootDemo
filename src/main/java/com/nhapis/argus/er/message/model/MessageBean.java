package com.nhapis.argus.er.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ga_messages")
@Setter
@Getter
public class MessageBean implements Serializable {

    private Integer id;

    /**
     * 标题/邮件主题
     */
    private String title;

    private String text;
    private String mail_receiver;

    private String status;

    private String send_type;
    private String message_type;
    //jackjson(SpringBoot)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    //fastjson
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date send_time;
    private String user_id;

    @Id
    @GeneratedValue
    @JsonGetter
    private Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }
}