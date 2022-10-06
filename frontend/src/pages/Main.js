import { useEffect, useState } from "react";
import axios from "axios";
import { scrollToTop } from "lib/utils/scrollToTop";
import RecommendedThemes from "components/Main/RecommendedThemes";
import RecommendedCurators from "components/Main/RecommendedCurators";
import PopularThemes from "components/Main/PopularThemes";
import SearchThemes from "components/Main/SearchThemes";
import Footer from "components/Footer";

function Main() {
  const [listsOfCards, setListsOfCards] = useState({
    recommendedThemes: [],
    popularThemes: [],
    recommendedCurators: [],
  });

  useEffect(() => {
    axios.get("/oauth2/users").then((res) => console.log(res));
  }, []);

  const getListsOfCards = () => {
    axios
      .all([
        axios.get("/api/v1/themes/recommends"),
        axios.get("/api/v1/themes/populars"),
        axios.get("/api/v1/users/curators"),
      ])
      .then(
        axios.spread((res1, res2, res3) => {
          setListsOfCards((prev) => ({
            ...prev,
            recommendedThemes: res1.data,
            popularThemes: res2.data,
            recommendedCurators: res3.data,
          }));
        })
      )
      .catch((err) => console.error(err.message));
  };

  useEffect(() => {
    scrollToTop();
    getListsOfCards();
  }, []);

  return (
    <>
      <div className="padding-container">
        <SearchThemes />
        <RecommendedThemes listOfCards={listsOfCards.recommendedThemes} />
        <PopularThemes listOfCards={listsOfCards.popularThemes} />
        <RecommendedCurators listOfCards={listsOfCards.recommendedCurators} />
      </div>
      <Footer />
    </>
  );
}

export default Main;
