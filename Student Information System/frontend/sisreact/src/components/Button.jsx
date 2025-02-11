export default function Button({onClick, text}) {
    return (
        <>

            <button className={"bg-slate-800 text-white w-24 h-10 rounded hover:bg-slate-600"} onClick={onClick}>
                {text}
            </button>
        </>
    )
}