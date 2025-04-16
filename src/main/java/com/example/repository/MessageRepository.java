package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Message save(Message message);
    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :msg_id") //use entity name, not table name!
    public int deleteByIdAndReturnCount(@Param("msg_id") int id);

    @Modifying
    @Query("UPDATE Message m SET messageText = :msg_txt WHERE messageId = :msg_id") //use entity name, not table name!
    public int patchByIdAndReturnCount(
        @Param("msg_id") int id,
        @Param("msg_txt") String text
    );

    // @Modifying
    // @Query("SELECT * FROM Message m WHERE m.postedBy = :posted_by")
    // public List<Message> findAllMessagesByPostedBy(@Param("posted_by") int posted_by);
    public List<Message> findByPostedBy(int posted_by);
}