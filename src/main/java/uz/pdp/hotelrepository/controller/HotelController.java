package uz.pdp.hotelrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelrepository.entity.Hotel;
import uz.pdp.hotelrepository.exception.NoHotelException;
import uz.pdp.hotelrepository.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @PostMapping
    public String add(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Hotel saved";
    }
    @GetMapping
    public List<Hotel> get(){
        return hotelRepository.findAll();
    }
    @GetMapping("/hotelById/{id}")
    public Hotel getById(@PathVariable Integer id) throws NoHotelException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
           throw new NoHotelException();
        }

        return optionalHotel.get();
    }
    @DeleteMapping("deleteId/{id}")
    public String delete(@PathVariable Integer id) throws NoHotelException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            throw new NoHotelException();
        }
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody Hotel hotelToEdit) throws NoHotelException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            throw new NoHotelException();
        }
        Hotel hotel = optionalHotel.get();
        hotel.setName(hotelToEdit.getName());
        hotelRepository.save(hotel);
        return "Hotel with id: "+id+" edited";
    }

}
