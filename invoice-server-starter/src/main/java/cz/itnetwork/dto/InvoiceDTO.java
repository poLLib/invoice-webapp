package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Vyplňte pole IČO")
    private int invoiceNumber;

    @NotBlank(message = "Zadejte datum vystavení faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @NotBlank(message = "Zadejte datum splatnosti faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotBlank(message = "Vyplňte pole položka")
    private String product;

    @NotBlank(message = "Zadejte cenu")
    @Positive(message = "Částka může být nejménně 0")
    @Min(value = 0, message = "Částka může být nejménně 0")
    private Long price;

    @NotBlank(message = "Zadejte DPH")
    @Positive(message = "Částka může být nejménně 0")
    @Min(value = 0, message = "Částka může být nejménně 0")
    private int vat;

    private String note;

    @NotBlank(message = "Musíte zvolit dodavatele")
    private PersonDTO buyer;

    @NotBlank(message = "Musíte zvolit odběratele")
    private PersonDTO seller;
}
