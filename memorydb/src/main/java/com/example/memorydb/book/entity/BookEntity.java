package com.example.memorydb.book.entity;

import com.example.memorydb.entity.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity extends Entity {
    private String title;
    private int price;
}
