import { apiPost } from "../utils/api";
import { json, useNavigate } from "react-router-dom";
import { useState } from "react";
import { InputField } from "../components/InputField";
import { FlashMessage } from "../components/FlashMessage";

export function RegistrationPage() {

    const navigate = useNavigate();
    const [errorMessageState, setErrorMessageState] = useState(null);
    const [valuesState, setValuesState] = useState({ password: "", confirmPassword: "", email: "" });

    async function handleSubmit(e) {
        e.preventDefault();
        if (valuesState.password !== valuesState.confirmPassword) {
            setErrorMessageState("Hesla se nerovnají");
            return;
        }

        const { confirmPassword, ...registrationData } = valuesState;

        try {
            await apiPost("/api/user", registrationData);
            navigate("/login");
        } catch (e) {
            if (e instanceof Error && e.response.status === 400) {
                const message = await e.response.text();
                setErrorMessageState(message);
            } else {
                setErrorMessageState("Při komunikaci se serverem nastala chyba.");
            }
        }
    }

    return (
        <div className="offset-4 col-sm-6 mt-5">
            <h1>Registrace</h1>
            <form onSubmit={handleSubmit}>
                {errorMessageState ? <FlashMessage theme={"danger"} text={errorMessageState}></FlashMessage> : null}
                <InputField type="email" name="email" label="E-mail" prompt="Zadejte Váš E-mail" value={valuesState.email}
                    handleChange={handleChange} />
                <InputField type="password" name="password" label="Heslo" prompt="Zadejte Vaše heslo" min={6} value={valuesState.password}
                    handleChange={handleChange} />
                <InputField type="password" name="confirmPassword" label="Heslo znovu" prompt="Zadejte Vaše heslo znovu" value={valuesState.confirmPassword}
                    handleChange={handleChange} />
                <input type="submit" className="btn btn-primary mt-2" value="Registrovat se" />
            </form>
        </div>
    );
}