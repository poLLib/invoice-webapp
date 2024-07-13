import { useNavigate } from "react-router-dom";

export function BackButton ({style}) {
    const navigate = useNavigate()

    return (
        <>
        <button className={style} onClick={() => navigate(-1)}>Zpět</button></>
    );
};