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
package cz.itnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
// TODO: ?? zobrazit valid msg do flash msg? nezobrazeni nemennych atributu (ico aj.) pri uprave ?? v FE si v tenarni podmince diff apiput apipost pro PUT nevypiseme nemenne pole. na BE pak muzes setnout null pro novou entitu po uprave
// TODO: FlashMsg, presunout okno na FM.. alert(), setIntervat(), useContext()
// TODO: promazat = udelat checkbox pro vyber kterych
// TODO: ?? reset btn, neresetuje vyhledani polozky
// TODO: DTO, zjednodusit dotaz query at rovnou vraci do DTO. personDTO vse se rovnou dotazat na DB.. nova instance InvoiceStatDTO v selectu v repo
// TODO: dynamic searchbar handle event
// TODO: autentizece

// TODO: btn back ve forms pri kliknuti validuje nevyplnene pole