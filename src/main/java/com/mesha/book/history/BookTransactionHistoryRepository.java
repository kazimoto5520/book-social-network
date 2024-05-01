package com.mesha.book.history;

import com.mesha.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.user.id = :userId
    """)
    Page<BookTransactionHistory> findAllByBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.book.owner.id = :userId
    """)
    Page<BookTransactionHistory> findAllByReturnedBooks(Pageable pageable, Integer userId);

    @Query("""
        SELECT 
        (COUNT(*) > 0) AS isBorrowed
        FROM BookTransactionHistory history
        WHERE history.user.id = :userId
        AND history.book.id = :bookId
        AND history.returnApproved = false
    """)
    boolean isAlradyBorrowByUser(Integer bookId, Integer id);

    @Query("""
        SELECT transaction
        FROM BookTransactionHistory transaction
        WHERE transaction.user.id = :userId
        AND transaction.book.id = :bookId
        AND transaction.returned = false 
        AND transaction.returnApproved = false 
    """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId, Integer userId);
}
