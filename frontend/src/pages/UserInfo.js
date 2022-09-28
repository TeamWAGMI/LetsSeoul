import Button from "components/Button";
import Card from "components/Card";
import Header from "components/Header";
import { useState } from "react";
import { userPicktheme } from "static/dummyData";
import { buttonStyles } from "lib/styles";

function UserInfo() {
  const [inputText, setInputText] = useState({ name: "유저명", intro: "" });
  const [isEditable, SetIsEditable] = useState(false);

  const {
    smTextBlackButton,
    smWhiteButton,
    smLightGreenButton,
    mdGreenButton,
    mdWhiteGreenButton,
    refreshButton,
  } = buttonStyles;

  return (
    <>
      <Header />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">내 정보 관리</div>
          <div className="flex justify-between mb-3">
            <div className="bg-white rounded-full w-24 h-24 p-6 relative">
              <span className="text-5xl">🐹</span>
              <Button icon="refresh" styles={refreshButton} />
            </div>
            <div className="flex">
              <div className="flex flex-col justify-between mr-3">
                <div className="text-right leading-none">
                  <Button num="1" name=" 팔로잉" styles={smTextBlackButton} />
                </div>
                {isEditable ? (
                  <Button
                    name="수정 완료"
                    styles={smWhiteButton}
                    handleButtonClick={() => SetIsEditable(false)}
                  />
                ) : (
                  <Button
                    name="프로필 수정"
                    styles={smWhiteButton}
                    handleButtonClick={() => SetIsEditable(true)}
                  />
                )}
              </div>
              <div className="flex flex-col justify-between">
                <div className="text-right leading-none">
                  <Button num="143" name=" 팔로워" styles={smTextBlackButton} />
                </div>
                <Button name="팔로우" styles={smLightGreenButton} />
              </div>
            </div>
          </div>
          <div className="flex flex-col">
            <input
              disabled={!isEditable}
              type="text"
              maxLength="10"
              className="w-[160px] h-[34px] rounded-lg p-[10px] text-sm leading-none font-normal text-black mb-3 bg-white"
              value={inputText.name}
              onChange={(e) => {
                setInputText({ ...inputText, name: e.target.value });
              }}
            />
            <input
              disabled={!isEditable}
              type="text"
              placeholder="한줄 소개를 입력하세요."
              maxLength="20"
              className="w-[280px] h-[34px] rounded-lg p-[10px] text-sm leading-none bg-white"
              value={inputText.intro}
              onChange={(e) => {
                setInputText({ ...inputText, intro: e.target.value });
              }}
            />
          </div>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">나의 지도 관리</div>
          <div className="grid gap-2">
            <Button
              isFull={true}
              styles={mdGreenButton}
              name="내가 추천한 장소 모아보기"
              emoji="👍"
            />
            <Button
              isFull={true}
              styles={mdWhiteGreenButton}
              name="내가 찜한 장소 모아보기"
              emoji="🥰"
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
    </>
  );
}

export default UserInfo;
