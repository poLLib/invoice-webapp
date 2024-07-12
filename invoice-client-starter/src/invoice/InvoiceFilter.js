import InputField from "../components/InputField"
import InputSelect from "../components/InputSelect";

function InvoiceFilter(props) {
    function handleChange(e) {
        return props.handleChange(e);
    };

    function handleSubmit(e) {
        return props.handleSubmit(e);
    };

    function handleInput(e) {
        return props.handleChange(e);
    }

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
                        label="Minimální cena"
                        prompt="Zadejte cenu"
                        value={filter.minPice}
                    />
                </div>

                <div className="col">
                    <InputField
                        type="number"
                        min="0"
                        name="maxPrice"
                        handleChange={handleChange}
                        label="Maximální cena"
                        prompt="Zadejte cenu"
                        value={filter.maxPrice}
                    />
                </div>

                <div className="col">
                    <InputField
                        type="text"
                        name="product"
                        handleChange={handleInput}
                        label="Vyhledat položku"
                        prompt="Zadejte název produktu"
                        value={filter.product}
                    />
                </div>

                <div className="row">
                    <div className="col">
                        <InputSelect
                            name="sellerId"
                            handleChange={handleChange}
                            label="Dodavatel"
                            prompt="Vyberte dodavatele"
                            value={filter.sellerId}
                            items={props.sellers}
                        />
                    </div>

                    <div className="col">
                        <InputSelect
                            name="buyerId"
                            handleChange={handleChange}
                            label="Odběratel"
                            prompt="Vyberte odběratele"
                            value={filter.buyerId}
                            items={props.buyers}
                        />
                    </div>

                    <div className="col">
                        <InputField
                            type="number"
                            name="limit"
                            handleChange={handleChange}
                            label="Limit zobrazených faktur"
                            min="1"
                            prompt="10"
                            value={filter.limit}
                        />
                    </div>

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
