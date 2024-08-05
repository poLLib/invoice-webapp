package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@Validated
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody @Valid InvoiceDTO data) {
        return invoiceService.createInvoice(data);
    }

    @GetMapping
    public InvoicePageDTO getPagesInvoices(InvoiceFilter invoiceFilter,
                                           @RequestParam(defaultValue = "0") int page) {
        return invoiceService.getAllInvoicesPageable(invoiceFilter, page);
    }

    @GetMapping("{invoiceId}")
    public InvoiceDTO getInvoiceDetail(@PathVariable Long invoiceId) {
        return invoiceService.detailInvoice(invoiceId);
    }

    @DeleteMapping("{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping("{invoiceId}")
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody @Valid InvoiceDTO data) {
        return invoiceService.editInvoice(invoiceId, data);
    }

    @GetMapping("statistics")
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceService.getInvoiceStatistics();
    }
}
