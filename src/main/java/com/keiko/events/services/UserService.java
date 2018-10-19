package com.keiko.events.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.keiko.events.models.State;
import com.keiko.events.models.User;
import com.keiko.events.repositories.StateRepository;
import com.keiko.events.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
    private final StateRepository stateRepository;
    
    public UserService(UserRepository userRepository, StateRepository stateRepository) {
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
    }
    
    public List<State> allState(){
    	return (List<State>) stateRepository.findAll();
    }
    
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
