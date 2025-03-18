package com.example.libraraymangementsystemapi.entity.EmbeddedIds;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BorrowingId implements Serializable {
    private Long bookId;
    private Long borrowerId;
    private LocalDateTime checkoutDate;
    public BorrowingId(Long bookId,Long borrowerId){
        this.bookId=bookId;
        this.borrowerId=borrowerId;
        this.checkoutDate = LocalDateTime.now();
    }
}