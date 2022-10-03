import axios from "axios";
import Button from "components/Button";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { buttonStyles } from "lib/styles";
import ProfileInput from "./ProfileInput";

function UserProfile({ userId, uid, userProfile, setUserProfile }) {
  const [isEditable, SetIsEditable] = useState(false);
  const [isFollowing, setIsFollowing] = useState(false);
  const navigate = useNavigate();
  const nicknameRef = useRef();
  const { emoji, nickname, introduction } = userProfile;

  const {
    smTextBlackButton,
    smWhiteButton,
    smGrayButton,
    smLightGreenButton,
    refreshButton,
  } = buttonStyles;

  useEffect(() => {
    nicknameRef.current.focus();
  }, [isEditable]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserProfile((prev) => ({ ...prev, [name]: value }));
  };

  const handleEditButton = () => {
    if (isEditable) {
      axios
        .patch(`/api/v1/users/${uid}`, { nickname, introduction })
        .then((res) => setUserProfile((prev) => ({ ...prev, ...res.data })))
        .then(() => SetIsEditable((prev) => !prev))
        .catch((err) => console.error(err.message));
    } else {
      SetIsEditable((prev) => !prev);
    }
  };

  const handleFollowButton = () => {
    if (userId) {
      setIsFollowing((prev) => !prev);
    }
  };

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">
        {uid === userId ? "내 정보 관리" : `${nickname}님의 정보`}
      </div>
      <div className="flex justify-between mb-3">
        <div className="bg-white rounded-full w-24 h-24 p-6 relative">
          <span className="text-5xl">{emoji}</span>
          {uid === userId && <Button icon="refresh" styles={refreshButton} />}
        </div>
        <div className="flex">
          <div className="flex flex-col justify-between mr-3">
            <div className="text-right leading-none whitespace-nowrap">
              <Button
                num="1111"
                name=" 팔로워"
                styles={smTextBlackButton}
                handleButtonClick={() => navigate(`/user/${uid}/followers`)}
              />
            </div>
          </div>
          <div className="flex flex-col justify-between">
            <div className="text-right leading-none w-[96.133px]">
              <Button
                num="1143"
                name=" 팔로잉"
                styles={smTextBlackButton}
                handleButtonClick={() => navigate(`/user/${uid}/followings`)}
              />
            </div>
            {uid === userId ? (
              <Button
                name={isEditable ? "수정 완료" : "프로필 수정"}
                styles={isEditable ? smGrayButton : smWhiteButton}
                handleButtonClick={handleEditButton}
              />
            ) : (
              <Button
                name={isFollowing ? "언팔로우" : "팔로우"}
                styles={isFollowing ? smGrayButton : smLightGreenButton}
                handleButtonClick={handleFollowButton}
              />
            )}
          </div>
        </div>
      </div>
      <div className="flex flex-col">
        <ProfileInput
          nicknameRef={nicknameRef}
          name="nickname"
          maxLength="10"
          isEditable={isEditable}
          value={nickname}
          handleInputChange={handleInputChange}
          styles="w-[160px] mb-3"
        />
        <ProfileInput
          name="introduction"
          maxLength="20"
          isEditable={isEditable}
          value={introduction}
          handleInputChange={handleInputChange}
          styles="w-[280px]"
        />
      </div>
    </div>
  );
}

export default UserProfile;
