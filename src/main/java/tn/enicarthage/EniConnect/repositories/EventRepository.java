package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.EniConnect.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}

