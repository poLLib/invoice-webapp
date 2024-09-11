package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.service.InvoiceOperations;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class InvoiceController {

    private final InvoiceOperations invoiceOperations;

    public InvoiceController(InvoiceOperations invoiceOperations) {
        this.invoiceOperations = invoiceOperations;
    }

    @PostMapping("/invoice")
    public InvoiceDTO createInvoice(@RequestBody @Valid InvoiceDTO data) {
        return invoiceOperations.createInvoice(data);
    }

    @GetMapping("/invoices")
    public InvoicePageDTO getPagesInvoices(InvoiceFilter invoiceFilter,
                                           @RequestParam(defaultValue = "0") int page) {
        return invoiceOperations.getAllInvoicesPageable(invoiceFilter, page);
    }

    @GetMapping("/invoice/{invoiceId}")
    public InvoiceDTO getInvoiceDetail(@PathVariable Long invoiceId) {
        return invoiceOperations.detailInvoice(invoiceId);
    }

    @DeleteMapping("/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceOperations.deleteInvoice(invoiceId);
    }

    @PutMapping("/invoice/{invoiceId}")
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody @Valid InvoiceDTO data) {
        return invoiceOperations.editInvoice(invoiceId, data);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceOperations.getInvoiceStatistics();
    }
}