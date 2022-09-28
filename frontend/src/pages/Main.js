import Card from "components/Card";
import Footer from "components/Footer";
import Header from "components/Header";
import { Link } from "react-router-dom";
import { buttonStyles, cardStyles } from "lib/styles";
import Button from "components/Button";
import { scrollToTop } from "lib/utils";
import { useEffect, useState } from "react";
import axios from "axios";

function Main() {
  const [cardLists, setCardLists] = useState({
    recommendThemes: [],
    popularThemes: [],
    recommendCurators: [],
  });

  const { fixedThemeCard, lgThemeCard } = cardStyles;
  const { mdGreenButton } = buttonStyles;

  const getCardLists = async () => {
    try {
      const res1 = await axios.get("/api/v1/themes/recommends");
      const res2 = await axios.get("/api/v1/themes/populars");
      const res3 = await axios.get("/api/v1/users/curators");

      const recommendThemes = res1.data;
      const popularThemes = res2.data;
      const recommendCurators = res3.data;

      setCardLists((prev) => {
        return { ...prev, recommendThemes, popularThemes, recommendCurators };
      });
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getCardLists();
    scrollToTop();
  }, []);

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
            {cardLists.recommendThemes.map(({ id, emoji, title, count }) => {
              const option = `${count}개의 추천 장소`;
              const path = `/theme/${id}`;
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={title}
                  option={option}
                  styles={fixedThemeCard}
                  path={path}
                />
              );
            })}
          </ul>
        </div>
        <div className="mb-[30px]">
          <div className="smHeadline">인기 테마지도</div>
          <ul>
            {cardLists.popularThemes.map(({ id, emoji, title, count }) => {
              const option = `${count}개의 추천 장소`;
              const path = `/theme/${id}`;
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={title}
                  option={option}
                  styles={lgThemeCard}
                  path={path}
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
            {cardLists.recommendCurators.map(
              ({ userId, emoji, nickname, reviewCount }) => {
                const option = `${reviewCount}개의 장소 추천중`;
                const path = `/user/${userId}`;

                return (
                  <Card
                    key={userId}
                    id={userId}
                    emoji={emoji}
                    name={nickname}
                    option={option}
                    styles={fixedThemeCard}
                    path={path}
                  />
                );
              }
            )}
          </ul>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Main;
