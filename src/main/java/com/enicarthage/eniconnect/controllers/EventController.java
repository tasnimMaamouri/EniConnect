package com.enicarthage.eniconnect.controllers;

import com.enicarthage.eniconnect.models.Admin;
import com.enicarthage.eniconnect.models.Event;
import com.enicarthage.eniconnect.services.EventService;
import com.enicarthage.eniconnect.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;
    private final AdminService adminService;

    @Autowired
    public EventController(EventService eventService, AdminService adminService) {
        this.eventService = eventService;
        this.adminService = adminService;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        try {
            Event event = eventService.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Event with ID " + eventId + " not found."));
            return ResponseEntity.ok(event);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/{adminId}/event/create")
    public ResponseEntity<Event> createEvent(@PathVariable Long adminId, @RequestBody Event event) {
        try {
            Optional<Admin> adminOptional = Optional.ofNullable(adminService.getAdminById(adminId));
            if (adminOptional.isPresent()) {
                event.setAdmin(adminOptional.get());
                Event createdEvent = eventService.createEvent(event);
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
            eventService.deleteEventById(eventId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/{adminId}/event/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long adminId, @PathVariable Long eventId, @RequestBody Event updatedEvent) {
        try {
            Event updated = eventService.updateEventById(adminId, eventId, updatedEvent);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
