function Card({
  id,
  emoji,
  name,
  option = null,
  style,
  isOneLine = false,
  isFull = false,
}) {
  return (
    <li className={`${style} text-sm`} key={id}>
      {isOneLine ? (
        <div
          className={`bg-white p-3 rounded-lg ${
            isFull ? null : "inline-block"
          }`}
        >
          <span>{emoji}</span>
          <span className="ml-2">{name}</span>
          <span className="float-right">{option}</span>
        </div>
      ) : (
        <>
          <span className="text-2xl">{emoji}</span>
          <div className="py-[4px] text-center">{name}</div>
          <span className="text-xs text-textGray">{option}</span>
        </>
      )}
    </li>
  );
}

export default Card;
