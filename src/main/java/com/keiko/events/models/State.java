package com.keiko.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State { //needs user and event models
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String state;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="state", fetch=FetchType.LAZY)
    private List<Event> events;
    
    @OneToMany(mappedBy="state", fetch=FetchType.LAZY)
    private List<User> users;
    
    public State() {
    	
    }
    
    
    
    
    public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public Date getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}




	public Date getUpdatedAt() {
		return updatedAt;
	}




	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}




	public List<Event> getEvents() {
		return events;
	}




	public void setEvents(List<Event> events) {
		this.events = events;
	}




	public List<User> getUsers() {
		return users;
	}




	public void setUsers(List<User> users) {
		this.users = users;
	}




	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
