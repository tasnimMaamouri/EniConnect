package tn.enicarthage.EniConnect.services;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.entities.Event;
import tn.enicarthage.EniConnect.repositories.AdminRepository;
import tn.enicarthage.EniConnect.repositories.EventRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IEventServiceImpl implements IEventService{
    private final EventRepository eventRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public IEventServiceImpl(EventRepository eventRepository, AdminRepository adminRepository) {
        this.eventRepository = eventRepository;
        this.adminRepository = adminRepository;
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    @Override
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

