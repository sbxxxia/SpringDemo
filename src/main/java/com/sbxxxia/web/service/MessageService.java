package com.sbxxxia.web.service;

import com.sbxxxia.web.entity.Message;
import com.sbxxxia.web.repository.MessageRepository;
import com.sbxxxia.web.security.SecurityCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository repository;

    public MessageService(MessageRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Message> getMessage(){
        return repository.getMessage();
    }

    @SecurityCheck
    @Transactional(noRollbackFor = { UnsupportedOperationException.class})
    public Message save(String text){
        return repository.saveMessage(new Message(text));
    }
}
