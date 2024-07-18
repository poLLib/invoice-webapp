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

import React from "react";
import {
  BrowserRouter as Router,
  Link,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import { PersonIndex } from "./persons/PersonIndex";
import { PersonDetail } from "./persons/PersonDetail";
import { PersonForm } from "./persons/PersonForm";
import { InvoiceForm } from "./invoice/InvoiceForm";
import { InvoiceDetail } from "./invoice/InvoiceDetail";
import { InvoiceIndex } from "./invoice/InvoiceIndex";
import { StatisticsTable } from "./statistics/StatisticsTable";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles.css"

export function App() {
  return (
    <div className="wrapper">
      <Router>
        <div className="container content">
          <nav className="navbar navbar-expand-lg navbar-light nav-style">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/persons"} className="nav-link">
                  Společnosti
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/invoices"} className="nav-link">
                  Faktury
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"statistics"} className="nav-link">
                  Statistiky
                </Link>
              </li>

            </ul>
          </nav>

          <Routes>
            <Route index element={<Navigate to={"/persons/pages/1"} />} />
            <Route path="/persons">
              <Route index element={<PersonIndex />} />
              <Route path="pages/:page" element={<PersonIndex />} />
              <Route path="show/:id" element={<PersonDetail />} />
              <Route path="create" element={<PersonForm />} />
              <Route path="edit/:id" element={<PersonForm />} />
            </Route>

            <Route index element={<Navigate to={"/invoices/pages/1"} />} />
            <Route path="/invoices">
              <Route index element={<InvoiceIndex />} />
              <Route path="pages/:page" element={<InvoiceIndex />} />
              <Route path="create" element={<InvoiceForm />} />
              <Route path="show/:id" element={<InvoiceDetail />} />
              <Route path="edit/:id" element={<InvoiceForm />} />
            </Route>
            <Route path="statistics" element={<StatisticsTable />} />
          </Routes>
        </div>

      </Router>
      <footer >
        <div className="container">
          <div className="row">
            <div className="col-md-6">
              <h4>Fakturační databáze</h4>
              <p>Tento projekt slouží jako učební.</p>
            </div>
            <div className="col-md-6 text-md-right">
              <ul className="list-unstyled">
                <li>Email: tom.bilder321@gmail.com</li>
                <li>Telefon: +420 735 256 931</li>
              </ul>
            </div>
          </div>
          <div className="text-center mt-3">
            <p>&copy; 2024 poLLib</p>
          </div>
        </div>
      </footer>
    </div>
  );
}