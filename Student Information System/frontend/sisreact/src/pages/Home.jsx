import {useContext, useEffect} from "react";
import {UserContext} from "../contexts/UserContext.js";
import {Info} from "../assets/Icons.jsx";
import {NewsCard} from "../components/NewsCard.jsx";
import ExamCard from "../components/ExamCard.jsx";

export function Home() {
    const userContext = useContext(UserContext);

    useEffect(() => {

    }, []);

    return (
        <>
            <div className="flex h-full flex-col pl-10 pr-10 pt-5">
                <div className="flex justify-between text-4xl my-7 font-bold">
                    <h1>Welcome, {userContext?.email.replace("@sis.com", "")}!</h1>
                    <h1 className="header">STUDENT INFORMATION SYSTEM</h1>
                </div>
                <hr/>
                <div className="flex flex-grow w-full h-full">
                    <div className="flex flex-col rounded bg-gray-300 p-5 w-1/2 h-4/5 mt-[29px] shadow-md shadow-gray-500">
                        <h2 className="flex text-2xl mb-5"><span className="mr-2"><Info/></span> News:</h2>
                        <div className="flex flex-grow flex-col">
                            <NewsCard/>
                            <NewsCard/>
                            <NewsCard/>
                            <NewsCard/>
                        </div>
                    </div>
                    <div className="flex flex-grow mt-[29px]">
                        <div className="flex flex-col flex-grow px-5 w-1/2 h-4/5">
                            <h2 className="flex text-2xl mb-2">Upcoming exams:</h2>
                            <hr/>
                            <ExamCard/>
                            <ExamCard/>
                            <ExamCard/>
                        </div>
                        <div className="flex flex-col flex-grow rounded bg-gray-300 p-5 w-1/2 h-4/5 shadow-md shadow-gray-500 hover:bg-gray-200 transition-all duration-300">
                            <h2 className="flex text-2xl mb-2">New messages:</h2>
                            <hr/>
                            <div className="mt-2">Work in progress</div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}