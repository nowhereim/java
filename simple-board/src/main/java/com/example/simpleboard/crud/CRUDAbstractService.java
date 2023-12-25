package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//dto를 받아 엔티티로 바꾸고 다시 dto로 변환 하는 역할임.
public abstract class CRUDAbstractService<DTO,ENTITY> implements  CRUDInterface<DTO> { //추상클래스이기때문에 빈으로 등록이 안됨. 그래서 @Service를 붙이면 에러가 남.

    @Autowired(required = false) //이 컨버터를 상속받은 빈이 있다면 상속받은 빈을 가져오고 없다면 에러를 내지 않고 null을 가져옴.
    private JpaRepository<ENTITY,Long> repository; //jpa를 사용하기 위해 repository를 가져옴.




    @Autowired(required = false) //이 컨버터를 상속받은 빈이 있다면 상속받은 빈을 가져오고 없다면 에러를 내지 않고 null을 가져옴.
    private Converter<DTO,ENTITY> converter; //만들어놓은 컨버터 인터페이스를 가져옴.


    @Override
    public DTO create(DTO dto) {

        //지금 흐름은 dto를 받아 엔티티로 바꿔 디비작업을 하고 내려줄때는 다시 dto로 바꿔서 내려주고 있어 이걸 추상화를 통해 통일시키는 것임.
        //DTO -> Entity
        ENTITY entity = converter.toEntity(dto); //converter를 통해 dto를 엔티티로 바꿈.
        //Entity -> save
        repository.save(entity); //repository를 통해 엔티티를 디비에 저장함.
        //save -> DTO
        DTO returnDto = converter.toDto(entity); //converter를 통해 엔티티를 다시 dto로 바꿈.


        return returnDto;
    }

    @Override
    public Optional<DTO> read(Long id) {

        // 이건 변환작업 없이 받은 값을 그대로 쿼리로 때림
        Optional<ENTITY> optionalEntity = repository.findById(id); //repository를 통해 id를 받아옴.

        // 받아온걸 역시 dto로 바꿔서 내려줘야함.
        DTO dto = optionalEntity.map(
                it -> converter.toDto(it) //entity를 dto로 바꿈.
        ).orElseThrow(null); //엔티티가 없다면? 널

        return Optional.ofNullable(dto); //이 메서드는 인자객체가 널일 수도 있다 라고 하는것. 위에는 그냥 반환 타입이고.
    }

    @Override
    public DTO update(DTO dto) {

        //DTO -> Entity
        var entity = converter.toEntity(dto);
        //Entity -> save
        var savedEntity = repository.save(entity);
        //save -> DTO
        var returnDto = converter.toDto(savedEntity);

        return returnDto;
    }

    @Override
    public void delete(Long id) {

        //얘는 반환값이 없음.
        repository.deleteById(id);

    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        //Page는 페이징처리를 위한 타입임. 페이저블을 findAll에 인자로 넣으면 반환될때 페이징처리가 된다. 페이지 데이터들이 반환된다.
        Page<ENTITY> list = repository.findAll(pageable); //페이징처리된 리스트를 가져옴.

        //페이지 정보를 이용해서 페이징처리를 위한 객체를 만들어줌.
        Pagination pagination  = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        //바디에 들어갈 엔티티 리스트를 dto로 바꿔줌.
        var dtoList = list.stream()
                .map(it -> converter.toDto(it))
                .collect(Collectors.toList());

        //페이지네이션에 들어갈 dto리스트와 페이징처리를 위한 객체를 넣어줌.
        var response = Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();

//이걸 이렇게 나눠 담아서 다시 만드는 이유는 클라이언트에서 편하게 쓰라고 하는거임
        //그냥 리스트 받은거 그대로 떄려박아 내려주면 멘붕옴.
        //위와같이 다시 가공 안해주면 밑에처럼 개판으로내려감
//        "pageable": {
//            "pageNumber": 0,
//                    "pageSize": 10,
//                    "sort": {
//                "empty": false,
//                        "sorted": true,
//                        "unsorted": false
//            },
//            "offset": 0,
//                    "unpaged": false,
//                    "paged": true
//        },
//        "last": false,
//                "totalElements": 27,
//                "totalPages": 3,
//                "size": 10,
//                "number": 0,
//                "sort": {
//            "empty": false,
//                    "sorted": true,
//                    "unsorted": false
//        },
//        "first": true,
//                "numberOfElements": 10,
//                "empty": false
//    }

        return response;
    }
}

