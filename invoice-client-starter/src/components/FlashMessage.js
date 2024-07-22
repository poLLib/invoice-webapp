import React from "react";

/**
 * FlashMessage component displays a styled alert message.
 *
 * @param {string} props.theme - The theme of the alert (e.g., 'success', 'danger', 'warning', etc.).
 * @param {string} props.text - The text to display inside the alert.
 * @returns {JSX.Element} A div element containing the alert message.
 *
 * @example
 * // Renders a success alert with the text 'Operation successful!'
 * <FlashMessage theme="success" text="Operation successful!" />
 */
export function FlashMessage({ theme, text }) {

  return (
    <div className={"alert alert-" + theme}>{text}</div>
  )
}