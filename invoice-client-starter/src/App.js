import React from "react";
import { BrowserRouter as Router, Link, Navigate, Route, Routes, } from "react-router-dom";
import { PersonIndex } from "./persons/PersonIndex";
import { PersonDetail } from "./persons/PersonDetail";
import { PersonForm } from "./persons/PersonForm";
import { InvoiceForm } from "./invoice/InvoiceForm";
import { InvoiceDetail } from "./invoice/InvoiceDetail";
import { InvoiceIndex } from "./invoice/InvoiceIndex";
import { StatisticsTable } from "./statistics/StatisticsTable";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "./styles.css"
import { FlashMessageContextProvider } from "./contexts/FlashMessageContext";
import { useSession } from "./contexts/session";
import { LoginPage } from "./login/LoginPage";
import { RegistrationPage } from "./login/RegistrationPage";
import { ProtectedRoute } from "./components/ProtectedRoute";

/**
 * Main App Component
 * - Sets up the Router for navigation
 * - Defines the layout and main routes for the application
 * - Starts the user's session
 */
export function App() {

    const { session, setSession } = useSession();

    function handleLogoutClick() {
        setSession({ data: null, status: "unauthenticated" });
    }

    return (
        /* { Navigation menu} */
        <div className="wrapper">
            <Router>
                <div className="container content">
                    <nav className="navbar navbar-expand-lg navbar-light nav-style">
                        <ul className="navbar-nav d-flex flex-row w-100 align-items-center">
                            {session.data && (
                                <>
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
                                </>
                            )}

                            <li className="nav-item ms-auto">
                                {session.data ? (

                                    <div className="dropdown">
                                        <img
                                            src="/profile-picture.png"
                                            alt="Profil"
                                            className="rounded-circle dropdown-toggle"
                                            data-bs-toggle="dropdown"
                                            role="button"
                                            style={{ width: "32px", height: "32px", cursor: "pointer" }}
                                        />
                                        <ul className="dropdown-menu dropdown-menu-end">
                                            <li className="px-3 py-2">
                                                <span className="fw-bold fs-6">{session.data.email}</span>
                                            </li>
                                            <li><hr className="dropdown-divider" /></li>
                                            <li>
                                                <button className="dropdown-item" onClick={handleLogoutClick}>
                                                    Odhlásit se
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                ) : session.status === "loading" ? (
                                    <div className="spinner-border spinner-border-sm" role="status">
                                        <span className="visually-hidden">Načítání...</span>
                                    </div>
                                ) : (
                                    <div className="d-flex gap-2">
                                        <Link to={"/register"} className="btn btn-outline-primary">
                                            Registrace
                                        </Link>
                                        <Link to={"/login"} className="btn btn-outline-primary">
                                            Přihlášení
                                        </Link>
                                    </div>
                                )}
                            </li>
                        </ul>
                    </nav>

                    <FlashMessageContextProvider>
                        {/* Person (companies) Routes */}
                        <Routes>
                            <Route index element={<Navigate to={"/persons/pages/1"} />} />
                            <Route path="/persons">
                                <Route index element={<ProtectedRoute><PersonIndex /></ProtectedRoute>} />
                                <Route path="pages/:page" element={<ProtectedRoute><PersonIndex /></ProtectedRoute>} />
                                <Route path="show/:id" element={<ProtectedRoute><PersonDetail /></ProtectedRoute>} />
                                <Route path="create" element={<ProtectedRoute><PersonForm /></ProtectedRoute>} />
                                <Route path="edit/:id" element={<ProtectedRoute><PersonForm /></ProtectedRoute>} />
                            </Route>

                            {/* Invoice Routes */}
                            <Route index element={<Navigate to={"/invoices/pages/1"} />} />
                            <Route path="/invoices">
                                <Route index element={<ProtectedRoute><InvoiceIndex /></ProtectedRoute>} />
                                <Route path="pages/:page" element={<ProtectedRoute><InvoiceIndex /></ProtectedRoute>} />
                                <Route path="create" element={<ProtectedRoute><InvoiceForm /></ProtectedRoute>} />
                                <Route path="edit/:id" element={<ProtectedRoute><InvoiceForm /></ProtectedRoute>} />
                                <Route path="show/:id" element={<ProtectedRoute><InvoiceDetail /></ProtectedRoute>} />
                            </Route>

                            {/* Statistics Route */}
                            <Route path="/statistics" element={<ProtectedRoute><StatisticsTable /></ProtectedRoute>} />
                            <Route path="/login" element={<LoginPage />} />
                            <Route path="/register" element={<RegistrationPage />} />
                        </Routes>
                    </FlashMessageContextProvider>
                </div>

            </Router>

            {/* Footer */}
            <footer>
                <div className="container">
                    <div className="row">
                        <div className="col-md-6">
                            <h4>Fakturační databáze</h4>
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