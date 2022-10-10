import axios from "axios";
import Card from "components/common/Card";
import { useEffect, useState } from "react";

function UserThemes({ userId, uid, nickname }) {
  const [wishThemes, setWishThemes] = useState([]);

  useEffect(() => {
    axios
      .get(`/api/v1/users/${uid}/themes`)
      .then((res) => setWishThemes(res.data.content))
      .catch((err) => console.error(err.message));
  }, [uid]);

  return (
    <div>
      <div className="smHeadline">
        {`${uid === userId ? "내가 찜한 테마 " : `${nickname}님이 찜한 테마`} ${
          wishThemes.length
        }개`}
      </div>
      <ul className="grid gap-2">
        {wishThemes.map(({ themeId, themeEmoji, themeName, dibsCount }) => {
          return (
            <Card
              key={themeId}
              id={themeId}
              emoji={themeEmoji}
              name={themeName}
              option={dibsCount}
              option2="♥️"
              isOneLine={true}
              isFull={true}
              path={`/theme/${themeId}`}
            />
          );
        })}
      </ul>
    </div>
  );
}

export default UserThemes;
