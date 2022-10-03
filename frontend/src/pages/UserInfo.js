import Button from "components/Button";
import Card from "components/Card";
import { useEffect, useRef, useState } from "react";
import { userPicktheme } from "static/dummyData";
import { buttonStyles } from "lib/styles";
import { useNavigate, useParams, useOutletContext } from "react-router-dom";
import axios from "axios";
import { scrollToTop } from "lib/utils/scrollToTop";

function UserInfo() {
  const [userProfile, setUserProfile] = useState({
    emoji: "",
    nickname: "",
    introduction: "",
  });
  const { emoji, nickname, introduction } = userProfile;

  const [isEditable, SetIsEditable] = useState(false);
  const [isFollowing, setIsFollowing] = useState(false);
  const navigate = useNavigate();
  const { uid } = useParams();
  const nicknameRef = useRef();
  const { userId } = useOutletContext();

  const {
    smTextBlackButton,
    smWhiteButton,
    smGrayButton,
    smLightGreenButton,
    mdGreenButton,
    mdWhiteGreenButton,
    refreshButton,
  } = buttonStyles;

  useEffect(() => {
    scrollToTop();
  }, []);

  useEffect(() => {
    axios
      .get(`/api/v1/users/${uid}`)
      .then((res) => setUserProfile((prev) => ({ ...prev, ...res.data })));
  }, [uid]);

  useEffect(() => {
    nicknameRef.current.focus();
  }, [isEditable]);

  const handleChangeInput = (e) => {
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
    <div className="padding-container">
      <div className="mb-[30px]">
        <div className="smHeadline">내 정보 관리</div>
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
          <input
            ref={nicknameRef}
            disabled={!isEditable}
            name="nickname"
            type="text"
            maxLength="10"
            className={`w-[160px] h-[34px] rounded-lg p-[10px] text-sm leading-none mb-3 bg-white ${
              isEditable ? "focus:ring focus:ring-wagmiLightGreen" : null
            }`}
            value={nickname}
            onChange={handleChangeInput}
          />
          <input
            disabled={!isEditable}
            name="introduction"
            type="text"
            placeholder="한줄 소개를 입력하세요."
            maxLength="20"
            className={`w-[280px] h-[34px] rounded-lg p-[10px] text-sm leading-none bg-white ${
              isEditable ? "focus:ring focus:ring-wagmiLightGreen" : null
            }`}
            value={introduction}
            onChange={handleChangeInput}
          />
        </div>
      </div>
      <div className="mb-[30px]">
        <div className="smHeadline">나의 지도 관리</div>
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
      <div className="mb-[23px]">
        <div className="smHeadline">
          내가 찜한 테마 {userPicktheme.length}개
        </div>
        <ul className="grid gap-2">
          {userPicktheme.map(({ id, themeEmoji, themeName, reviewCount }) => {
            return (
              <Card
                key={id}
                id={id}
                emoji={themeEmoji}
                name={themeName}
                option={reviewCount}
                option2="♥️"
                isOneLine={true}
                isFull={true}
              />
            );
          })}
        </ul>
      </div>
    </div>
  );
}

export default UserInfo;
