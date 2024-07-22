/**
 * InputCheck component renders a checkbox or radio input field with a label.
 *
 * This component supports only `checkbox` and `radio` input types.
 *
 * @param {string} props.type - The type of the input field. Should be either 'checkbox' or 'radio'.
 * @param {string} props.name - The name attribute for the input element.
 * @param {string} [props.value] - The value attribute for the input element.
 * @param {boolean} [props.checked] - The checked state of the input element.
 * @param {Function} props.handleChange - The function to call when the input value changes.
 * @param {string} props.label - The label text associated with the input field.
 * @returns {JSX.Element|null} A form group containing the input and label if type is valid; otherwise, null.
 *
 * @example
 * // Renders a checkbox with the label 'Accept Terms'
 * <InputCheck
 *   type="checkbox"
 *   name="acceptTerms"
 *   checked={true}
 *   handleChange={handleCheckboxChange}
 *   label="Accept Terms"
 * />
 */

export function InputCheck(props) {
  // Supported input types
  const INPUTS = ["checkbox", "radio"];

  // Validate input type
  const type = props.type.toLowerCase();
  const checked = props.checked || "";

  // Return null if the type is not supported
  if (!INPUTS.includes(type)) {
    return null;
  }

  return (
    <div className="form-group form-check">
      <label className="form-check-label">
        {/* Render the input field with the current type */}
        <input
          type={props.type}
          className="form-check-input"
          name={props.name}
          value={props.value}
          checked={checked}
          onChange={props.handleChange}
        />{" "}
        {props.label}
      </label>
    </div>
  );
}