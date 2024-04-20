package com.enicarthage.eniconnect.services;

import com.enicarthage.eniconnect.models.Admin;
import com.enicarthage.eniconnect.models.Event;
import com.enicarthage.eniconnect.repositories.AdminRepository;
import com.enicarthage.eniconnect.repositories.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public EventService(EventRepository eventRepository, AdminRepository adminRepository) {
        this.eventRepository = eventRepository;
        this.adminRepository = adminRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    @Transactional
    public Event updateEventById(Long adminId, Long eventId, Event updatedEvent) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isPresent()) {

            Optional<Event> optionalEvent = eventRepository.findById(eventId);

            if (optionalEvent.isPresent()) {
                Event existingEvent = optionalEvent.get();

                if (updatedEvent.getTitle() != null && !updatedEvent.getTitle().isEmpty() && !Objects.equals(updatedEvent.getTitle(), existingEvent.getTitle())) {
                    existingEvent.setTitle(updatedEvent.getTitle());
                }
                if (updatedEvent.getLocation() != null && !updatedEvent.getLocation().isEmpty() && !Objects.equals(updatedEvent.getLocation(), existingEvent.getLocation())) {
                    existingEvent.setLocation(updatedEvent.getLocation());
                }
                if (updatedEvent.getStatus() != null && !Objects.equals(updatedEvent.getStatus(), existingEvent.getStatus())) {
                    existingEvent.setStatus(updatedEvent.getStatus());
                }

                // Save the updated event back to the repository
                return eventRepository.save(existingEvent);
            } else {
                throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
            }
        } else {
            throw new IllegalArgumentException("Admin with ID " + adminId + " not found.");
        }
    }
}
