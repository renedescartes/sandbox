package com.work.tdd.json;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class EventType {
    private Long id;
    private String eventType;
    private Long cardinality;

    EventType(Long id, String eventType, Long cardinality) {
        this.id = id;
        this.eventType = eventType;
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
        EventType e1 = new EventType(55L, "Football", 25L);
        EventType e2 = new EventType(56L, "Baseball", 45L);
        System.out.println(gson.toJson(Arrays.asList(e1, e2)));


        Map<Event, Long> map = new HashMap<>();
        map.put(new Event(55L, "Football"), 25L);
        map.put(new Event(56L, "Baseball"), 45L);
        System.out.println(gson.toJson(map));

    }
}
