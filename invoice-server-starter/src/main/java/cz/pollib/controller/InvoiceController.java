package cz.pollib.controller;

import cz.pollib.entity.filter.InvoiceFilter;
import cz.pollib.service.InvoiceServices;
import cz.pollib.service.common.model.ErrorResponse;
import cz.pollib.service.model.CreateInvoiceRequest;
import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.service.model.UpdateInvoiceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Validated
@Tag(name = "Invoice", description = "Invoice management")
public class InvoiceController {

    private final InvoiceServices invoiceServices;

    public InvoiceController(InvoiceServices invoiceServices) {
        this.invoiceServices = invoiceServices;
    }

    @Operation(
            operationId = "createInvoice",
            tags = "Invoice",
            summary = "Creates new Invoice"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Invoice was created",
                            content = {@Content(schema = @Schema(implementation = InvoiceResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @PostMapping(
            value = "/invoice",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody @Valid CreateInvoiceRequest request) {
        return new ResponseEntity<>(invoiceServices.createInvoice(request), CREATED);
    }

    @Operation(
            operationId = "searchInvoices",
            tags = "Invoices",
            summary = "Search invoices"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns list of invoices",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceResponse.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/invoices",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InvoicePageResponse> searchInvoices(
            InvoiceFilter invoiceFilter, // TODO: 28.07.2025 query params as input for controller
            @Parameter(
                    description = "Page",
                    schema = @Schema(defaultValue = "0")
            )
            @RequestParam(defaultValue = "0") int page
    ) {
        return new ResponseEntity<>(invoiceServices.searchInvoices(invoiceFilter, page), OK);
    }

    @Operation(
            operationId = "getInvoice",
            tags = "Invoice",
            summary = "Returns existing Invoice"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns invoice",
                            content = {@Content(schema = @Schema(implementation = InvoiceResponse.class))}
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
            value = "/invoice/{invoiceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InvoiceResponse> getInvoice(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long invoiceId
    ) {
        return new ResponseEntity<>(invoiceServices.getInvoice(invoiceId), OK);
    }

    @Operation(
            operationId = "deleteInvoice",
            tags = "Invoice",
            summary = "Deletes existing Invoice"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Invoice was deleted"
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
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(value = "/invoice/{invoiceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteInvoice(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long invoiceId
    ) {
        invoiceServices.deleteInvoice(invoiceId);
    }

    @Operation(
            operationId = "updateInvoice",
            tags = "Invoice",
            summary = "Updates existing Invoice"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Invoice was updated",
                            content = {@Content(schema = @Schema(implementation = InvoiceResponse.class))}
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
    @PutMapping(
            value = "/invoice/{invoiceId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InvoiceResponse> updateInvoice(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long invoiceId,
            @RequestBody @Valid UpdateInvoiceRequest request
    ) {
        return new ResponseEntity<>(invoiceServices.updateInvoice(invoiceId, request), OK);
    }

    @Operation(
            operationId = "getInvoiceStatistics",
            tags = "Invoice",
            summary = "Returns statistics of Invoices"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns statistics of Invoices",
                            content = {@Content(schema = @Schema(implementation = InvoiceStatisticsResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/invoices/statistics",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InvoiceStatisticsResponse> getStatistics() {
        return new ResponseEntity<>(invoiceServices.getInvoiceStatistics(), OK);
    }
}