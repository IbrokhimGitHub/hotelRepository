package uz.pdp.hotelrepository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelrepository.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findAllByHotel_Id(Integer hotelId, Pageable pageable);
}
