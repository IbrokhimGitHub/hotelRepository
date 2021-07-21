package uz.pdp.hotelrepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelrepository.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
