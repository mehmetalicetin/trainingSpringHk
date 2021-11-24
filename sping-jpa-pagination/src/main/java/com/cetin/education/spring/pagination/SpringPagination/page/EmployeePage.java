package com.cetin.education.spring.pagination.SpringPagination.page;

import org.springframework.data.domain.Sort;

public class EmployeePage {
    private int pageNumber = 0;
    private int pageSize   = 10;

    private Sort.Direction direction = Sort.Direction.ASC;
    private String sortBy = "lastName";

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
