package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractController<DTO,ENTITY> implements CRUDInterface<DTO>{

    @Autowired(required = false)
    private CRUDAbstractService<DTO,ENTITY>  crudAbstractService; //crudAbstractService를 상속받은 빈을 가져옴.

    @PostMapping("")
    @Override
    public DTO create(
            @Valid
            @RequestBody
            DTO dto) {
        return crudAbstractService.create(dto);
    }


    @GetMapping("{id}")
    @Override
    public Optional<DTO> read(
            @PathVariable
            Long id
    ) {
        return crudAbstractService.read(id);
    }

    @PutMapping("")
    @Override
    public DTO update(
            @RequestBody
            DTO dto) {
        return crudAbstractService.update(dto);
    }

    @DeleteMapping("{id}")
    @Override
    public void delete(
            @PathVariable
            Long id) {

    }

    @GetMapping("/all")
    @Override
    public Api<List<DTO>> list(
            @PageableDefault
            Pageable pageable) {
        return crudAbstractService.list(pageable);
    }
}
