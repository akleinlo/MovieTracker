import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";

export default function App() {
    const [movie, setMovie] = useState<string>("")
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

    useEffect(() => {
        axios.get("/api/movie")
            .then(response => {
                console.log("Antwort vom Backend:", response.data.value);
                setMovie(response.data.value);
            })
            .catch(error => {
                console.error("Keine Filme gefunden..", error)
            });
    }, []);

    return (
        <>
            <h1>Backend sagt:</h1>
            <p>{message}</p>
            <p>aktueller Film</p>
            <p>{movie}</p>
        </>
    )
}
