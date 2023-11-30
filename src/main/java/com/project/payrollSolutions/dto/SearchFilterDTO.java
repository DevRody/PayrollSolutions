package com.project.payrollSolutions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchFilterDTO<E> {
    private List<E> data;
    private Long count;
    private Integer totalPages;
}
