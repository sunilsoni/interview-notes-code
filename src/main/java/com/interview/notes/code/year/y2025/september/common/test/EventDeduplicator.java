package com.interview.notes.code.year.y2025.september.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class EventDeduplicator {

    public static List<Event> filterUniqueEvents(Iterator<String> eventStream) throws IOException {
        Set<Event> uniqueEvents = new LinkedHashSet<>();
        ObjectMapper mapper = new ObjectMapper();

        while (eventStream.hasNext()) {
            String jsonEvent = eventStream.next();
            Event event = mapper.readValue(jsonEvent, Event.class);
            uniqueEvents.add(event);
        }

        return new ArrayList<>(uniqueEvents);
    }

    public static void main(String[] args) throws IOException {
        // Simulating a stream of JSON events
        List<String> jsonEvents = Arrays.asList(
                "{\"id\":\"1\",\"timestamp\":\"2023-01-01T10:00:00\",\"description\":\"Event 1\"}",
                "{\"id\":\"2\",\"timestamp\":\"2023-01-01T10:01:00\",\"description\":\"Event 2\"}",
                "{\"id\":\"1\",\"timestamp\":\"2023-01-01T10:02:00\",\"description\":\"Duplicate Event 1\"}"
        );

        List<Event> uniqueEvents = filterUniqueEvents(jsonEvents.iterator());

        for (Event event : uniqueEvents) {
            System.out.println(event);
        }
    }
}

class Event {
    private String id;
    private Date timestamp;
    private String description;

    // Getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}