package com.keiko.events.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keiko.events.models.Message;
import com.keiko.events.models.User;
import com.keiko.events.repositories.MessageRepository;
import com.keiko.events.repositories.UserRepository;

@Service
public class MessageService {
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	
	public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}
	
	public List<User> allUser() {
    	return (List<User>) userRepository.findAll();
    }
    public List<Message> allMessage() {
    	return (List<Message>) messageRepository.findAll();
    }
    public Message createMessage(Message message) {
    	return messageRepository.save(message);
    }
}
