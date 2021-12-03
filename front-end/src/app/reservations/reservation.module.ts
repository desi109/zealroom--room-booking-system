import { Time } from "@angular/common";
import { Room } from "../book-room/room.module";

export class Reservation {
    public id: number;
    public room: Room;
    public date: Date;
    public startTime: string;
    public endTime: string;
    
    constructor(id: number, room: Room, date: Date, startTime: string, endTime: string) {
      this.id = id;
      this.room = room;
      this.date = date;
      this.startTime = startTime;
      this.endTime = endTime;

    }
  }