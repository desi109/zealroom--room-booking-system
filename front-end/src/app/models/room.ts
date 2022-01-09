import { Equipment } from "../book-room/search-room/equipment-room.module";

export class Room {
    public id: string;
    public capacity: number;
    public roomDescription: string;
    public roomName: string;
  
    
    constructor(id: string, capacity: number, roomDescription: string, roomName: string) {
      this.id = id;
      this.capacity = capacity;
      this.roomDescription = roomDescription;
      this.roomName = roomName;
    }

    /*getEquipment(room: Room): String {
      let equipmentOutput = '';
      //let equipmentNumber = room.equipment.length;
      let counter = 1;
  
      room.equipment.forEach((equipment) => {
        if (counter == equipmentNumber) {
          equipmentOutput += equipment.name;
        } else {
          equipmentOutput += equipment.name + ', ';
          counter++;
        }
      });
  
      return equipmentOutput;
    }*/
  }