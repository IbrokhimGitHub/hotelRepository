package uz.pdp.hotelrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelrepository.entity.Hotel;
import uz.pdp.hotelrepository.entity.Room;
import uz.pdp.hotelrepository.model.RoomDto;
import uz.pdp.hotelrepository.repository.HotelRepository;
import uz.pdp.hotelrepository.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @PostMapping
    public String add(@RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "can't find hotel with id: "+roomDto.getHotelId();
        }
        Hotel hotel = optionalHotel.get();
        Room room=new Room();
        room.setNumber(room.getNumber());
        room.setFloor(room.getFloor());
        room.setSize(room.getSize());
        room.setHotel(hotel);
        roomRepository.save(room);
        return "new room added";
    }
    @GetMapping
    public Page<Room> getAll(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> rooms = roomRepository.findAll(pageable);
        return rooms;
    }
    @GetMapping("findByHotel/{id}")
    public Page<Room> getHotelRooms(@RequestParam int page,@PathVariable Integer id){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> rooms = roomRepository.findAllByHotel_Id(id,pageable);
        return rooms;
    }
    @DeleteMapping("deleteRoom/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.deleteById(id);
            return "room deleted";
        }
        return "cant delete room";
    }
    @PutMapping("edit/{id}")
    public String edit(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) {
            return "cant find such room";
        }
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "can't find hotel with id: "+roomDto.getHotelId();
        }
        Hotel hotel = optionalHotel.get();
        Room room= optionalRoom.get();
        room.setNumber(room.getNumber());
        room.setFloor(room.getFloor());
        room.setSize(room.getSize());
        room.setHotel(hotel);
        roomRepository.save(room);
        return "room with id: "+room.getId()+" is deleted";
    }


}
