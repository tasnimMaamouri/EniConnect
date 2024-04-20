package com.enicarthage.eniconnect.repositories;

import com.enicarthage.eniconnect.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
