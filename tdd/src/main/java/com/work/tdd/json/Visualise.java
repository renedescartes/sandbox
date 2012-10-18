package com.work.tdd.json;

import com.google.gson.Gson;

import java.util.Arrays;

class EventType {
    private Event eventType;
    private Long cardinality;

    EventType(Event e, Long cardinality) {
        this.eventType = e;
        this.cardinality = cardinality;
    }
}


class Event {
    private Long id;
    private String eventType;

    Event(Long id, String eventType) {
        this.id = id;
        this.eventType = eventType;
    }
}

public class Visualise {

    public static void main(String[] args) {
        Gson gson = new Gson();
        EventType e1 = new EventType(new Event(55L, "Football"), 25L);
        EventType e2 = new EventType(new Event(56L, "Baseball"), 25L);
        System.out.println(gson.toJson(Arrays.asList(e1, e2)));


    }
}
