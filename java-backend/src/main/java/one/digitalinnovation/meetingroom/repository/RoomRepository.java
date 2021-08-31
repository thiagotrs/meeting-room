package one.digitalinnovation.meetingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.meetingroom.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
