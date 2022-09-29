import axios from "axios";
import Card from "components/Card";
import Header from "components/Header";
import { useEffect, useState } from "react";

function FollowList() {
  const [activeTab, setActiveTab] = useState(0);
  const [followNums, setFollowNums] = useState({});
  const [followList, setFollowList] = useState([]);

  const userId = 1;
  const tabTitle = ["팔로워", "팔로우"];

  console.log(followNums);

  useEffect(() => {
    if (activeTab === 0) {
      axios
        .get(`/api/v1/follows/${userId}/followers`)
        .then((res) => setFollowList(res.data));
    } else if (activeTab === 1) {
      axios
        .get(`/api/v1/follows/${userId}/followings`)
        .then((res) => setFollowList(res.data));
    }
    axios
      .get(`/api/v1/follows/${userId}/count`)
      .then((res) => setFollowNums(res.data));
  }, [activeTab]);

  const tabClass = (idx) => {
    if (activeTab === idx) {
      return "w-1/2 text-center text-sm leading-none p-[18px]";
    }
    return "w-1/2 text-center text-sm leading-none p-[18px] bg-wagmiGreen text-textWhite";
  };

  const handleTabActive = (idx) => {
    setActiveTab(idx);
  };

  return (
    <>
      <Header />
      <div className="flex">
        {tabTitle.map((tab, idx) => (
          <div
            key={idx}
            className={tabClass(idx)}
            onClick={() => handleTabActive(idx)}
          >
            {tab}
          </div>
        ))}
      </div>
      <div className="padding-container">
        <ul className="grid gap-2">
          {followList.map(({ userId, emoji, nickname, followerCount }) => {
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
              />
            );
          })}
        </ul>
      </div>
    </>
  );
}

export default FollowList;
