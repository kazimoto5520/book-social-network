package com.mesha.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("""
        SELECT book
        FROM Book book
        WHERE book.archived = false 
        AND book.shareable = true 
        AND book.owner.id != :userId
    """)
    Page<Book> findAllDispayableBooks(Pageable pageable, Integer userId);
}
