import { useTranslation } from 'react-i18next';

/**
 * LanguageSwitcher component for switching between languages.
 */
export function LanguageSwitcher() {
    const { i18n } = useTranslation();

    const handleChange = (e) => {
        i18n.changeLanguage(e.target.value);
    };

    return (
        <select
            className="form-select form-select-sm"
            style={{ width: 'auto' }}
            value={i18n.language?.substring(0, 2) || 'en'}
            onChange={handleChange}
        >
            <option value="en">EN</option>
            <option value="cs">CZ</option>
        </select>
    );
}
