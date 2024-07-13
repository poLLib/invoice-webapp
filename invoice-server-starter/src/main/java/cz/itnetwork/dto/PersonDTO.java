/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.constant.Countries;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Zadejte jméno")
    private String name;

    @NotBlank(message = "Zadejte IČO")
    @Positive
    private String identificationNumber;

    @NotBlank(message = "Zadejte DIČ")
    @Positive
    private String taxNumber;

    @NotBlank(message = "Zadejte číslo účtu")
    @Positive
    private String accountNumber;

    @NotBlank(message = "Zadejte kód banky")
    @Positive
    private String bankCode;

    @NotBlank(message = "Zadejte IBAN")
    private String iban;

    @NotBlank(message = "Zadejte telefoní číslo")
    private String telephone;

    @NotBlank(message = "Zadejte email")
    @Email(message = "Zadejte email ve správném tvaru")
    private String mail;

    @NotBlank(message = "Zadejte ulici a č.p")
    private String street;

    @NotBlank(message = "Zadejte PSČ")
    private String zip;

    @NotBlank(message = "Zadejte obec")
    private String city;

    @NotBlank(message = "Zadejte zemi")
    private Countries country;

    private String note;
}
