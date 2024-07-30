import React from "react";
/**
 * InputField component renders an input element or textarea based on the type prop.
 *
 * Supports `text`, `number`, `date` for input types and `textarea` for multi-line text input.
 *
 * @param {Object} props - Component properties.
 * @param {string} props.type - The type of the input field. Supported types: 'text', 'number', 'date', 'textarea'.
 * @param {string} props.name - The name attribute for the input element.
 * @param {string} [props.label] - The label text for the input field.
 * @param {string} [props.prompt] - Placeholder text for the input field.
 * @param {number} [props.rows] - Number of rows for textarea (if type is 'textarea').
 * @param {number|string} [props.min] - Minimum value or length for the input field.
 * @param {boolean} [props.required] - Whether the input field is required.
 * @param {string} [props.value] - The value of the input field.
 * @param {Function} props.handleChange - The function to call when the input value changes.
 * @param {string} [props.error] - The error message to display for the input field.
 * @returns {JSX.Element|null} A form group containing the input element or textarea if the type is valid; otherwise, null.
 *
 * @example
 * // Renders a text input field
 * <InputField
 *   type="text"
 *   name="username"
 *   label="Username"
 *   prompt="Enter your username"
 *   required={true}
 *   value={username}
 *   handleChange={handleInputChange}
 *   error={usernameError}
 * />
 *
 * @example
 * // Renders a textarea
 * <InputField
 *   type="textarea"
 *   name="description"
 *   label="Description"
 *   prompt="Enter description"
 *   rows={5}
 *   value={description}
 *   handleChange={handleInputChange}
 *   error={descriptionError}
 * />
 */
export function InputField(props) {
  // Supported input types
  const INPUTS = ["text", "number", "date"];

  // Validate element type
  const type = props.type.toLowerCase();
  const isTextarea = type === "textarea";
  const required = props.required || false;

  if (!isTextarea && !INPUTS.includes(type)) {
    return null;
  }

  // Assign minimum value/length based on type
  const minProp = props.min || null;
  const min = ["number", "date"].includes(type) ? minProp : null;
  const minlength = ["text", "textarea"].includes(type) ? minProp : null;

  return (
    <div className="form-group">
      <label>{props.label}:</label>

      {/* Render the appropriate input element */}
      {isTextarea ? (
        <textarea
          required={required}
          className="form-control"
          placeholder={props.prompt}
          rows={props.rows}
          minLength={minlength}
          name={props.name}
          value={props.value}
          onChange={props.handleChange}
        />
      ) : (
        <input
          required={required}
          type={type}
          className={`form-control ${props.error ? "is-invalid" : "is-valid"}`}
          aria-describedby={props.validationFeedback}
          placeholder={props.prompt}
          minLength={minlength}
          min={min}
          name={props.name}
          value={props.value}
          onChange={props.handleChange}
        />
      )}

      {/* Display error message if exists */}
      {props.error && <small id={props.validationFeedback} className="ms-2 invalid-feedback">{props.error}</small>}
    </div>
  );
}
