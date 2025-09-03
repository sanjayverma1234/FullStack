package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.Event;
import com.Group3tatastrive.VetPawtner.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public List<Event> GetAllEvents()
    {
        return eventRepository.findAll();
    }

    public Event insertEvent(@RequestBody Event event)
    {
        return eventRepository.save(event);
    }
    public Event modifyEvent(@PathVariable int id, @RequestBody Event event)
    {
        Event event1= eventRepository.findById(id).orElse(null);
        event1.setEvent_name(event.getEvent_name());
        event1.setDate(event.getDate());
        event1.setLocation(event.getLocation());
        return eventRepository.save(event1);
    }
    public Event deleteEvent(int id)
    {
        Event event1 = eventRepository.findById(id).orElse(null);
        if(event1 != null)
        {
            eventRepository.delete(event1);
        }
        return event1;
    }
}
