package cz.pollib.entity.repository.specification;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.InvoiceEntity_;
import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.PersonEntity_;
import cz.pollib.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification for filtering {@link InvoiceEntity} instances.
 * <p>
 * This class constructs JPA criteria queries based on the provided {@link InvoiceFilter} to filter invoices
 * by various criteria including price range, seller, buyer, and product description.
 */
public record InvoiceSpecification(InvoiceFilter invoiceFilter) implements Specification<InvoiceEntity> {

    /**
     * Constructs the {@link Predicate} for the JPA criteria query based on the given filter criteria.
     *
     * @param root            The root entity for the query.
     * @param query           The criteria query.
     * @param criteriaBuilder The criteria builder used to construct predicates.
     * @return The combined {@link Predicate} based on the filter criteria.
     */
    @Override
    public Predicate toPredicate(
            @NonNull Root<InvoiceEntity> root,
            CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder
                                ) {
        List<Predicate> predicates = new ArrayList<>();

        if (invoiceFilter.minPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get(InvoiceEntity_.PRICE),
                    invoiceFilter.minPrice()
                                                               ));
        }

        if (invoiceFilter.maxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get(InvoiceEntity_.PRICE),
                    invoiceFilter.maxPrice()
                                                            ));
        }

        if (invoiceFilter.sellerId() != null) {
            Join<PersonEntity, InvoiceEntity> seller = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(
                    seller.get(PersonEntity_.ID),
                    invoiceFilter.sellerId()
                                                ));
        }

        if (invoiceFilter.buyerId() != null) {
            Join<PersonEntity, InvoiceEntity> buyer = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(
                    buyer.get(PersonEntity_.ID),
                    invoiceFilter.buyerId()
                                                ));
        }
        if (invoiceFilter.product() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(InvoiceEntity_.PRODUCT)),
                    "%" + invoiceFilter.product()
                                       .toLowerCase() + "%"
                                               ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
