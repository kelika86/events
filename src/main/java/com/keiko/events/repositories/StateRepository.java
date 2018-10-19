package com.keiko.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keiko.events.models.State;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
	List<State> findAll();
	@Query("SELECT e.name, e.date, e.location, e.host, e.id, u.firstName FROM State s JOIN s.events e LEFT JOIN e.users u WHERE s.state=?1")
	List<Object[]> samestate(String state);
	@Query("SELECT e.name, e.date, e.location, s.state, e.host, e.id, u.firstName FROM State s JOIN s.events e LEFT JOIN e.users u WHERE s.state!=?1")
	List<Object[]> diffstate(String state);
}
