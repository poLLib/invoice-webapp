package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping({"/invoices"})
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO data) {
        return invoiceService.createInvoice(data);
    }

    @GetMapping({"/invoices"})
    public InvoicePageDTO getPagesInvoices(InvoiceFilter invoiceFilter,
                                           @RequestParam(defaultValue = "0") int page) {
        return invoiceService.getAllInvoicesPageable(invoiceFilter, page);
    }

    @GetMapping({"/invoices/{invoiceId}"})
    public InvoiceDTO getInvoiceDetail(@PathVariable Long invoiceId) {
        return invoiceService.detailInvoice(invoiceId);
    }

    @DeleteMapping({"/invoices/{invoiceId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping({"/invoices/{invoiceId}"})
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO data) {
        return invoiceService.editInvoice(invoiceId, data);
    }

    @GetMapping({"/invoices/statistics"})
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceService.getInvoiceStatistics();
    }
}
