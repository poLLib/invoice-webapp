package cz.pollib.controller;

import cz.pollib.service.PersonInvoiceServices;
import cz.pollib.service.common.model.ErrorResponse;
import cz.pollib.service.model.InvoiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Tag(
        name = "PersonInvoice",
        description = "Management between sellers and buyers"
)
public class PersonInvoiceController {
    private final PersonInvoiceServices personInvoiceServices;

    public PersonInvoiceController(PersonInvoiceServices personInvoiceServices) {
        this.personInvoiceServices = personInvoiceServices;
    }

    @Operation(
            operationId = "getInvoiceSeller",
            tags = "PersonInvoice",
            summary = "Returns invoices of seller"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns invoices of seller",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceResponse.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/identification/{identificationNumber}/sales",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<InvoiceResponse>> getSellerInvoices(
            @Parameter(
                    description = "Unique identification number of seller",
                    required = true
            )
            @PathVariable String identificationNumber
                                                                  ) {
        return new ResponseEntity<>(
                personInvoiceServices.getInvoicesBySeller(identificationNumber),
                OK
        );
    }

    @Operation(
            operationId = "getInvoiceBuyer",
            tags = "PersonInvoice",
            summary = "Returns invoices of buyer"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns invoices of buyer",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceResponse.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/identification/{identificationNumber}/purchases",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<InvoiceResponse>> getBuyersInvoices(
            @Parameter(
                    description = "Unique identification number of buyer",
                    required = true
            )
            @PathVariable String identificationNumber
                                                                  ) {
        return new ResponseEntity<>(
                personInvoiceServices.getInvoicesByBuyer(identificationNumber),
                OK
        );
    }
}
