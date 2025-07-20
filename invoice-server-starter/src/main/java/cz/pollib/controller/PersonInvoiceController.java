package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.Invoice;
import cz.pollib.service.PersonOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PersonInvoiceController {
    private final PersonOperations personOperations;

    private final InvoiceMapper invoiceMapper;

    public PersonInvoiceController(PersonOperations personOperations, InvoiceMapper invoiceMapper) {
        this.personOperations = personOperations;
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSellerInvoices(@PathVariable String identificationNumber) {
        return listToDTO(personOperations.getInvoicesBySeller(identificationNumber));
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getBuyersInvoices(@PathVariable String identificationNumber) {
        return listToDTO(personOperations.getInvoicesByBuyer(identificationNumber));
    }

    // region: Private methods

    /**
     * Converts the list of entities into the list of DTO's
     *
     * @param entities The list of Invoice
     * @return list of InvoiceDTO
     */
    private List<InvoiceDTO> listToDTO(List<Invoice> entities) {
        return entities.stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }
}
