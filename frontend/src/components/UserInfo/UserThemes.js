import Card from "components/Card";
import { userPicktheme } from "static/dummyData";

function UserThemes({ userId, uid, nickname }) {
  return (
    <div>
      <div className="smHeadline">
        {`${uid === userId ? "내가 찜한 테마 " : `${nickname}님이 찜한 테마`} ${
          userPicktheme.length
        }개`}
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
              path={`/theme/${id}`}
            />
          );
        })}
      </ul>
    </div>
  );
}

export default UserThemes;
