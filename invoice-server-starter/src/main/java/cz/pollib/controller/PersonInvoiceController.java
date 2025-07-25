package cz.pollib.controller;

import cz.pollib.service.PersonInvoiceServices;
import cz.pollib.service.model.InvoiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PersonInvoiceController {
    private final PersonInvoiceServices personInvoiceServices;

    public PersonInvoiceController(PersonInvoiceServices personInvoiceServices) {
        this.personInvoiceServices = personInvoiceServices;
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public ResponseEntity<List<InvoiceResponse>> getSellerInvoices(@PathVariable String identificationNumber) {
        return new ResponseEntity<>(personInvoiceServices.getInvoicesBySeller(identificationNumber), OK);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public ResponseEntity<List<InvoiceResponse>> getBuyersInvoices(@PathVariable String identificationNumber) {
        return new ResponseEntity<>(personInvoiceServices.getInvoicesByBuyer(identificationNumber), OK);
    }
}
