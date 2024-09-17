package cz.pollib.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice.class)
public abstract class Invoice_ {

	public static volatile SingularAttribute<Invoice, Person> seller;
	public static volatile SingularAttribute<Invoice, String> note;
	public static volatile SingularAttribute<Invoice, String> product;
	public static volatile SingularAttribute<Invoice, Long> price;
	public static volatile SingularAttribute<Invoice, LocalDate> dueDate;
	public static volatile SingularAttribute<Invoice, Integer> invoiceNumber;
	public static volatile SingularAttribute<Invoice, Integer> vat;
	public static volatile SingularAttribute<Invoice, Long> id;
	public static volatile SingularAttribute<Invoice, LocalDate> issued;
	public static volatile SingularAttribute<Invoice, Person> buyer;

	public static final String SELLER = "seller";
	public static final String NOTE = "note";
	public static final String PRODUCT = "product";
	public static final String PRICE = "price";
	public static final String DUE_DATE = "dueDate";
	public static final String INVOICE_NUMBER = "invoiceNumber";
	public static final String VAT = "vat";
	public static final String ID = "id";
	public static final String ISSUED = "issued";
	public static final String BUYER = "buyer";

}

