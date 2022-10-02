import { buttonStyles } from "lib/styles";
import { getConvertedDate } from "lib/utils/getConvertedDate";
import { Link } from "react-router-dom";
import Button from "./Button";

function Review({
  userId,
  reviewId,
  emoji,
  nickname,
  score,
  content,
  createdAt,
  modifiedAt,
}) {
  const { mdTextGrayButton } = buttonStyles;
  const convertedDate = getConvertedDate(createdAt);

  return (
    <li
      key={reviewId}
      className="flex flex-col bg-white rounded-lg p-3 text-sm"
    >
      <div>
        <Link to={`/user/${userId}`}>
          <span>{emoji}</span>
          <span className="mx-2">{nickname}</span>
        </Link>
        <span className="text-wagmiLightGreen cursor-default">
          {"★".repeat(score)}
        </span>
      </div>
      <div className="py-2">{content}</div>
      <div className="flex justify-between text-xs">
        <div className="py-2">
          <span>{convertedDate}</span>
          {createdAt !== modifiedAt && (
            <span className="text-textGray ml-1">(수정됨)</span>
          )}
        </div>
        <div>
          <Button styles={mdTextGrayButton} name="수정" />
          <span className="border" />
          <Button styles={mdTextGrayButton} name="삭제" />
        </div>
      </div>
    </li>
  );
}

export default Review;
