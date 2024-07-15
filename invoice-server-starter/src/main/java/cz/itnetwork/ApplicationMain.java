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
// TODO: VALID MSH do flash msg? nezobrazeni nemennych atributu (ico aj.) pri uprave ?? v FE si v tenarni podmince diff apiput apipost pro PUT nevypiseme nemenne pole. na BE pak muzes setnout null pro novou entitu po uprave
// TODO: FLASHMSH, presunout okno na FM.. alert(), setIntervat(), useContext()
// TODO: CHECKBOX udelat checkbox pro vyber na promazani
// TODO: RESET BTN, neresetuje vyhledani polozky !!!!!
// TODO: JQUERY DTOSTATS, vyrvorit nova instance InvoiceStatDTO v primo v query selectu v repo
// TODO: SEARCHBAR dynamic = resit pres handle event. pokazde kdyz se zmeni pole tak zmena stavu, use debounced tool?
// TODO: AUTENTIZACE
// TODO: PAGINACE pri kliku na jinou page zachovat size. pri submit filtrace zachovat page. limitovat zobrazeni pages (budto par tecek, nebo zobazovat middle active a zbytek posouvat  !!!!!
// TODO: BACKBUTTON ve forms pri kliknuti validuje nevyplnene pole
// TODO: snizit responsibilitu
// TODO:

// TODO:        ?? nechavame si tento projekt do portfolia? ikdyz tam mam licencni komentar u clienta od ITN? Martin Valicek => ??
// TODO:        ?? testy jen junit