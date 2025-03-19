package com.example.libraraymangementsystemapi.Mappers;

import com.example.libraraymangementsystemapi.dto.response.BorrowingResponse;
import com.example.libraraymangementsystemapi.entity.Borrowing;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper {
    public BorrowingResponse sourceToDestination(Borrowing borrowing){
        return new BorrowingResponse(
                borrowing.getId().getBorrowerId(),
                borrowing.getId().getBookId(),
                borrowing.getId().getCheckoutDate(),
                borrowing.getDueDate(),
                borrowing.getReturnDate()
        );
    }

}
