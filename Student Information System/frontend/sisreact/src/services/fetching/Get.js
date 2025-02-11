import getAuthorization from "../Authorization.js";

export default async function get(url) {
    url = import.meta.env.VITE_BASE_URL + url;
    const allHeaders = new Headers(getAuthorization());
    allHeaders.append("Content-Type", "application/json");
    const res = await fetch(url, {
        method: "GET",
        headers: allHeaders,
    });

    if(res.status === 200) {
        const resJson = await res.json();
        return { data : resJson , status : res.ok };
    } else {
        return { data : {}, status: res.body };
    }
}