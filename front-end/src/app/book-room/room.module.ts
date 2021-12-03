import { Equipment } from "./search-room/equipment-room.module";

export class Room {
    public id: number;
    public name: string;
    public capacity: number;
    public equipment: Equipment [];
  
    
    constructor(id: number, name: string, capacity: number, equipment: Equipment []) {
      this.id = id;
      this.name = name;
      this.capacity = capacity;
      this.equipment = equipment;
    }

    getEquipment(room: Room): String {
      let equipmentOutput = '';
      let equipmentNumber = room.equipment.length;
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
    }
  }