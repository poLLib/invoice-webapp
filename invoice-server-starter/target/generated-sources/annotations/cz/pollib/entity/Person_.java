package cz.pollib.entity;

import cz.pollib.constant.Countries;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Person_ {

	public static final String ZIP = "zip";
	public static final String BANK_CODE = "bankCode";
	public static final String COUNTRY = "country";
	public static final String NOTE = "note";
	public static final String MAIL = "mail";
	public static final String HIDDEN = "hidden";
	public static final String CITY = "city";
	public static final String PURCHASES = "purchases";
	public static final String TAX_NUMBER = "taxNumber";
	public static final String TELEPHONE = "telephone";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String SALES = "sales";
	public static final String STREET = "street";
	public static final String IBAN = "iban";
	public static final String NAME = "name";
	public static final String IDENTIFICATION_NUMBER = "identificationNumber";
	public static final String ID = "id";

	
	/**
	 * @see cz.pollib.entity.Person#zip
	 **/
	public static volatile SingularAttribute<Person, String> zip;
	
	/**
	 * @see cz.pollib.entity.Person#bankCode
	 **/
	public static volatile SingularAttribute<Person, String> bankCode;
	
	/**
	 * @see cz.pollib.entity.Person#country
	 **/
	public static volatile SingularAttribute<Person, Countries> country;
	
	/**
	 * @see cz.pollib.entity.Person#note
	 **/
	public static volatile SingularAttribute<Person, String> note;
	
	/**
	 * @see cz.pollib.entity.Person#mail
	 **/
	public static volatile SingularAttribute<Person, String> mail;
	
	/**
	 * @see cz.pollib.entity.Person#hidden
	 **/
	public static volatile SingularAttribute<Person, Boolean> hidden;
	
	/**
	 * @see cz.pollib.entity.Person#city
	 **/
	public static volatile SingularAttribute<Person, String> city;
	
	/**
	 * @see cz.pollib.entity.Person#purchases
	 **/
	public static volatile ListAttribute<Person, Invoice> purchases;
	
	/**
	 * @see cz.pollib.entity.Person#taxNumber
	 **/
	public static volatile SingularAttribute<Person, String> taxNumber;
	
	/**
	 * @see cz.pollib.entity.Person#telephone
	 **/
	public static volatile SingularAttribute<Person, String> telephone;
	
	/**
	 * @see cz.pollib.entity.Person#accountNumber
	 **/
	public static volatile SingularAttribute<Person, String> accountNumber;
	
	/**
	 * @see cz.pollib.entity.Person#sales
	 **/
	public static volatile ListAttribute<Person, Invoice> sales;
	
	/**
	 * @see cz.pollib.entity.Person#street
	 **/
	public static volatile SingularAttribute<Person, String> street;
	
	/**
	 * @see cz.pollib.entity.Person#iban
	 **/
	public static volatile SingularAttribute<Person, String> iban;
	
	/**
	 * @see cz.pollib.entity.Person#name
	 **/
	public static volatile SingularAttribute<Person, String> name;
	
	/**
	 * @see cz.pollib.entity.Person#identificationNumber
	 **/
	public static volatile SingularAttribute<Person, String> identificationNumber;
	
	/**
	 * @see cz.pollib.entity.Person#id
	 **/
	public static volatile SingularAttribute<Person, Long> id;
	
	/**
	 * @see cz.pollib.entity.Person
	 **/
	public static volatile EntityType<Person> class_;

}

