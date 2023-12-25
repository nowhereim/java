package com.example.simpleboard.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

    private Integer page;

    private Integer size;

    private Integer totalPage;

    private Long totalElements;

    private Integer currentElements;
}
