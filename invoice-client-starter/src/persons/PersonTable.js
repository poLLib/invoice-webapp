import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import 'bootstrap/dist/css/bootstrap.css'

/**
 * PersonTable component renders a table of person records.
 * Each record includes options to view, edit, or delete the person.
 *
 * @param {Array} props.itemsPerPage - Array of person objects to display in the table.
 * @param {Function} props.deletePerson - Function to call when deleting a person.
 * @returns {JSX.Element} A table displaying the person records with action buttons.
 */
export function PersonTable({ itemsPerPage, deletePerson, isAdmin }) {
    const { t } = useTranslation();

    return (
        <div>
            <table className="table table-striped">
                <thead className="fs-5">
                    <th>{t('persons.table.name')}</th>
                    <th>{t('persons.table.ico')}</th>
                    <th>{t('persons.table.email')}</th>
                    <th colSpan={3}></th>
                </thead>
                <tbody>
                    {itemsPerPage.map((item) => (
                        <tr key={item.id}>
                            <td className="fw-bold">{item.name}</td>
                            <td>{item.identificationNumber}</td>
                            <td>{item.mail}</td>
                            <td className="text-end">
                                <div className="btn-group">
                                    <Link
                                        to={"/persons/show/" + item.id}
                                        className="btn btn-sm btn-info"
                                    >
                                        {t('common.show')}
                                    </Link>
                                    <Link
                                        to={"/persons/edit/" + item.id}
                                        className="btn btn-sm btn-warning"
                                    >
                                        {t('common.edit')}
                                    </Link>
                                    {isAdmin && (
                                        <button
                                            onClick={() => deletePerson(item.id)}
                                            className="btn btn-sm btn-danger"
                                        >
                                            {t('common.delete')}
                                        </button>
                                    )}
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div>
    );
}
