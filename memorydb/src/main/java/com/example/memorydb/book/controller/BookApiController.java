package com.example.memorydb.book.controller;

import com.example.memorydb.book.entity.BookEntity;
import com.example.memorydb.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor //이것의 역할은 생성자함수를 만들어줌. final이 붙은 필드를 가지고 생성자함수를 만들어줌.
public class BookApiController {

    private final BookService bookService;
    //리콰이어 어노테이션을 쓰지 않으면 이렇게 직접 해주면 된다.
//    public BookApiController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @PutMapping
    public BookEntity save(
           @RequestBody //이걸 빼먹으니 널이나오지.
            BookEntity bookEntity) {

        System.out.println("book api controller");
        System.out.println(bookEntity);
        return bookService.save(bookEntity);

    }


    @GetMapping
    public List<BookEntity> findAll(){
        return bookService.findAll();
    };
}
