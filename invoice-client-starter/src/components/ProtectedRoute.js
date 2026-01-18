import { Navigate } from "react-router-dom";
import { useSession } from "../contexts/session";

export function ProtectedRoute({ children }) {
    const { session } = useSession();

    if (session.status === "loading") {
        return (
            <div className="d-flex justify-content-center mt-5">
                <div className="spinner-border" role="status">
                    <span className="visually-hidden">Načítání...</span>
                </div>
            </div>
        );
    }

    if (session.status === "unauthenticated") {
        return <Navigate to="/login" replace />;
    }

    return children;
}
