package com.example.javacodingjourney.patterns;

import java.util.*;

public class EventSystem {
    private List<EventListener> listeners = new ArrayList<>();

    // Inner interface for events
    public interface EventListener {
        void onEvent(Event event);
    }

    // Inner class for events
    public class Event {
        private String type;
        private Object data;
        private long timestamp;

        public Event(String type, Object data) {
            this.type = type;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }

        public String getType() { return type; }
        public Object getData() { return data; }

        // Can access outer class to re-dispatch
        public void redispatch() {
            EventSystem.this.fireEvent(this);
        }
    }

    // Static nested class for event builder
    public static class EventBuilder {
        private String type;
        private Object data;

        public EventBuilder type(String type) {
            this.type = type;
            return this;
        }

        public EventBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public Event build(EventSystem system) {
            return system.new Event(type, data);
        }
    }

    public void addEventListener(EventListener listener) {
        listeners.add(listener);
    }

    public void fireEvent(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    public static void main(String[] args) {
        EventSystem system = new EventSystem();

        // Anonymous inner class as listener
        system.addEventListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("Event received: " + event.getType());
            }
        });

        // Using lambda
        system.addEventListener(event ->
                System.out.println("Lambda listener: " + event.getData())
        );

        // Creating event with inner class
        Event event1 = system.new Event("click", "Button1");
        system.fireEvent(event1);

        // Creating event with static builder
        Event event2 = new EventBuilder()
                .type("hover")
                .data("Menu")
                .build(system);
        system.fireEvent(event2);
    }
}