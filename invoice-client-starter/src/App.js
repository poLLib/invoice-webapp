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
import "bootstrap/dist/css/bootstrap.min.css";
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
import { PersonStatistics } from "./persons/PersonStatistics";
import { InvoiceForm } from "./invoice/InvoiceForm";
import { InvoiceStatistics } from "./invoice/InvoiceStatistics";
import { InvoiceDetail } from "./invoice/InvoiceDetail";
import { InvoiceIndex } from "./invoice/InvoiceIndex";

export function App() {
  return (
    <Router>
      <div className="container">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
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
              <Link to={"invoices/statistics"} className="nav-link">
                Statistiky faktur
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"persons/statistics"} className="nav-link">
                Statistiky firem
              </Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route index element={<Navigate to={"/persons"} />} />
          <Route path="/persons">
            <Route index element={<PersonIndex />} />
            <Route path="show/:id" element={<PersonDetail />} />
            <Route path="create" element={<PersonForm />} />
            <Route path="edit/:id" element={<PersonForm />} />
            <Route path="statistics" element={<PersonStatistics />} />
          </Route>

          <Route index element={<Navigate to={"/invoices"} />} />
          <Route path="/invoices">
            <Route index element={<InvoiceIndex />} />
            <Route path="create" element={<InvoiceForm />} />
            <Route path="show/:id" element={<InvoiceDetail />} />
            <Route path="edit/:id" element={<InvoiceForm />} />
            <Route path="statistics" element={<InvoiceStatistics />} />

          </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
