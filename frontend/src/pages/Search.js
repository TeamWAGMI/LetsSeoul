import Button from "components/Button";
import Card from "components/Card";
import Footer from "components/Footer";
import Header from "components/Header";
import SearchBar from "components/SearchBar";
import Tag from "components/Tag";
import { scrollToTop } from "libraries/utils";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { atWhere, doWhat, popularThemes, withWhom } from "static/dummyData";
import { buttonStyles, cardStyles } from "styles/props";

function Search() {
  const { gridThemeCard } = cardStyles;
  const { mdGreenButton } = buttonStyles;

  useEffect(() => {
    scrollToTop();
  }, []);

  return (
    <div className="relative">
      <Header />
      <div className="padding-container">
        <div className=" mb-[30px]">
          <SearchBar />
        </div>
        <div className="mb-[30px]">
          <Tag title="누구와?" tags={withWhom} />
          <Tag title="뭘 하기 좋은?" tags={doWhat} />
          <Tag title="어떤 곳에서?" tags={atWhere} />
        </div>
        <div className="mb-[30px]">
          <div className="mb-3 font-medium text-sm leading-none">
            6개의 테마를 찾았어요.
          </div>
          <ul className="grid grid-cols-2 gap-[11px]">
            {popularThemes.map((card) => {
              const { id, emoji, name, count } = card;
              const option = `${count}개의 추천 장소`;
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={name}
                  option={option}
                  style={gridThemeCard}
                />
              );
            })}
          </ul>
        </div>
        <div>
          <p className="mb-2 text-center text-xs leaidng-none">
            찾으시는 테마가 없나요?
          </p>
          <Link to="/request" state="all themes">
            <Button
              name="새로운 테마 요청하기"
              emoji="🤔"
              isFull={true}
              style={mdGreenButton}
            />
          </Link>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Search;
