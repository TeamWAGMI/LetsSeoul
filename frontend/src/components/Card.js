function Card({ id, emoji, name, option = null, style, isOneLine = false }) {
  return (
    <li className={`${style} text-sm`} key={id}>
      <span className={isOneLine ? null : "text-2xl"}>{emoji}</span>
      {isOneLine ? (
        <span className="ml-2">{name}</span>
      ) : (
        <div className="py-[4px]">{name}</div>
      )}
      <span className={isOneLine ? "float-right" : "text-xs text-textGray"}>
        {option}
      </span>
    </li>
  );
}

export default Card;
