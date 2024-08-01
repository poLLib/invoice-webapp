import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css'

/**
 * PersonTable component renders a table of person records.
 * Each record includes options to view, edit, or delete the person.
 * 
 * @param {Array} props.itemsPerPage - Array of person objects to display in the table.
 * @param {Function} props.deletePerson - Function to call when deleting a person.
 * @returns {JSX.Element} A table displaying the person records with action buttons.
 */
export function PersonTable({ itemsPerPage, deletePerson }) {

    return (
        <div>
            <table className="table table-striped">
                <thead className="fs-5">
                    <th>NÁZEV</th>
                    <th>IČO</th>
                    <th>E-mail</th>
                    <th colSpan={3}></th>
                </thead>
                <tbody>
                    {itemsPerPage.map((item) => (
                        <tr key={item._id}>
                            <td className="fw-bold">{item.name}</td>
                            <td>{item.identificationNumber}</td>
                            <td>{item.mail}</td>
                            <td className="text-end">
                                <div className="btn-group">
                                    <Link
                                        to={"/persons/show/" + item._id}
                                        className="btn btn-sm btn-info"
                                    >
                                        Zobrazit
                                    </Link>
                                    <Link
                                        to={"/persons/edit/" + item._id}
                                        className="btn btn-sm btn-warning"
                                    >
                                        Upravit
                                    </Link>
                                    <button
                                        onClick={() => deletePerson(item._id)}
                                        className="btn btn-sm btn-danger"
                                    >
                                        Odstranit
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div>
    );
}