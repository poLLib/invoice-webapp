import { createContext, useContext, useEffect, useState } from "react";
import { apiGet } from "../utils/api";

const SessionContext = createContext({
    session: { data: null, status: "loading" },
    setSession: () => { },
});

export function useSession() {
    return useContext(SessionContext);
}

export function SessionProvider({ children }) {
    const [sessionState, setSessionState] = useState({ data: null, status: "loading" });

    useEffect(() => {
        async function fetchAuth() {
            try {
                const authData = await apiGet("/api/auth");
                setSessionState({ data: authData, status: "authenticated" });
            } catch (e) {
                if (e instanceof Error && e.response?.status === 401) {
                    setSessionState({ data: null, status: "unauthenticated" });
                } else {
                    throw e;
                }
            }
        }
        fetchAuth();
    }, []);

    return (
        <SessionContext.Provider value={{session: sessionState, setSession: setSessionState}}>
            {children}
        </SessionContext.Provider>
    )
};