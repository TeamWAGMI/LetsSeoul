import axios from "axios";
import Card from "components/common/Card";
import { scrollToTop } from "lib/utils/scrollToTop";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function Follow() {
  const [followNums, setFollowNums] = useState({
    numberOfFollowing: "",
    numberOfFollower: "",
  });
  const [followList, setFollowList] = useState([]);
  const navigate = useNavigate();
  const { uid, follow } = useParams();

  const tabs = [
    { name: "팔로워", params: "followers" },
    { name: "팔로잉", params: "followings" },
  ];

  const tabClass = (params) => {
    if (follow === params) {
      return "w-1/2 text-center text-sm font-semibold leading-none p-[18px] cursor-pointer";
    }
    return "w-1/2 text-center text-sm font-semibold leading-none p-[18px] bg-wagmiGreen text-textWhite cursor-pointer";
  };

  useEffect(() => {
    const getFollowLists = async () => {
      try {
        if (follow === "followers") {
          const res1 = await axios.get(`/api/v1/follows/${uid}/followers`);
          setFollowList(res1.data);
        } else if (follow === "followings") {
          const res2 = await axios.get(`/api/v1/follows/${uid}/followings`);
          setFollowList(res2.data);
        }
        const res = await axios.get(`/api/v1/follows/${uid}/count`);
        const { numberOfFollower, numberOfFollowing } = res.data;
        setFollowNums((prev) => ({
          ...prev,
          numberOfFollower,
          numberOfFollowing,
        }));
      } catch (err) {
        console.error(err.message);
      }
    };
    getFollowLists();
    scrollToTop();
  }, [follow, uid]);

  return (
    <>
      <div className="flex">
        {tabs.map(({ name, params }) => (
          <div
            key={params}
            className={tabClass(params)}
            onClick={() => navigate(`/user/${uid}/${params}`)}
          >
            {`${
              params === "followers"
                ? followNums.numberOfFollower
                : followNums.numberOfFollowing
            }
            ${name}`}
          </div>
        ))}
      </div>
      <div className="padding-container">
        <ul className="grid gap-2">
          {followList.map(({ userId, emoji, nickname, followerCount }) => {
            const path = `/user/${userId}`;
            return (
              <Card
                key={userId}
                id={userId}
                emoji={emoji}
                name={nickname}
                option={followerCount}
                option2="♥️"
                isOneLine={true}
                isFull={true}
                path={path}
              />
            );
          })}
        </ul>
      </div>
    </>
  );
}

export default Follow;
