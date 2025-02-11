export default function ExamCard() {
    return (
        <>
            <div className={"flex flex-col my-2 hover:text-lg hover:text-blue-500 transition-all duration-300"}>
                <div className="font-bold">Course - dd.mm.yyyy. - hh:mm</div>
                <div>Exam type</div>
            </div>
        </>
    )
}