import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, useOutletContext } from "react-router-dom";
import { scrollToTop } from "lib/utils/scrollToTop";
import UserProfile from "components/UserInfo/UserProfile";
import UserMaps from "components/UserInfo/UserMaps";
import UserThemes from "components/UserInfo/UserThemes";

function UserInfo() {
  const [userProfile, setUserProfile] = useState({
    emoji: "",
    nickname: "",
    introduction: "",
  });
  const { uid } = useParams();
  const { userId } = useOutletContext();

  useEffect(() => {
    scrollToTop();
  }, []);

  useEffect(() => {
    axios
      .get(`/api/v1/users/${uid}`)
      .then((res) => setUserProfile((prev) => ({ ...prev, ...res.data })));
  }, [uid]);

  return (
    <div className="padding-container">
      <UserProfile
        userId={userId}
        uid={uid}
        userProfile={userProfile}
        setUserProfile={setUserProfile}
      />
      <UserMaps userId={userId} uid={uid} nickname={userProfile.nickname} />
      <UserThemes userId={userId} uid={uid} nickname={userProfile.nickname} />
    </div>
  );
}

export default UserInfo;
