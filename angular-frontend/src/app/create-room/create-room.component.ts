import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.css']
})
export class CreateRoomComponent implements OnInit {

  room: Room = {} as Room;
  errorInfo!: string;

  constructor(private roomService: RoomService, private router: Router) { }

  ngOnInit() { }

  private save() {
    this.roomService.createRoom(this.room)
      .subscribe(data => {
        this.room = {} as Room;
        this.gotoList();
      }, error => {
        this.errorInfo = "Invalid filed(s)!";
      });
  }

  onSubmit() {
    this.save();
  }

  private gotoList() {
    this.router.navigate(['rooms']);
  }

}
