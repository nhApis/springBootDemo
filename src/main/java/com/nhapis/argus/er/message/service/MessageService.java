package com.nhapis.argus.er.message.service;

import com.github.pagehelper.Page;
import com.nhapis.argus.er.message.model.MessageBean;

import java.util.List;
import java.util.Map;

public interface MessageService {

    Integer save(MessageBean message_model);

    Integer getUnreadAll(String status);

    Integer saveList(List<MessageBean> message_models);

    Integer update(MessageBean message_model);

    MessageBean get(Integer id);

    Page<MessageBean> getAll(Map<String, Object> params);

}