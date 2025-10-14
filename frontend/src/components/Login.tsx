import axios from "axios";
import {useEffect} from "react";
import styles from "../css/Login.module.css";
import btnStyles from "../css/Button.module.css";

export default function Login() {
    const host: string = window.location.host === "localhost:5173" ? "http://localhost:8080" : window.location.origin

    const handleLogin = () => {
        window.open(host + "/oauth2/authorization/github", "_self");
    };

    const loadUser= () => {
        axios.get("/api/auth/me")
            .then(response => console.log(response.data))
    }
    useEffect(() => {
        loadUser();
    }, []);

    return (
        <div className={styles.container}>
            <button className={btnStyles.button} onClick={handleLogin}>Login</button>
        </div>
    )
}