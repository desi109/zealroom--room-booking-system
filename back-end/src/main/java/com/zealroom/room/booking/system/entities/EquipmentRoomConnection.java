package com.zealroom.room.booking.system.entities;

import javax.persistence.*;

@Entity
@Table(name = "equipmentroomconnections")
public class EquipmentRoomConnection {

        @Id
        @GeneratedValue
        @Column(unique = true,name = "connection_id")
        private Long id;


        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "room_uuid")
        private Room room;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "equipment_id")
        private Equipment equipment;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "organization_uuid")
        private Organization organization;

        public EquipmentRoomConnection() {

        }

        public EquipmentRoomConnection(Room room, Equipment equipment) {
            this.room = room;
            this.equipment = equipment;
        }

        public long getId() {
            return id;
        }
}
