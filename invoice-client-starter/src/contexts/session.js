import { createContext, useContext, useState } from "react";

const SessionContext = createContext({
    session: { data: null, status: "loading" },
    setSession: () => { },
});

export function useSession() {
    return useContext(SessionContext);
}

export function SessionProvider({ children }) {
    const [sessionState, setSessionState] = useState(() => {
        const stored = sessionStorage.getItem("session");
        if (stored) {
            const parsed = JSON.parse(stored);
            if (parsed?.data?.token) {
                return { data: parsed.data, status: "authenticated" };
            }
        }
        return { data: null, status: "unauthenticated" };
    });

    const setSession = (newSession) => {
        setSessionState(newSession);
        if (newSession.data) {
            sessionStorage.setItem("session", JSON.stringify(newSession));
        } else {
            sessionStorage.removeItem("session");
        }
    };

    return (
        <SessionContext.Provider value={{session: sessionState, setSession}}>
            {children}
        </SessionContext.Provider>
    )
};