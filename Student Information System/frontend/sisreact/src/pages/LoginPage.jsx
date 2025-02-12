import post from "../services/fetching/Post.js";
import {useState} from "react";
import Button from "../components/Button.jsx";


export function LoginPage({handleLogin, badLogin, setBadLogin}) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit() {
        const url = "auth/login";

        const loginRequest = {
            email: email,
            password: password
        }

        const res = await post(url, loginRequest);

        if(!res.status) {
            setBadLogin(true);
        } else {
            setBadLogin(false);
            handleLogin(res.data.user, res.data.token);
        }
    }

    return(
        <>
            <div className="flex flex-grow flex-col px-20 py-28 justify-end bg-sisbackground bg-cover">
                <div className="flex flex-col h-80 w-[450px] p-8">
                    <label className="my-2 text-white">Email</label>
                    <input type="email" className="h-8 opacity-65 px-2 text-xs mb-4" required={true} onChange={(e) => setEmail(e.target.value)} />
                    <label className="my-2 text-white">Password</label>
                    <input type="password" className="h-8 opacity-65 px-2 text-xs mb-8" required={true} onChange={(e) => setPassword(e.target.value)} />
                    {badLogin && <label className="text-rose-500 ">Invalid email or password</label>}
                    <Button onClick={handleSubmit} text={"Log in"} />
                </div>
            </div>
        </>
    )
}