package one.digitalinnovation.meetingroom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.meetingroom.dto.RoomDTO;
import one.digitalinnovation.meetingroom.exception.RoomNotFoundException;
import one.digitalinnovation.meetingroom.service.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    
    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable long id) throws RoomNotFoundException {
        return roomService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createRoom(@RequestBody @Valid RoomDTO dto) {
        roomService.create(dto);
    }

    @PutMapping("/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody @Valid RoomDTO dto)
            throws RoomNotFoundException {
        roomService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id) throws RoomNotFoundException {
        roomService.delete(id);
    }
}
