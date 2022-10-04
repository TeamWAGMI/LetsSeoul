import axios from "axios";
import MapNav from "components/MapNav";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Outlet, useLocation, useParams } from "react-router-dom";

// UserPickMap, UserWishMap
function Layout2() {
  const [userInfo, setUserInfo] = useState({
    emoji: "",
    nickname: "",
    introduction: "",
  });
  const loginUserInfo = useSelector((state) => state.userInfo.value);
  const { uid } = useParams();
  const location = useLocation();
  let emoji = "";
  let name = "";

  if (loginUserInfo.userId === uid) {
    if (location.pathname.slice(-4) === "pick") {
      name = "내가 추천한 장소";
    } else {
      name = "내가 찜한 장소";
    }
    emoji = loginUserInfo.userEmoji;
  } else {
    if (location.pathname.slice(-4) === "pick") {
      name = `${userInfo.nickname}님이 추천한 장소`;
    } else {
      name = `${userInfo.nickname}님이 찜한 장소`;
    }
    emoji = userInfo.emoji;
  }

  useEffect(() => {
    axios
      .get(`/api/v1/users/${uid}`)
      .then((res) => setUserInfo((prev) => ({ ...prev, ...res.data })));
  }, [uid]);

  return (
    <>
      <MapNav isUsers={true} emoji={emoji} name={name} />
      <Outlet />
    </>
  );
}

export default Layout2;
