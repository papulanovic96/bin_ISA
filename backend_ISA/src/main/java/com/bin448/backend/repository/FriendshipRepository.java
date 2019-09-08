package com.bin448.backend.repository;

import com.bin448.backend.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Modifying
    @Query(value = "UPDATE isa.friendship SET are_friends = '1' WHERE (sender_username = ?2) and (receiver_username = ?1)", nativeQuery = true)
    void modifyIT(String receiver, String sender);
}
