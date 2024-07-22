import { InputField } from "../components/InputField";
import { InputSelect } from "../components/InputSelect";

/**
 * InvoiceFilter component provides a form for filtering invoices based on various criteria.
 * It includes fields for price range, product search, and selectors for sellers and buyers.
 * 
 * @param {Object} props - The properties passed to the component.
 * @param {Function} props.handleChange - Function to handle input changes.
 * @param {Function} props.handleSubmit - Function to handle form submission.
 * @param {Function} props.handleReset - Function to handle resetting the filter form.
 * @param {Object} props.filter - The current filter values.
 * @param {Array} props.sellers - List of sellers for the select input.
 * @param {Array} props.buyers - List of buyers for the select input.
 * @param {string} props.confirm - Text for the submit button.
 * @param {string} props.reset - Text for the reset button.
 * 
 * @returns {JSX.Element} A form element with input fields and buttons for filtering invoices.
 */
export function InvoiceFilter(props) {

    // Handle input change events
    function handleChange(e) {
        return props.handleChange(e);
    };

    // Handle form submission
    function handleSubmit(e) {
        return props.handleSubmit(e);
    };

    // Handle input change specifically for the product search field
    function handleInput(e) {
        return props.handleChange(e);
    }

    // Handle reset button click
    function handleReset(e) {
        return props.handleReset(e);
    }

    // Destructure filter values from props
    const filter = props.filter;

    return (
        <form onSubmit={handleSubmit}>

            <div className="row">
                {/* Minimum Price Input Field */}
                <div className="col">
                    <InputField
                        type="number"
                        min="0"
                        name="minPrice"
                        handleChange={handleChange}
                        label="Minimální cena"
                        prompt="Zadejte cenu"
                        value={filter.minPrice ? filter.minPrice : ""}
                    />
                </div>

                {/* Maximum Price Input Field */}
                <div className="col">
                    <InputField
                        type="number"
                        min="0"
                        name="maxPrice"
                        handleChange={handleChange}
                        label="Maximální cena"
                        prompt="Zadejte cenu"
                        value={filter.maxPrice ? filter.maxPrice : ""}
                    />
                </div>

                {/* Product Search Input Field */}
                <div className="col">
                    <InputField
                        type="text"
                        name="product"
                        handleChange={handleInput}
                        label="Vyhledat položku"
                        prompt="Zadejte název produktu"
                        value={filter.product ? filter.product : ""}
                    />
                </div>

                <div className="row">
                    {/* Seller Select Field */}
                    <div className="col">
                        <InputSelect
                            name="sellerId"
                            handleChange={handleChange}
                            label="Dodavatel"
                            prompt="Vyberte dodavatele"
                            value={filter.sellerId ? filter.sellerId : ""}
                            items={props.sellers}
                        />
                    </div>

                    {/* Buyer Select Field */}
                    <div className="col">
                        <InputSelect
                            name="buyerId"
                            handleChange={handleChange}
                            label="Odběratel"
                            prompt="Vyberte odběratele"
                            value={filter.buyerId ? filter.buyerId : ""}
                            items={props.buyers}
                        />
                    </div>

                    {/* Limit of Invoices Displayed Input Field */}
                    <div className="col">
                        <InputField
                            type="number"
                            name="limit"
                            handleChange={handleChange}
                            label="Limit zobrazených faktur"
                            min="1"
                            prompt="10"
                            value={filter.limit ? filter.limit : ""}
                        />
                    </div>

                </div>

            </div>

            <div className="row">
                <div className="col">

                    {/* Submit Button */}
                    <input
                        type="submit"
                        className="btn btn-secondary float-right mt-2"
                        value={props.confirm}
                    />
                    
                    {/* Reset Button */}
                    <button
                        type="button"
                        className="btn btn-danger float-right mt-2 ms-3"
                        value={props.reset}
                        onClick={handleReset}
                    >RESET</button>
                </div>
            </div>
        </form>
    );
}
