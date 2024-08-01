package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.dto.validation.ValidInvoiceDates;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidInvoiceDates
public class InvoiceDTO {

    @JsonProperty("_id")
    private Long id;

    @Positive(message = "Zadejte číslo faktury v absolutním čísle")
    private int invoiceNumber;

    @NotNull(message = "Zadejte datum vystavení faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @NotNull(message = "Zadejte datum splatnosti faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotBlank(message = "Vyplňte pole položka")
    private String product;

    @Positive(message = "Zadejte cenu v absolutním čísle")
    private Long price;

    @Positive(message = "Zadejte DPH v absolutním čísle")
    @Max(value = 100, message = "Číslo nemůže být větší než 100")
    private int vat;

    private String note;

    @NotNull(message = "Zvolte odběratele")
    private PersonDTO buyer;

    @NotNull(message = "Zvolte dodavatele")
    private PersonDTO seller;
}
