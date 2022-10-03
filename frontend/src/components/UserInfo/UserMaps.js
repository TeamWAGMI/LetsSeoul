import Button from "components/Button";
import { useNavigate } from "react-router-dom";
import { buttonStyles } from "lib/styles";

function UserMaps({ userId, uid, nickname }) {
  const navigate = useNavigate();
  const { mdGreenButton, mdWhiteGreenButton } = buttonStyles;

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">
        {uid === userId ? "나의 지도 관리" : `${nickname}님의 지도`}
      </div>
      <div className="grid gap-2">
        <Button
          isFull={true}
          styles={mdGreenButton}
          name={
            uid === userId
              ? "내가 추천한 장소 모아보기"
              : `${nickname}님이 추천한 장소 모아보기`
          }
          emoji="👍"
          handleButtonClick={() => navigate(`/user/${uid}/pick`)}
        />
        <Button
          isFull={true}
          styles={mdWhiteGreenButton}
          name={
            uid === userId
              ? "내가 찜한 장소 모아보기"
              : `${nickname}님이 찜한 장소 모아보기`
          }
          emoji="🥰"
          handleButtonClick={() => navigate(`/user/${uid}/wish`)}
        />
      </div>
    </div>
  );
}

export default UserMaps;
