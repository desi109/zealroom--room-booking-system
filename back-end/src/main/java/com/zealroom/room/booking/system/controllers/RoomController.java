package com.zealroom.room.booking.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.web.bind.annotation.*;

import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.repositories.RoomRepository;
import com.zealroom.room.booking.system.repositories.BookingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "localhost:4200")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public Room add(@RequestBody Room room){
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        roomRepository.deleteById(id.toString());
    }

    @PostMapping("/book/room")
    public void bookExactRoom(@RequestBody Booking booking) {
        //Check if there is any booking record for some room and time period. If this list is empty this room is available for that period
        //if it is not empty it is already booked
        List<Booking> bookings = bookingRepository.findAllByRoomAndCheckInCheckOut(booking.getRoom(),
                booking.getCheckIn(), booking.getCheckOut());
        
        if (bookings == null) {
            bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("This room is already booked for this time period");
        }
    }

    @GetMapping("/date/{checkIn}/{checkOut}")
    public List<Room> date(@PathVariable LocalDateTime checkIn, @PathVariable LocalDateTime checkOut) {
        return bookingRepository.findByCheckInAndCheckOut(checkIn, checkOut);
    }

    @GetMapping("/capacity/{cap}")
    public List<Room> capacity(@PathVariable int cap) {
        return roomRepository.findByCapacityGreaterThanEqual(cap);
    }

    @GetMapping("/number/{roomNum}")
    public List<Room> roomNumber(@PathVariable String roomNum) {
        return roomRepository.findByRoomNumber(roomNum);
    }

    @GetMapping("/description/{desc}")
    public Set<Room> FindRoomByDescription(@PathVariable List<String> desc){
        Set<Room> rooms = new HashSet<>();
        for (String description: desc) {
            rooms.addAll(roomRepository.findByRoomDescriptionIsContaining(description));
        }

        return rooms;
    }

    /*@GetMapping("/search")
    public List<Room> getRoomsBySearch(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName) {

        List<Criteria> andCriteriaList = new ArrayList<Criteria>();
        boolean ok = false;

        if (firstName != null && firstName.length() > 0) {
            Criteria c1 = Criteria.where("firstName").regex(firstName, "i");
            andCriteriaList.add(c1);
            ok = true;
        }
        if (lastName != null && lastName.length() > 0) {
            Criteria c1 = Criteria.where("lastName").regex(lastName, "i");
            andCriteriaList.add(c1);
            ok = true;
        }

        if (ok) {
            query.addCriteria(new Criteria().andOperator(andCriteriaList
                    .toArray(new Criteria[andCriteriaList.size()])));

            return mongoTemplate.find(query, Employee.class,
                    COLLECTION_NAME);
        } else {
            return null;
        }
    }

    public List<Employee> getEmployeesBySelectionCriteria(
            List<SelectionCriteria> criteriaList) {

        List<Criteria> andCriteriaList = new ArrayList<Criteria>();

        Query query = new Query();

        for (SelectionCriteria criteriaElem : criteriaList) {
            if (criteriaElem.getOperator().getId().equals("equalTo")) {
                Criteria c1 = Criteria.where(criteriaElem.getField().getId())
                        .is(criteriaElem.getValue());
                andCriteriaList.add(c1);
            } else if (criteriaElem.getOperator().getId().equals("like")) {
                Criteria c1 = Criteria.where(criteriaElem.getField().getId())
                        .regex(criteriaElem.getValue(), "i");
                andCriteriaList.add(c1);
            } else if (criteriaElem.getOperator().getId()
                    .equals("notEqualTo")) {
                Criteria c1 = Criteria.where(criteriaElem.getField().getId())
                        .ne(criteriaElem.getValue());
                andCriteriaList.add(c1);
            } else if (criteriaElem.getOperator().getId()
                    .equals("greaterThan")) {
                Criteria c1 = Criteria.where(criteriaElem.getField().getId())
                        .gt(DateUtility.getDate(criteriaElem.getValue()));
                andCriteriaList.add(c1);
            } else if (criteriaElem.getOperator().getId()
                    .equals("lessThan")) {
                Criteria c1 = Criteria.where(criteriaElem.getField().getId())
                        .lt(DateUtility.getDate(criteriaElem.getValue()));
                andCriteriaList.add(c1);
            }
            logger.info(criteriaElem.toString());
        }
        query.addCriteria(new Criteria().andOperator(andCriteriaList
                .toArray(new Criteria[andCriteriaList.size()])));

        return mongoTemplate.find(query, Employee.class, COLLECTION_NAME);
    }*/
}
