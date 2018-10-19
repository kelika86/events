package com.keiko.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


	@Entity
	@Table(name="users")
	public class User {
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	    @Size(min=1, message="First Name cannot be empty")
	    private String firstName;
	    @Size(min=1, message="Last Name cannot be empty")
	    private String lastName;
	    @Email(message="Email must be valid")
	    private String email;
	    @Size(min=5, message="Password must be greater than 5 characters")
	    private String password;
	    private String location;
	    @Transient
	    private String passwordConfirmation;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="state_id")
	    private State state;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "users_events", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "event_id")
	    ) //event model
	    
	    private List<Event> events;
	    @OneToMany(mappedBy="message", fetch=FetchType.LAZY)
	    private List<Message> messages;
	    
	    public User() {
	    }
	    
	    
	    
	    
	    // other getters and setters removed for brevity
	    public Long getId() {
			return id;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public String getFirstName() {
			return firstName;
		}




		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}




		public String getLastName() {
			return lastName;
		}




		public void setLastName(String lastName) {
			this.lastName = lastName;
		}




		public String getEmail() {
			return email;
		}




		public void setEmail(String email) {
			this.email = email;
		}




		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}




		public String getLocation() {
			return location;
		}




		public void setLocation(String location) {
			this.location = location;
		}




		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}




		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
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




		public State getState() {
			return state;
		}




		public void setState(State state) {
			this.state = state;
		}




		public List<Event> getEvents() {
			return events;
		}




		public void setEvents(List<Event> events) {
			this.events = events;
		}




		public List<Message> getMessages() {
			return messages;
		}




		public void setMessages(List<Message> messages) {
			this.messages = messages;
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
