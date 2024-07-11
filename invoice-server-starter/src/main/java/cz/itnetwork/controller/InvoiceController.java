package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping({"/invoices", "/invoices/"})
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO data) {
        return invoiceService.createInvoice(data);
    }

    @GetMapping({"/invoices", "/invoices/"})
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAllInvoices(invoiceFilter);
    }

/*    @GetMapping(value = {"/invoices", "/invoices/"}, params = {"page", "size"})
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter,
                                           @RequestParam("page") int page
    ) {
        Page<InvoiceDTO> resultPage = invoiceService.getAllInvoices(invoiceFilter, page);
        return resultPage.getContent();
    }*/

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
        return invoiceService.getInvoiceStatistics();
    }
}
