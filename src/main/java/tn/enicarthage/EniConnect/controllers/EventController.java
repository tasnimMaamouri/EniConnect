package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.entities.Event;
import tn.enicarthage.EniConnect.services.IAdminServiceImpl;
import tn.enicarthage.EniConnect.services.IEventServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final IEventServiceImpl IEventServiceImpl;
    private final IAdminServiceImpl IAdminServiceImpl;

    @Autowired
    public EventController(IEventServiceImpl IEventServiceImpl, IAdminServiceImpl IAdminServiceImpl) {
        this.IEventServiceImpl = IEventServiceImpl;
        this.IAdminServiceImpl = IAdminServiceImpl;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return IEventServiceImpl.getAllEvents();
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        try {
            Event event = IEventServiceImpl.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Event with ID " + eventId + " not found."));
            return ResponseEntity.ok(event);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/{adminId}/event/create")
    public ResponseEntity<Event> createEvent(@PathVariable Long adminId, @RequestBody Event event) {
        try {
            Optional<Admin> adminOptional = Optional.ofNullable(IAdminServiceImpl.getAdminById(adminId));
            if (adminOptional.isPresent()) {
                event.setAdmin(adminOptional.get());
                Event createdEvent = IEventServiceImpl.createEvent(event);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
            } else {
                throw new IllegalArgumentException("Admin with id " + adminId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long eventId) {
        try {
            IEventServiceImpl.deleteEventById(eventId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/{adminId}/event/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long adminId, @PathVariable Long eventId, @RequestBody Event updatedEvent) {
        try {
            Event updated = IEventServiceImpl.updateEventById(adminId, eventId, updatedEvent);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

