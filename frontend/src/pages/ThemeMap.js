import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch } from "react-redux";
import axios from "axios";
import Button from "components/common/Button";
import MapNav from "components/common/MapNav";
import { Map } from "react-kakao-maps-sdk";
import { buttonStyles } from "lib/styles";
import { checkSession } from "lib/utils/checkSession";

function ThemeMap() {
  const [themeInfo, setThemeInfo] = useState({
    themeId: "",
    themeEmoji: "",
    themeTitle: "",
  });
  const { tid } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { lgGreenSemiRoundButton } = buttonStyles;

  useEffect(() => {
    axios
      .get(`/api/v1/themes/${tid}/info`)
      .then((res) => setThemeInfo(res.data));
  }, [tid]);

  const handleRecommendButton = () => {
    const nextCallBack = () => {
      navigate(`/theme/search/${tid}`, { state: { themeInfo } });
    };
    checkSession(dispatch, nextCallBack);
  };

  return (
    <>
      <div className="sticky z-10 desktop:top-[90%] desktop:ml-[61%] top-[90%] ml-[50%] h-0 cursor-pointer">
        <Button
          name="장소 추천하기"
          emoji="📌"
          styles={`${lgGreenSemiRoundButton} py-4`}
          handleButtonClick={handleRecommendButton}
        />
      </div>
      <MapNav emoji={themeInfo.themeEmoji} name={themeInfo.themeTitle} />
      <Map
        center={{ lat: 37.566769, lng: 126.978323 }}
        style={{ width: "100%", height: "100vh" }}
        level={9}
      ></Map>
    </>
  );
}
export default ThemeMap;
