package cz.pollib.entity;

import cz.pollib.constant.Countries;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> zip;
	public static volatile SingularAttribute<Person, String> bankCode;
	public static volatile SingularAttribute<Person, Countries> country;
	public static volatile SingularAttribute<Person, String> note;
	public static volatile SingularAttribute<Person, String> mail;
	public static volatile SingularAttribute<Person, Boolean> hidden;
	public static volatile SingularAttribute<Person, String> city;
	public static volatile ListAttribute<Person, Invoice> purchases;
	public static volatile SingularAttribute<Person, String> taxNumber;
	public static volatile SingularAttribute<Person, String> telephone;
	public static volatile SingularAttribute<Person, String> accountNumber;
	public static volatile ListAttribute<Person, Invoice> sales;
	public static volatile SingularAttribute<Person, String> street;
	public static volatile SingularAttribute<Person, String> iban;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, String> identificationNumber;
	public static volatile SingularAttribute<Person, Long> id;

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

}

