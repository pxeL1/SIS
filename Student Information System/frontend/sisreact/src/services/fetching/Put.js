import getAuthorization from "../Authorization.js";

export default async function put(url, putRequest) {
    url = import.meta.env.VITE_BASE_URL + url;
    const allHeaders = new Headers(getAuthorization());
    allHeaders.append("Content-Type", "application/json");
    const res = await fetch(url, {
        method: "PUT",
        headers: allHeaders,
        body: JSON.stringify(putRequest),
    });

    if(res.status === 200) {
        return { status : res.ok };
    } else {
        return { status: res.statusText };
    }
}