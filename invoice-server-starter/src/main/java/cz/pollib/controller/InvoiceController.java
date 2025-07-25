package cz.pollib.controller;

import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.service.InvoiceServices;
import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceRequest;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Validated
public class InvoiceController {

    private final InvoiceServices invoiceServices;

    public InvoiceController(InvoiceServices invoiceServices) {
        this.invoiceServices = invoiceServices;
    }

    @PostMapping("/invoice")
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody @Valid InvoiceRequest request) {
        return new ResponseEntity<>(invoiceServices.createInvoice(request), CREATED);
    }

    @GetMapping("/invoices")
    public ResponseEntity<InvoicePageResponse> searchInvoices(InvoiceFilter invoiceFilter, @RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(invoiceServices.searchInvoices(invoiceFilter, page), OK);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<InvoiceResponse> getInvoiceDetail(@PathVariable Long invoiceId) {
        return new ResponseEntity<>(invoiceServices.getInvoice(invoiceId), OK);
    }

    @DeleteMapping("/invoice/{invoiceId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceServices.deleteInvoice(invoiceId);
    }

    @PutMapping("/invoice/{invoiceId}")
    public ResponseEntity<InvoiceResponse> updateInvoice(@PathVariable Long invoiceId, @RequestBody @Valid InvoiceRequest request) {
        return new ResponseEntity<>(invoiceServices.updateInvoice(invoiceId, request), OK);
    }

    @GetMapping("/invoices/statistics")
    public ResponseEntity<InvoiceStatisticsResponse> getStatistics() {
        return new ResponseEntity<>(invoiceServices.getInvoiceStatistics(), OK);
    }
}