import { apiPost } from "../utils/api";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { InputField } from "../components/InputField";
import { FlashMessage } from "../components/FlashMessage";

export function RegistrationPage() {
    const { t } = useTranslation();
    const navigate = useNavigate();
    const [errorMessageState, setErrorMessageState] = useState(null);
    const [valuesState, setValuesState] = useState({ password: "", confirmPassword: "", email: "" });

    function handleChange(e) {
        const fieldName = e.target.name;
        setValuesState({ ...valuesState, [fieldName]: e.target.value });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        if (valuesState.password !== valuesState.confirmPassword) {
            setErrorMessageState(t('register.passwordMismatch'));
            return;
        }

        const { confirmPassword, ...registrationData } = valuesState;

        try {
            await apiPost("/api/user/register", registrationData);
            navigate("/login");
        } catch (e) {
            if (e.response && e.response.code) {

                const errorMessages = { "DUPLICATE_EMAIL": t('register.emailExists') };

                const message = errorMessages[e.response.code] || t('register.error');
                setErrorMessageState(message);
            } else {
                setErrorMessageState(t('register.serverError'));
            }
        }
    }

    return (
        <div className="offset-4 col-sm-6 mt-5">
            <h1>{t('register.title')}</h1>
            <form onSubmit={handleSubmit}>
                {errorMessageState ? <FlashMessage theme={"danger"} text={errorMessageState}></FlashMessage> : null}
                <InputField type="email" name="email" label={t('register.email')} prompt={t('register.emailPlaceholder')}
                    value={valuesState.email}
                    handleChange={handleChange} />
                <InputField type="password" name="password" label={t('register.password')} prompt={t('register.passwordPlaceholder')} min={6}
                    value={valuesState.password}
                    handleChange={handleChange} />
                <InputField type="password" name="confirmPassword" label={t('register.passwordConfirm')} prompt={t('register.passwordConfirmPlaceholder')}
                    value={valuesState.confirmPassword}
                    handleChange={handleChange} />
                <input type="submit" className="btn btn-primary mt-2" value={t('register.submit')} />
            </form>
        </div>
    );
}
