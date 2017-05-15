package com.chatroom.messages.chatmessage.publicmessage;

import org.springframework.data.jpa.repository.JpaRepository;

interface PublicMessageRepository extends JpaRepository<PublicMessage, Long> {

}
