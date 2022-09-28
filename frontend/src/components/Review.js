import { buttonStyles } from "lib/styles";
import Button from "./Button";

function Review({
  //   userId,
  reviewId,
  emoji,
  nickname,
  score,
  content,
  createdAt,
}) {
  const { mdTextGrayButton } = buttonStyles;

  return (
    <li
      key={reviewId}
      className="flex flex-col bg-white rounded-lg p-3 text-sm"
    >
      <div>
        <span>{emoji}</span>
        <span className="mx-2">{nickname}</span>
        <span>{score}</span>
      </div>
      <div className="py-2">{content}</div>
      <div className="flex justify-between text-xs">
        <div className="py-2">{createdAt}</div>
        <div>
          <Button style={mdTextGrayButton} name="수정" />
          <span className="border" />
          <Button style={mdTextGrayButton} name="삭제" />
        </div>
      </div>
    </li>
  );
}

export default Review;
