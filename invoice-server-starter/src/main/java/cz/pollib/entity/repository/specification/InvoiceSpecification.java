package cz.pollib.entity.repository.specification;

import cz.pollib.entity.Invoice;
import cz.pollib.entity.Invoice_;
import cz.pollib.entity.Person;
import cz.pollib.entity.Person_;
import cz.pollib.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification for filtering {@link Invoice} instances.
 * <p>
 * This class constructs JPA criteria queries based on the provided {@link InvoiceFilter} to filter invoices
 * by various criteria including price range, seller, buyer, and product description.
 */
public class InvoiceSpecification implements Specification<Invoice> {

    private final InvoiceFilter invoiceFilter;

    public InvoiceSpecification(InvoiceFilter invoiceFilter) {
        this.invoiceFilter = invoiceFilter;
    }

    /**
     * Constructs the {@link Predicate} for the JPA criteria query based on the given filter criteria.
     *
     * @param root            The root entity for the query.
     * @param query           The criteria query.
     * @param criteriaBuilder The criteria builder used to construct predicates.
     * @return The combined {@link Predicate} based on the filter criteria.
     */
    @Override
    public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (invoiceFilter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Invoice_.PRICE), invoiceFilter.getMinPrice()));
        }

        if (invoiceFilter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Invoice_.PRICE), invoiceFilter.getMaxPrice()));
        }

        if (invoiceFilter.getSellerId() != null) {
            Join<Person, Invoice> sellerJoin = root.join(Invoice_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(Person_.ID), invoiceFilter.getSellerId()));
        }

        if (invoiceFilter.getBuyerId() != null) {
            Join<Person, Invoice> buyerJoin = root.join(Invoice_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(Person_.ID), invoiceFilter.getBuyerId()));
        }
        if (invoiceFilter.getProduct() != null) {
            predicates.add(criteriaBuilder.like(root.get(Invoice_.PRODUCT.toLowerCase()), "%" + invoiceFilter.getProduct().toLowerCase() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
