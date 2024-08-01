/**
 * Formats a date string into a more readable format.
 * @param {string} str - The date string to be formatted.
 * @param {boolean} [locale=false] - If true, formats the date in Czech locale format.
 * @returns {string} - The formatted date string.
 * @example
 * // returns '2023-07-13'
 * dateStringFormatter('2023-07-13T00:00:00Z');
 * @example
 * // returns '13. července 2023'
 * dateStringFormatter('2023-07-13T00:00:00Z', true);
 */
export function dateStringFormatter(str, locale = false) {
    const d = new Date(str);

    if (locale) {
        return d.toLocaleDateString("cs-CZ", {
            year: "numeric",
            month: "long",
            day: "numeric",
        });
    }

    const year = d.getFullYear();
    const month = "" + (d.getMonth() + 1);
    const day = "" + d.getDate();

    return [
        year,
        month.length < 2 ? "0" + month : month,
        day.length < 2 ? "0" + day : day,
    ].join("-");
}