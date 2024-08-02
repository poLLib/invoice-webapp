package cz.pollib.entity.repository.specification;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.InvoiceEntity_;
import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.PersonEntity_;
import cz.pollib.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification for filtering {@link InvoiceEntity} instances.
 * <p>
 * This class constructs JPA criteria queries based on the provided {@link InvoiceFilter} to filter invoices
 * by various criteria including price range, seller, buyer, and product description.
 */
@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {

    private final InvoiceFilter invoiceFilter;

    /**
     * Constructs the {@link Predicate} for the JPA criteria query based on the given filter criteria.
     *
     * @param root            The root entity for the query.
     * @param query           The criteria query.
     * @param criteriaBuilder The criteria builder used to construct predicates.
     * @return The combined {@link Predicate} based on the filter criteria.
     */
    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (invoiceFilter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(InvoiceEntity_.PRICE), invoiceFilter.getMinPrice()));
        }

        if (invoiceFilter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(InvoiceEntity_.PRICE), invoiceFilter.getMaxPrice()));
        }

        if (invoiceFilter.getSellerId() != null) {
            Join<PersonEntity, InvoiceEntity> sellerJoin = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), invoiceFilter.getSellerId()));
        }

        if (invoiceFilter.getBuyerId() != null) {
            Join<PersonEntity, InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), invoiceFilter.getBuyerId()));
        }
        if (invoiceFilter.getProduct() != null) {
            predicates.add(criteriaBuilder.like(root.get(InvoiceEntity_.PRODUCT.toLowerCase()), "%" + invoiceFilter.getProduct().toLowerCase() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
