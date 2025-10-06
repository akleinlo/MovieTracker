import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";
import ViewAllMovies from "./components/ViewAllMovies.tsx";

export default function App() {
    const [message, setMessage] = useState<string>("")

    useEffect(() => {
        axios.get("/api/hello")
            .then(response => {
                console.log("Antwort vom Backend:", response.data.value);
                setMessage(response.data);
            })
            .catch(error => {
                console.error("Fehler beim Laden", error);
            });
    }, []);

    return (
        <>
            <h1>Backend sagt:</h1>
            <p>{message}</p>
            <ViewAllMovies />
        </>
    )
}