package com.example.JpaMigrationLab.book.service;

import com.example.JpaMigrationLab.book.entity.BookEntity;
import com.example.JpaMigrationLab.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor //이걸 끄면 아래에 직접 생성자함수로 bookRepository를 주입해야함.
public class BookService {


//    @Autowired 이건 적용해도 에러남.
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);

    }

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    };

}
