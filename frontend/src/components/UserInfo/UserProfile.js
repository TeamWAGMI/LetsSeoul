import axios from "axios";
import Button from "components/Button";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { buttonStyles } from "lib/styles";
import ProfileInput from "./ProfileInput";
import { useDispatch } from "react-redux";
import { getUserInfo } from "slice/userInfoSlice";
import { checkSession } from "lib/utils/checkSession";

function UserProfile({ userId, uid, userProfile, setUserProfile }) {
  const [isEditable, SetIsEditable] = useState(false);
  const [isFollowing, setIsFollowing] = useState(false);
  const [followNum, setFollowNum] = useState({
    numberOfFollower: 0,
    numberOfFollowing: 0,
  });
  const navigate = useNavigate();
  const nicknameRef = useRef();
  const { emoji, nickname, introduce } = userProfile;
  const dispatch = useDispatch();

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

  useEffect(() => {
    axios
      .get(`/api/v1/follows/${uid}/count`)
      .then((res) => setFollowNum((prev) => ({ ...prev, ...res.data })));
  }, [uid, isFollowing]);

  useEffect(() => {
    if (userId) {
      // 비로그인 유저가 유저 페이지 접속하자마자 로그인 모달이 뜨지 않도록 설정
      const nextAPICall = () => {
        axios
          .get(`/api/v1/follows/${uid}/check`)
          .then((res) => setIsFollowing(res.data.isFollowing));
      };
      checkSession(dispatch, nextAPICall);
    } else {
      // 비로그인 유저 디폴트 설정
      setIsFollowing(false);
    }
  }, [uid, dispatch, userId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserProfile((prev) => ({ ...prev, [name]: value }));
  };

  const handleEditButton = () => {
    const nextAPICall = () => {
      if (isEditable) {
        axios
          .patch(`/api/v1/users`, { nickname, introduce })
          .then((res) => {
            setUserProfile((prev) => ({ ...prev, ...res.data }));
            dispatch(
              getUserInfo({ userId, userEmoji: emoji, userNickname: nickname })
            );
          })
          .then(() => SetIsEditable((prev) => !prev))
          .catch((err) => console.error(err.message));
      } else {
        SetIsEditable((prev) => !prev);
      }
    };
    checkSession(dispatch, nextAPICall);
  };

  const handleFollowButton = () => {
    const nextAPICall = () => {
      if (isFollowing) {
        axios.delete(`/api/v1/follows/${uid}`).then(() => {
          setIsFollowing(false);
        });
      } else {
        axios.post(`/api/v1/follows/${uid}`).then(() => setIsFollowing(true));
      }
    };
    checkSession(dispatch, nextAPICall);
  };

  const handleEmojiRefresh = () => {
    const nextAPICall = () => {
      axios
        .patch("/api/v1/users/emoji")
        .then((res) => {
          setUserProfile((prev) => ({ ...prev, emoji: res.data.emoji }));
          dispatch(
            getUserInfo({
              userId,
              userEmoji: res.data.emoji,
              userNickname: nickname,
            })
          );
        })
        .catch((err) => console.error(err.message));
    };

    checkSession(dispatch, nextAPICall);
  };

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">
        {uid === userId ? "내 정보 관리" : `${nickname}님의 정보`}
      </div>
      <div className="flex justify-between mb-3">
        <div className="bg-white rounded-full w-24 h-24 p-6 relative">
          <span className="text-5xl">{emoji}</span>
          {uid === userId && (
            <Button
              icon="refresh"
              styles={refreshButton}
              handleButtonClick={handleEmojiRefresh}
            />
          )}
        </div>
        <div className="flex">
          <div className="flex flex-col justify-between mr-3">
            <div className="text-right leading-none whitespace-nowrap">
              <Button
                num={followNum.numberOfFollower}
                name=" 팔로워"
                styles={smTextBlackButton}
                handleButtonClick={() => navigate(`/user/${uid}/followers`)}
              />
            </div>
          </div>
          <div className="flex flex-col justify-between">
            <div className="text-right leading-none w-[96.133px]">
              <Button
                num={followNum.numberOfFollowing}
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
          maxLength="15"
          isEditable={isEditable}
          value={nickname}
          handleInputChange={handleInputChange}
          styles="w-[215px] mb-3"
        />
        <ProfileInput
          name="introduce"
          maxLength="20"
          isEditable={isEditable}
          value={introduce}
          handleInputChange={handleInputChange}
          styles="w-[280px]"
        />
      </div>
    </div>
  );
}

export default UserProfile;
