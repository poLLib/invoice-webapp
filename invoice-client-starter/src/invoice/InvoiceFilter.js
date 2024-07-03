import InputField from "../components/InputField"


function InvoiceFilter (props) {
    function handleChange(e) {
        return props.handleChange(e);
    };

    function handleSubmit(e){
        return props.handleSubmit(e);
    };

    const filter = props.filter;

    return (
        <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col">

                    <InputField
                        type="number"
                        min="0"
                        name="minPrice"
                        handleChange={handleChange}
                        label="Minimální částka"
                        prompt="částka"
                        value={filter.minPice ? filter.minPrice : ''}
                    />
                </div>

                <div className="col">
                    <InputField
                        type="number"
                        min="0"
                        name="maxPrice"
                        handleChange={handleChange}
                        label="Maximální částka"
                        prompt="částka"
                        value={filter.maxPrice ? filter.maxPrice : ''}
                    />
                </div>

            </div>

            <div className="row">
                <div className="col">
                    <input
                        type="submit"
                        className="btn btn-secondary float-right mt-2"
                        value={props.confirm}
                    />
                </div>
            </div>
        </form>
    );
};

export default InvoiceFilter;
