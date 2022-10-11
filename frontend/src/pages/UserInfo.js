import axios from "axios";
import React, { useEffect, useState, Suspense, lazy } from "react";
import { useParams, useOutletContext } from "react-router-dom";
import { scrollToTop } from "lib/utils/scrollToTop";
import Loading from "components/common/Loading";
const UserProfile = lazy(() => import("components/UserInfo/UserProfile"));
const UserMaps = lazy(() => import("components/UserInfo/UserMaps"));
const UserThemes = lazy(() => import("components/UserInfo/UserThemes"));

function UserInfo() {
  const [userProfile, setUserProfile] = useState({
    emoji: "",
    nickname: "",
    introduce: "",
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
    <Suspense fallback={<Loading />}>
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
    </Suspense>
  );
}

export default UserInfo;
