package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonRepository personRepository;

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
/*         InvoiceEntity entity = fetchInvoiceById(id);
        invoiceMapper.updateEntity(data, entity);

        return invoiceMapper.toDTO(entity);
    }*/
        return null;
    }

    @Override
    public InvoiceDTO detailInvoice(Long id) {
        return invoiceMapper.toDTO(invoiceRepository.getReferenceById(id));
    }

    @Override
    public void deleteInvoice(Long id) {

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
