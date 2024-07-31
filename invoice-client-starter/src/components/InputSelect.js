import React from "react";

/**
 * InputSelect component renders a select dropdown for choosing options.
 *
 * Supports both single and multiple selection modes. Handles rendering options based on whether
 * the items are objects (e.g., database records) or simple enum values.
 *
 * @param {Object} props - Component properties.
 * @param {boolean} [props.multiple=false] - Whether the select allows multiple selections.
 * @param {boolean} [props.required=false] - Whether the select is required.
 * @param {string} props.name - The name attribute for the select element.
 * @param {string} [props.label] - The label text for the select field.
 * @param {string} [props.prompt] - Placeholder text for the select field.
 * @param {Array} props.items - The list of items to display in the select options.
 * @param {Object} [props.enum] - An optional object mapping item values to display names (for enum values).
 * @param {any} props.value - The selected value(s) in the select.
 * @param {Function} props.handleChange - The function to call when the selection changes.
 * @returns {JSX.Element} A form group containing the select dropdown.
 *
 * @example
 * // Renders a single select dropdown with object items
 * <InputSelect
 *   name="person"
 *   label="Select Person"
 *   prompt="Choose a person"
 *   items={[{ _id: '1', name: 'John Doe' }, { _id: '2', name: 'Jane Smith' }]}
 *   value="1"
 *   handleChange={handleSelectChange}
 * />
 *
 * @example
 * // Renders a multiple select dropdown with enum values
 * <InputSelect
 *   multiple={true}
 *   name="genres"
 *   label="Select Genres"
 *   prompt="Choose genres"
 *   items={['fiction', 'non-fiction', 'science']}
 *   enum={{ fiction: 'Fiction', 'non-fiction': 'Non-Fiction', science: 'Science' }}
 *   value={['fiction', 'science']}
 *   handleChange={handleSelectChange}
 * />
 */
export function InputSelect(props) {
  const multiple = props.multiple;
  const required = props.required || false;

  // Flag for empty value
  const emptySelected = multiple ? props.value?.length === 0 : !props.value;

  // Flag for whether items are objects or enum values
  const objectItems = props.enum ? false : true;

  return (
    <div className="form-group">
      <label>{props.label}:</label>
      <select
        required={required}
        className={`browser-default form-select w-75 ${props.isSubmitted ? (props.error ? "is-invalid" : "is-valid") : ""}`}
        multiple={multiple}
        name={props.name}
        onChange={props.handleChange}
        value={props.value}
      >
        {required ? (
          /* Disabled empty value for required fields */
          <option disabled value={emptySelected}>
            {props.prompt}
          </option>
        ) : (
          /* Option for empty value allowed */
          <option key={0} value={emptySelected}>
            ({props.prompt})
          </option>
        )}

        {objectItems
          ? /* Render options as objects (e.g., database records) */
          props.items.map((item, index) => (
            <option key={required ? index : index + 1} value={item._id}>
              {item.name}
            </option>
          ))
          : /* Render options as enum values */
          props.items.map((item, index) => (
            <option key={required ? index : index + 1} value={item}>
              {props.enum[item]}
            </option>
          ))}
      </select>

      {/* Display error message if exists */}
      {props.error && <small id={props.validationFeedback} className="ms-2 invalid-feedback">{props.error}</small>}
    </div >
  );
}