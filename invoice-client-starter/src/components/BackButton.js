import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";


/**
 * BackButton component renders a button that navigates the user back to the previous page.
 *
 * @param {Object} props - Component properties.
 * @param {string} props.style - The CSS class to apply to the button.
 * @returns {JSX.Element} A button that navigates back when clicked.
 *
 * @example
 * // Renders a back button with the 'btn btn-primary' class
 * <BackButton style="btn btn-primary" />
 */
export function BackButton({ style }) {
    const { t } = useTranslation();
    const navigate = useNavigate()

    return (
        <>
            <button className={style} type="button" onClick={() => navigate(-1)}>{t('common.back')}</button></>
    );
}
