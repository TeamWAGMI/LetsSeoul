import Card from "components/Card";
import Footer from "components/Footer";
import Header from "components/Header";
import { Link } from "react-router-dom";
import { buttonStyles, cardStyles } from "lib/styles";
import { popularThemes, recommendedCurators } from "static/dummyData";
import Button from "components/Button";
import { scrollToTop } from "lib/utils";
import { useEffect, useState } from "react";
import axios from "axios";

function Main() {
  const [recommendedThemes, setRecommendedThemes] = useState([]);

  const { fixedThemeCard, lgThemeCard } = cardStyles;
  const { mdGreenButton } = buttonStyles;

  useEffect(() => {
    getRecommendedThemes();
    scrollToTop();
  }, []);

  const getRecommendedThemes = async () => {
    const recommend = await axios.get("/api/v1/themes/recommends");
    setRecommendedThemes(recommend.data);
  };

  return (
    <div className="relative">
      <Header />
      <div className="padding-container">
        <div className="mb-[30px]">
          <div className="smHeadline">테마 검색하기</div>
          <Link to="/search">
            <div className="flex items-center text-sm text-textGray p-[13px] bg-textWhite rounded-lg border border-borderGray">
              <img
                className="w-4 h-4 mr-2"
                src="images/search.svg"
                alt="search "
              />
              <div>찾는 맛집의 테마가 뭐예요?</div>
            </div>
          </Link>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">추천 테마지도</div>
          <ul className="flex flex-nowrap overflow-x-scroll no-scrollbar">
            {recommendedThemes.map(({ id, emoji, title, count }) => {
              const option = `${count}개의 추천 장소`;
              return (
                <Link key={id} to={`/theme/${id}`}>
                  <Card
                    key={id}
                    id={id}
                    emoji={emoji}
                    name={title}
                    option={option}
                    styles={fixedThemeCard}
                  />
                </Link>
              );
            })}
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">인기 테마지도</div>
          <ul>
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
                  styles={lgThemeCard}
                />
              );
            })}
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
        <div className="mb-[30px]">
          <div className="smHeadline">추천 큐레이터</div>
          <ul className="flex flex-nowrap overflow-x-scroll no-scrollbar">
            {recommendedCurators.map((card) => {
              const { id, emoji, nickname, reviewCount } = card;
              const option = `${reviewCount}개의 장소 추천중`;
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={nickname}
                  option={option}
                  styles={fixedThemeCard}
                />
              );
            })}
          </ul>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Main;
