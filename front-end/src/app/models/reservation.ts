import { Room } from "./room";
import { User } from "./user";

export class Reservation {
    public id: string;
    public room: Room;
    public userUuid: User;
    public isBooked: boolean;
    public checkIn: string;
    public checkOut: string;

    constructor(id: string, room: Room, userUuid: User, isBooked: boolean, checkIn: string, checkOut: string){ 
            this.id = id;
            this.room = room;
            this.userUuid = userUuid;
            this.isBooked = isBooked;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
}
}