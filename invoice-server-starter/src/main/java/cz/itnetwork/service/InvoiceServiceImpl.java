package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO data) {
        InvoiceEntity entity = invoiceMapper.toEntity(data);
        entity.setBuyer(personRepository.getReferenceById(data.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(data.getBuyer().getId()));

        invoiceRepository.saveAndFlush(entity);
        return invoiceMapper.toDTO(entity);
    }

    @Override
    public InvoiceDTO editInvoice(Long id, InvoiceDTO data) {
        InvoiceEntity invoice = fetchInvoiceById(id);
        invoiceMapper.updateEntity(data, invoice);

        PersonEntity seller = personRepository.getReferenceById(data.getSeller().getId());
        PersonEntity buyer = personRepository.getReferenceById(data.getBuyer().getId());

        invoice.setSeller(seller);
        invoice.setBuyer(buyer);
        invoice.setId(id);

        invoiceRepository.saveAndFlush(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
    }

    @Override
    public InvoiceDTO detailInvoice(Long id) {
        return invoiceMapper.toDTO(invoiceRepository.getReferenceById(id));
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
