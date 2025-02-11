import {useEffect, useState} from "react";
import get from "../services/fetching/Get.js";

export default function useFetchData(url) {
    const [data, setData] = useState({});
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        setIsLoading(true);
        const fetchData = async () => {
            try {
                const res = await get(url);

                if (res.status) {
                    setData(res.data);
                } else {
                    setError(true);
                }
            } catch (error) {
                console.error(error);
                setError(true);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, [url]);

    return { data, isLoading, error };
}