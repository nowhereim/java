package com.example.simpleboard.crud;

public interface Converter <DTO,ENTITY>{
    DTO toDto(ENTITY entity); //엔티티를 받아 디티오를 반환함.

    ENTITY toEntity(DTO dto); //디티오를 받아 엔티티를 반환함.
}
