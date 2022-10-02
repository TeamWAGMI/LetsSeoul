import Button from "components/Button";
import Card from "components/Card";
import { buttonStyles, cardStyles } from "lib/styles";
import { Link } from "react-router-dom";

function PopularThemes({ listOfCards }) {
  const { lgThemeCard } = cardStyles;
  const { mdGreenButton } = buttonStyles;

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">인기 테마지도</div>
      <ul>
        {listOfCards.map(({ id, emoji, title, count }) => (
          <Card
            key={id}
            id={id}
            emoji={emoji}
            name={title}
            option={`${count}개의 추천 장소`}
            styles={lgThemeCard}
            path={`/theme/${id}`}
          />
        ))}
      </ul>
      <Link to="/search" state="all themes">
        <Button
          name="테마지도 전체 보기"
          emoji="👀"
          isFull={true}
          styles={mdGreenButton}
        />
      </Link>
    </div>
  );
}

export default PopularThemes;
