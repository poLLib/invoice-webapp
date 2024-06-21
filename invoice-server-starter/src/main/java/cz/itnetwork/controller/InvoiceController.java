package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.InvoiceStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceStatisticsService invoiceStatisticsService;

    @PostMapping({"/invoices", "/invoices/"})
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO data) {
        return invoiceService.createInvoice(data);
    }

    @GetMapping({"/invoices", "/invoices/"})
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    public InvoiceDTO getInvoiceDetail(@PathVariable Long invoiceId) {
        return invoiceService.detailInvoice(invoiceId);
    }

    @DeleteMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}/"})
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO data) {
        return invoiceService.editInvoice(invoiceId, data);
    }

    @GetMapping({"/invoices/statistics", "/invoices/statistics/"})
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceStatisticsService.getInvoiceStatistics();
    }
}
