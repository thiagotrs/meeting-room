package one.digitalinnovation.meetingroom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.digitalinnovation.meetingroom.dto.RoomDTO;
import one.digitalinnovation.meetingroom.entity.Room;
import one.digitalinnovation.meetingroom.exception.RoomNotFoundException;
import one.digitalinnovation.meetingroom.repository.RoomRepository;

@Service
public class RoomService {
    
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> findAll() {
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream().map(room -> mapToDTO(room)).collect(Collectors.toList());
    }

    public RoomDTO findById(Long id) throws RoomNotFoundException {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RoomNotFoundException(id));

        return mapToDTO(room);
    }

    public void delete(Long id) throws RoomNotFoundException {
        roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
        roomRepository.deleteById(id);
    }

    public void create(RoomDTO dto) {      
        roomRepository.save(mapToEntity(dto));
    }

    public void update(RoomDTO dto, Long id) throws RoomNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));

        room.setName(dto.getName());
        room.setDate(dto.getDate());
        room.setStartHour(dto.getStartHour());
        room.setEndHour(dto.getEndHour());

        roomRepository.save(room);
    }

    private Room mapToEntity(RoomDTO dto) {
        return Room
            .builder()
            .name(dto.getName())
            .date(dto.getDate())
            .startHour(dto.getStartHour())
            .endHour(dto.getEndHour())
            .build();
    }

    private RoomDTO mapToDTO(Room room) {
        return RoomDTO
            .builder()
            .id(room.getId())
            .name(room.getName())
            .date(room.getDate())
            .startHour(room.getStartHour())
            .endHour(room.getEndHour())
            .build();
    }
}

