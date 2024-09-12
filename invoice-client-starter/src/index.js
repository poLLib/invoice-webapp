import React from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App";
import { SessionProvider } from "./contexts/session";

const root = createRoot(document.getElementById("root"));
root.render(
    <SessionProvider>
        <App />
    </SessionProvider>
);
