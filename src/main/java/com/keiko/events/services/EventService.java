package com.keiko.events.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.keiko.events.models.Event;
import com.keiko.events.models.State;
import com.keiko.events.models.User;
import com.keiko.events.repositories.EventRepository;
import com.keiko.events.repositories.StateRepository;
import com.keiko.events.repositories.UserRepository;

@Service
public class EventService {
	private final UserRepository userRepository;
	private final EventRepository eventRepository;
	private final StateRepository stateRepository;
	
    public EventService(UserRepository userRepository, EventRepository eventRepository, StateRepository stateRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.stateRepository = stateRepository;
    }
    
    public List<State> allState() {
    	return (List<State>) stateRepository.findAll();
    }
    public List<User> allUser() {
    	return (List<User>) userRepository.findAll();
    }
    public List<Event> allEvent() {
    	return (List<Event>) eventRepository.findAll();
    }
    public Event createEvent(Event event) {
    	return eventRepository.save(event);
    }
    public Event findEvent(Long id) {
    	Optional<Event> optionalEvent = eventRepository.findById(id);
    	if(optionalEvent.isPresent()) {
    		return optionalEvent.get();
    	} else {
    		return null;
    	}
    }
    public Event updateEvent(Event event) {
    	return eventRepository.save(event);
    }
    public Event updateEvent(Long id, String name, String location, Date date, String Location, State state) {
    	Event updateEvent = eventRepository.findById(id).get();
    	updateEvent.setName(name);
    	updateEvent.setDate(date);
    	updateEvent.setLocation(location);
    	updateEvent.setState(state);
    	return eventRepository.save(updateEvent);
    }
    public void deleteEvent(Long id) {
    	eventRepository.deleteById(id);
    }
    public List<Object[]> inMyState(String state) {
    	return stateRepository.samestate(state);
    }
    public List<Object[]> NotMyState(String state) {
    	return stateRepository.diffstate(state);
    }
}
