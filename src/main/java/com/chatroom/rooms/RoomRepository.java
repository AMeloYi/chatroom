package com.chatroom.rooms;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface RoomRepository extends JpaRepository<Room, Long> {

	Optional<Room> findByRoomName(String roomName);
}
