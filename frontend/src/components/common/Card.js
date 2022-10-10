import { Link } from "react-router-dom";

function Card({
  id,
  emoji,
  name,
  option = null,
  option2 = null,
  styles,
  isOneLine = false,
  isFull = false,
  path,
}) {
  return (
    <Link to={path} key={id}>
      <li
        className={`${styles} rounded-lg text-sm hover:shadow-md active:shadow-md`}
      >
        {isOneLine ? (
          <div className={`bg-white p-3 ${isFull ? null : "inline-block"}`}>
            <span>{emoji}</span>
            <span className="ml-2">{name}</span>
            <span className="float-right">
              {option2 === "♥️" ? (
                <span className="text-heartRed mr-2">{option2}</span>
              ) : null}
              <span>{option}</span>
            </span>
          </div>
        ) : (
          <>
            <span className="text-2xl">{emoji}</span>
            <div className="py-[4px] text-center">{name}</div>
            <span className="text-xs text-textGray">{option}</span>
          </>
        )}
      </li>
    </Link>
  );
}

export default Card;
