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
import { FlashMessageContextProvider } from "./contexts/FlashMessageContext";
import { useSession } from "./contexts/session";
import { apiDelete } from "./utils/api";
import { LoginPage } from "./login/LoginPage";
import { RegistrationPage } from "./login/RegistrationPage";

function handleLogoutClick() {
  apiDelete("/api/auth").finally(() => setSession({ data: null, status: "unauthorized" }));
}

/**
 * Main App Component
 * - Sets up the Router for navigation
 * - Defines the layout and main routes for the application
 * - Starts the user's session 
 */
export function App() {

  const { session, setSession } = useSession();

  return (
    /* { Navigation menu} */
    <div className="wrapper">
      <Router>
        <div className="container content">
          <nav className="navbar navbar-expand-lg navbar-light nav-style">
            <ul className="navbar-nav mr-auto d-flex flex-row">
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
                <Link to={"/statistics"} className="nav-link">
                  Statistiky
                </Link>
              </li>

              {session.data ? <>
                <li className="nav-item">{session.data.email}</li>
                <li className="nav-item">
                  <button className="btn btn-sm btn-secondary" onClick={handleLogoutClick}>Odhlásit se</button>
                </li>
              </> : session.status === "loading" ?
                <>
                  <div className="spinner-border spinner-border-sm" role="status">
                    <span className="visually-hidden">Načítání...</span>
                  </div>
                </>
                : <>
                  <li className="nav-item">
                    <Link to={"/register"}>Registrace</Link>
                  </li>
                  <li className="nav-item">
                    <Link to={"/login"}>Přihlášení</Link>
                  </li>
                </>
              }
            </ul>
          </nav>

          <FlashMessageContextProvider>
            {/* Person (companies) Routes */}
            <Routes>
              <Route index element={<Navigate to={"/persons/pages/1"} />} />
              <Route path="/persons">
                <Route index element={<PersonIndex />} />
                <Route path="pages/:page" element={<PersonIndex />} />
                <Route path="show/:id" element={<PersonDetail />} />
                <Route path="create" element={<PersonForm />} />
                <Route path="edit/:id" element={<PersonForm />} />
              </Route>

              {/* Invoice Routes */}
              <Route index element={<Navigate to={"/invoices/pages/1"} />} />
              <Route path="/invoices">
                <Route index element={<InvoiceIndex />} />
                <Route path="pages/:page" element={<InvoiceIndex />} />
                <Route path="create" element={<InvoiceForm />} />
                <Route path="edit/:id" element={<InvoiceForm />} />
                <Route path="show/:id" element={<InvoiceDetail />} />
              </Route>

              {/* Statistics Route */}
              <Route path="/statistics" element={<StatisticsTable />} />
              <Route path="/login" element={<LoginPage/>} />
              <Route path="/register" element={<RegistrationPage/>}/>
            </Routes>
          </FlashMessageContextProvider>
        </div>

      </Router>

      {/* Footer */}
      <footer >
        <div className="container">
          <div className="row">
            <div className="col-md-6">
              <h4>Fakturační databáze</h4>
              <p>Tento projekt vznikl s pomocí ITnetwork a slouží jako ukázka dovedností.</p>
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