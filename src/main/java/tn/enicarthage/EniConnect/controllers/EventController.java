package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Admin;
import tn.enicarthage.EniConnect.entities.Event;
import tn.enicarthage.EniConnect.services.AdminServiceImp;
import tn.enicarthage.EniConnect.services.EventServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventServiceImp eventServiceImp;
    private final AdminServiceImp adminServiceImp;

    @Autowired
    public EventController(EventServiceImp eventServiceImp, AdminServiceImp adminServiceImp) {
        this.eventServiceImp = eventServiceImp;
        this.adminServiceImp = adminServiceImp;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventServiceImp.getAllEvents();
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        try {
            Event event = eventServiceImp.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Event with ID " + eventId + " not found."));
            return ResponseEntity.ok(event);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/{adminId}/event/create")
    public ResponseEntity<Event> createEvent(@PathVariable Long adminId, @RequestBody Event event) {
        try {
            Optional<Admin> adminOptional = Optional.ofNullable(adminServiceImp.getAdminById(adminId));
            if (adminOptional.isPresent()) {
                event.setAdmin(adminOptional.get());
                Event createdEvent = eventServiceImp.createEvent(event);
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
            eventServiceImp.deleteEventById(eventId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/{adminId}/event/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long adminId, @PathVariable Long eventId, @RequestBody Event updatedEvent) {
        try {
            Event updated = eventServiceImp.updateEventById(adminId, eventId, updatedEvent);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

