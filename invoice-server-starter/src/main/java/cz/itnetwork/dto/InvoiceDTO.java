package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    @JsonProperty("_id")
    private Long id;
    private int invoiceNumber;
    private Date issued;
    private Date dueDate;
    private String product;
    private Long price;
    private int vat;
    private String note;

    private PersonDTO buyer;
    private PersonDTO seller;
}
