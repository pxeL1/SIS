import { useLocation } from "react-router-dom";

export function Course() {
    const location = useLocation();
    const enrollment  = location.state;

    return (
        <>
            <div className="flex flex-col px-40 pt-28">
                <div className="flex flex-col font-bold text-4xl mb-5">
                    <h1 className={"mb-5"}>Course: {enrollment.course.name}</h1>
                    <hr/>
                </div>
                <div className={"flex items-center mb-7"}>
                    <div className={"text-2xl mr-5"}>Professor: </div>
                    <div className={"text-2xl group hover:text-blue-500"}>
                        {enrollment.course.professor.firstName} {enrollment.course.professor.lastName}
                        <span className={"bg-gray-200 text-xs transition-all duration-100 p-1 m-2 absolute scale-0 group-hover:scale-100 shadow-md rounded-md w-auto origin-left"}>
                            {enrollment.course.professor.email}
                        </span>
                    </div>
                </div>
                <div className={"flex flex-col"}>
                    <div className={"text-2xl mb-2"}>Description: </div>
                    <div className={"bg-gray-200 w-[1200px] rounded-md px-3 py-2 h-60"}>
                        {enrollment.course.description}
                    </div>
                </div>
                <div className={"flex mt-52"}>
                    <div className={"flex flex-col mr-10"}>
                        <div className={"font-bold text-xl"}>Points:</div>
                        <div className={"h-20 w-20 border-2 rounded flex justify-center items-center text-4xl"}>{enrollment.points}</div>
                    </div>

                    {enrollment.grade == null ? "" :
                        <div className={"flex flex-col"}>
                            <div className={"font-bold text-xl"}>Grade:</div>
                            <div
                                className={"h-20 w-20 border-2 rounded flex justify-center items-center text-4xl"}>{enrollment.grade}</div>
                        </div>
                    }
                </div>
            </div>
        </>
    )
}