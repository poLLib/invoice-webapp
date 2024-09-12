import { InputField } from "../components/InputField";
import { FlashMessage } from "../components/FlashMessage";
import { useEffect, useState } from "react";
import { useSession } from "../contexts/session";
import { useNavigate } from "react-router-dom";
import { apiPost } from "../utils/api";

export function LoginPage() {
    const [valuesState, setValuesState] = useState({ email: "", password: "" });
    const [errorMessageState, setErrorMessageState] = useState(null);
    const { session, setSession } = useSession();
    const navigate = useNavigate();

    useEffect(() => {
        if (session.data) {
            navigate("/");
        }
    }, [session]);

    function handleChange(e) {
        const fieldName = e.target.name;
        setValuesState({ ...valuesState, [fieldName]: e.target.value });
    }

    async function handleSubmit(e) {
        e.preventDefauld();

        try {
            const data = await apiPost("/api/auth", valuesState);
            setSession({ data, status: "authenticated" });
        } catch (e) {
            if (e instanceof Error && e.response) {
                const message = await e.response.text();
                setErrorMessageState(message);
            } else {
                setErrorMessageState("Při komunikaci se serverem nastala chyba.");
            }
        }
    }

    return (
        <div className="offset-4 col-sm-6 mt-5">
            <h1>Přihlášení</h1>
            <form onSubmit={handleSubmit}>
                {errorMessageState ? <FlashMessage theme={"danger"} text={errorMessageState}></FlashMessage> : null}

                <InputField type="email" required={true} label="E-mail" handleChange={handleChange}
                    value={valuesState.email} prompt="E-mail" name="email" />
                <InputField type="password" required={true} label="Heslo" handleChange={handleChange}
                    value={valuesState.password} prompt={"Heslo"} name="password" />

                <input type="submit" className="btn btn-primary mt-2" value="Přihlášení" />
            </form>
        </div>
    )
}