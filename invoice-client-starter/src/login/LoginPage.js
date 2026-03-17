import { InputField } from "../components/InputField";
import { FlashMessage } from "../components/FlashMessage";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useSession } from "../contexts/session";
import { useNavigate } from "react-router-dom";
import { apiPost } from "../utils/api";

export function LoginPage() {
    const { t } = useTranslation();
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
        e.preventDefault();

        try {
            const data = await apiPost("/api/user/login", valuesState);
            setSession({ data, status: "authenticated" });
        } catch (e) {
            if (e.response) {
                const message = e.response.message || t('login.failed');
                setErrorMessageState(message);
            } else {
                setErrorMessageState(t('login.serverError'));
            }
        }
    }

    return (
        <div className="offset-4 col-sm-6 mt-5">
            <h1>{t('login.title')}</h1>
            <form onSubmit={handleSubmit}>
                {errorMessageState ? <FlashMessage theme={"danger"} text={errorMessageState}></FlashMessage> : null}

                <InputField type="email" required={true} label={t('login.email')} handleChange={handleChange}
                    value={valuesState.email} prompt={t('login.emailPlaceholder')} name="email" />
                <InputField type="password" required={true} label={t('login.password')} handleChange={handleChange}
                    value={valuesState.password} prompt={t('login.passwordPlaceholder')} name="password" />

                <input type="submit" className="btn btn-primary mt-2" value={t('login.submit')} />
            </form>
        </div>
    )
}
