import { createContext, useState } from "react";

export const FlashMessageContext = createContext();

export function FlashMessageContextProvider({ children }) {
    const [flashMessage, setFlashMessage] = useState(null);

    return (
        <FlashMessageContext.Provider value={{ flashMessage, setFlashMessage }}>
            {children}
        </FlashMessageContext.Provider>
    )
}