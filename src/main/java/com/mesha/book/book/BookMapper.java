package com.mesha.book.book;

import com.mesha.book.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().fullName())
                .authorName(book.getAuthorName())
                .synopsis(book.getSynopsis())
                .rate(book.getRates())
                //TODO implement later
//                .cover(null)

                .build();
    }

    public BorrowedBookResponse toBorrowedBooResponse(BookTransactionHistory history) {
        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .isbn(history.getBook().getIsbn())
                .authorName(history.getBook().getAuthorName())
                .rate(history.getBook().getRates())
                .returnApproved(history.isReturnApproved())
                .returned(history.isReturned())
                .build();
    }
}
