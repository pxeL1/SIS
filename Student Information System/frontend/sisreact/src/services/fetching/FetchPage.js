import get from "./Get.js";

export default async function fetchPage(url, page, size) {
    url += "filter?page=" + page + "&size=" + size;
    const res = get(url);

    if(res.status){
        return res.data;
    } else {
        return res.status;
    }
}