package tn.enicarthage.EniConnect.services;

import jakarta.transaction.Transactional;
import tn.enicarthage.EniConnect.entities.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> getAllEvents();

    Optional<Event> getEventById(Long eventId);

    Event createEvent(Event event);

    void deleteEventById(Long eventId);

    @Transactional
    Event updateEventById(Long adminId, Long eventId, Event updatedEvent);
}
