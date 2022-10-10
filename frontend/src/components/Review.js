import axios from "axios";
import { buttonStyles } from "lib/styles";
import { getConvertedDate } from "lib/utils/getConvertedDate";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import Button from "./common/Button";
import { checkSession } from "lib/utils/checkSession";

function Review({
  userId,
  reviewId,
  emoji,
  nickname,
  score,
  content,
  createdAt,
  modifiedAt,
  storeReviews,
  setStoreReviews,
}) {
  const userInfo = useSelector((state) => state.userInfo.value);
  const dispatch = useDispatch();
  const { mdTextGrayButton } = buttonStyles;
  const convertedDate = getConvertedDate(createdAt);

  const handleDeleteButtonClick = () => {
    const notDeletedReviews = storeReviews.filter(
      (review) => review.reviewId !== reviewId
    );

    const nextAPICall = () => {
      if (window.confirm("리뷰를 삭제하시겠습니까?")) {
        axios
          .delete(`/api/v1/stores/reviews/${reviewId}`)
          .then(() => setStoreReviews(notDeletedReviews))
          .catch((err) => console.error(err.message));
      }
    };

    checkSession(dispatch, nextAPICall);
  };

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
        {parseInt(userInfo.userId) === userId && (
          <div>
            <Button styles={mdTextGrayButton} name="수정" />
            <span className="border" />
            <Button
              styles={mdTextGrayButton}
              name="삭제"
              handleButtonClick={handleDeleteButtonClick}
            />
          </div>
        )}
      </div>
    </li>
  );
}

export default Review;
