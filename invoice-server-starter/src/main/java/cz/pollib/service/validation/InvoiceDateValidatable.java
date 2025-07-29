package cz.pollib.service.validation;

import java.time.LocalDate;

public interface InvoiceDateValidatable {
    LocalDate getIssued();
    LocalDate getDueDate();
}
