/**
 * Country enumeration provides constants for supported country codes.
 * 
 * The `Country` object is a frozen constant, ensuring that its properties 
 * cannot be modified, which helps in maintaining consistent usage throughout 
 * the application.
 * 
 * @type {Object}
 * @property {string} CZECHIA - Represents the Czech Republic.
 * @property {string} SLOVAKIA - Represents Slovakia.
 */
export const Country = Object.freeze({
    CZECHIA: 'CZECHIA',
    SLOVAKIA: 'SLOVAKIA',
});
