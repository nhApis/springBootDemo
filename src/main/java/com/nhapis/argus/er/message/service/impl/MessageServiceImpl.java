package com.nhapis.argus.er.message.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nhapis.argus.er.message.model.MessageBean;
import com.nhapis.argus.er.message.repository.MessageRepository;
import com.nhapis.argus.er.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    protected MessageRepository messageModelRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Integer save(MessageBean message_model) {

        return messageModelRepository.save(message_model);
    }

    @Override
    public Integer getUnreadAll(String status) {
        return messageModelRepository.getUnreadAll(status);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Integer saveList(List<MessageBean> message_models) {

        return messageModelRepository.saveList(message_models);
    }

    @Override
    public MessageBean get(Integer id) {
        return null;
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
//    public void delete(Integer id) {
//        message_modelRepository.delete(id);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Integer update(MessageBean message_model) {
        return messageModelRepository.update(message_model);
    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
//    public MessageBean get(Integer id) {
//        return messageModelRepository.findOne(id);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Page<MessageBean> getAll(Map<String, Object> params) {

        Integer page = (Integer) params.get("pageNum");
        Integer rows = (Integer) params.get("pageSize");

        PageHelper.startPage(page - 1, rows);
        return messageModelRepository.getAll(params);
//        return message_modelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//
//            if (!StringUtils.isEmpty(status)) {
//                criteriaQuery.where(criteriaBuilder.equal(root.get("status"), status));
//            }
//            return null;
//        }, pageable);
    }
}