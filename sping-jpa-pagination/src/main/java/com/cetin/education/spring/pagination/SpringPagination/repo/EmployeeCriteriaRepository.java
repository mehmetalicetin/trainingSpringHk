package com.cetin.education.spring.pagination.SpringPagination.repo;

import com.cetin.education.spring.pagination.SpringPagination.model.Employee;
import com.cetin.education.spring.pagination.SpringPagination.page.EmployeePage;
import com.cetin.education.spring.pagination.SpringPagination.service.EmployeeSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeCriteriaRepository{
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EmployeeCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    public Page<Employee> findAllWithFilters(EmployeePage page, EmployeeSearchCriteria criteria){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate predicate = getPredicate(criteria, root);
        criteriaQuery.where(predicate);
        setOrder(page, criteriaQuery, root);

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page.getPageNumber()*page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        Pageable pageable = getPageable(page);

        long employessCount = getEmployessCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employessCount);
    }

    private long getEmployessCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> employeeRoot = countQuery.from(Employee.class);
        countQuery.select(criteriaBuilder.count(employeeRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(EmployeePage page) {
        Sort sort = Sort.by(page.getDirection(),page.getSortBy());
       return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

    private void setOrder(EmployeePage page,
                          CriteriaQuery<Employee> criteriaQuery,
                          Root<Employee> root) {
        if(page.getDirection().isAscending()){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(page.getSortBy())));
        } else{
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(page.getSortBy())));
        }

    }

    private Predicate getPredicate(EmployeeSearchCriteria criteria, Root<Employee> root) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(criteria.getFirstName())){
            predicates.add(
                    (Predicate) criteriaBuilder.like(root.get("firstName"),
                            "%"+criteria.getFirstName()+"%"));
        }
        if(Objects.nonNull(criteria.getLastName())){
            predicates.add(
                    (Predicate) criteriaBuilder.like(root.get("lastName"),
                            "%"+criteria.getLastName()+"%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
