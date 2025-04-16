package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.exception.GenericCustomException;
import com.example.repository.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Message saveMessage(Message msg) throws GenericCustomException {
        verifyTextLength(msg.getMessageText());
        if (accountRepository.findById(msg.getPostedBy()).isEmpty()) {
            throw new GenericCustomException("Invalid user");
        }
        return messageRepository.save(msg);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // I don't *think* selections should have to be transactional?
    public Optional<Message> getMessageById(int msgId) {
        return messageRepository.findById(msgId);
    }

    @Transactional
    public Integer deleteMessage(int msgId) throws GenericCustomException {
        int numberRowsDeleted = messageRepository.deleteByIdAndReturnCount(msgId);
        if (numberRowsDeleted > 0) {return (Integer)numberRowsDeleted;}
        else if (numberRowsDeleted == 0) {return null;}
        throw new GenericCustomException("Deletion error");
    }

    @Transactional
    public Integer patchMessage(int msgId, String msgTxt) throws GenericCustomException {
        verifyTextLength(msgTxt);
        if (messageRepository.findById(msgId).isEmpty()) {
            throw new GenericCustomException("Message not found");
        }
        int numberRowsPatched = messageRepository.patchByIdAndReturnCount(msgId, msgTxt);
        if (numberRowsPatched > 0) {return (Integer)numberRowsPatched;}
        else if (numberRowsPatched == 0) {return null;}
        throw new GenericCustomException("Patch error");
    }

    public List<Message> getMessagesByAccount(int acctId) {
        // don't even check for user, just return empty response with 200
        return messageRepository.findByPostedBy(acctId);
    }

    private void verifyTextLength(String msgTxt) throws GenericCustomException {
        if (msgTxt.isEmpty()) {
            throw new GenericCustomException("Message cannot be blank");
        } else if (msgTxt.length() > 255) {
            throw new GenericCustomException("Message cannot exceed 255 characters");
        }
    }

}