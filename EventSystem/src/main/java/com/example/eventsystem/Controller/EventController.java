package com.example.eventsystem.Controller;

import com.example.eventsystem.ApiResponse.ApiResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/create-event")
    public ApiResponse createEvent(@RequestBody Event event) {
        events.add(event);

        return new ApiResponse("Event has been created successfully");
    }

    @GetMapping("/display-events")
    public ArrayList<Event> displayEvents() {
        return events;
    }

    @PutMapping("/update-event/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event) {
        events.set(index, event);

        return new ApiResponse("Event at index " + index + " has been updated successfully");
    }

    @DeleteMapping("/delete-event/{index}")
    public ApiResponse deleteEvent(@PathVariable int index) {
        events.remove(index);

        return new ApiResponse("Event at index " + index + " has been deleted successfully");
    }

    @PutMapping("/change-capacity/{index}")
    public ApiResponse changeCapacity (@PathVariable int index, @RequestBody int newCapacity) {
        events.get(index).setCapacity(events.get(index).getCapacity() - newCapacity);

        return new ApiResponse("Event capacity at index " + index + " has been changed successfully");
    }

    @GetMapping("/get-event/{id}")
    public Event getEventById(@PathVariable String id) {
        for (Event event : events)
            if (event.getId().equals(id))
                return event;

        return null;
    }
}
