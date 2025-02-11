import post from "./Post.js";

export default async function fetchPageFiltered(url, page, size, filterList) {
    url += "filter?page=" + page + "&size=" + size;
    const res = await post(url, filterList);

    if(res.status){
        return res.data;
    } else {
        return res.status;
    }
}