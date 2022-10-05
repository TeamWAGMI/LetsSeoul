import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Button from "components/Button";
import Card from "components/Card";
import Footer from "components/Footer";
import SearchBar from "components/SearchBar";
import Tag from "components/Tag";
import { scrollToTop } from "lib/utils/scrollToTop";
import { atWhere, doWhat, withWhom } from "static/dummyData";
import { buttonStyles, cardStyles } from "lib/styles";

function Search() {
  const [themeCardsList, setThemeCardsList] = useState([]);
  const [selectedWhom, setSelectedWhom] = useState([]);
  const [selectedWhat, setSelectedWhat] = useState([]);
  const [selectedWhere, setSelectedWhere] = useState([]);
  const [inputKeyword, setInputKeyword] = useState("");
  const [submitKeyword, setSubmitKeyword] = useState("");

  const { gridThemeCard } = cardStyles;
  const { mdGreenButton } = buttonStyles;

  useEffect(() => {
    scrollToTop();
  }, []);

  useEffect(() => {
    const data = {
      keyword: submitKeyword,
      who: selectedWhom,
      what: selectedWhat,
      where: selectedWhere,
    };

    axios
      .post(`/api/v1/themes/search`, data)
      .then((res) => setThemeCardsList(res.data.content));
  }, [selectedWhom, selectedWhat, selectedWhere, submitKeyword]);

  return (
    <>
      <div className="padding-container">
        <div className="mb-[30px]">
          <SearchBar
            inputKeyword={inputKeyword}
            handleInputChange={(e) => setInputKeyword(e.target.value)}
            handleInputSubmit={() => setSubmitKeyword(inputKeyword)}
          />
        </div>
        <div className="mb-[30px]">
          <Tag
            title="누구와?"
            tags={withWhom}
            selectedTag={selectedWhom}
            setSelectedTag={setSelectedWhom}
          />
          <Tag
            title="뭘 하기 좋은?"
            tags={doWhat}
            selectedTag={selectedWhat}
            setSelectedTag={setSelectedWhat}
          />
          <Tag
            title="어떤 곳에서?"
            tags={atWhere}
            selectedTag={selectedWhere}
            setSelectedTag={setSelectedWhere}
          />
        </div>
        <div className="mb-[30px]">
          <div className="mb-3 font-semibold text-sm leading-none">
            {themeCardsList.length === 0
              ? "검색된 테마가 없습니다. "
              : `${themeCardsList.length}개의 테마를 찾았어요.`}
          </div>
          <ul className="grid grid-cols-2 gap-[11px]">
            {themeCardsList.map(
              ({ themeId, themeEmoji, themeTitle, reviewCount }) => (
                <Card
                  key={themeId}
                  id={themeId}
                  emoji={themeEmoji}
                  name={themeTitle}
                  option={`${reviewCount}개의 추천 장소`}
                  styles={gridThemeCard}
                  path={`theme/${themeId}`}
                />
              )
            )}
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
              styles={mdGreenButton}
            />
          </Link>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default Search;
