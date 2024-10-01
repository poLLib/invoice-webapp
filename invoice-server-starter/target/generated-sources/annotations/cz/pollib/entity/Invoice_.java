package cz.pollib.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Invoice.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Invoice_ {

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

	
	/**
	 * @see cz.pollib.entity.Invoice#seller
	 **/
	public static volatile SingularAttribute<Invoice, Person> seller;
	
	/**
	 * @see cz.pollib.entity.Invoice#note
	 **/
	public static volatile SingularAttribute<Invoice, String> note;
	
	/**
	 * @see cz.pollib.entity.Invoice#product
	 **/
	public static volatile SingularAttribute<Invoice, String> product;
	
	/**
	 * @see cz.pollib.entity.Invoice#price
	 **/
	public static volatile SingularAttribute<Invoice, Long> price;
	
	/**
	 * @see cz.pollib.entity.Invoice#dueDate
	 **/
	public static volatile SingularAttribute<Invoice, LocalDate> dueDate;
	
	/**
	 * @see cz.pollib.entity.Invoice#invoiceNumber
	 **/
	public static volatile SingularAttribute<Invoice, Integer> invoiceNumber;
	
	/**
	 * @see cz.pollib.entity.Invoice#vat
	 **/
	public static volatile SingularAttribute<Invoice, Integer> vat;
	
	/**
	 * @see cz.pollib.entity.Invoice#id
	 **/
	public static volatile SingularAttribute<Invoice, Long> id;
	
	/**
	 * @see cz.pollib.entity.Invoice#issued
	 **/
	public static volatile SingularAttribute<Invoice, LocalDate> issued;
	
	/**
	 * @see cz.pollib.entity.Invoice
	 **/
	public static volatile EntityType<Invoice> class_;
	
	/**
	 * @see cz.pollib.entity.Invoice#buyer
	 **/
	public static volatile SingularAttribute<Invoice, Person> buyer;

}

