import React, { useState } from "react";
import SearchStore from "./SearchStore";
import Button from "components/Button";
import { buttonStyles } from "lib/styles";

function LandingPage() {
  const [inputText, setInputText] = useState("");
  const [place, setPlace] = useState("");
  const { searchButton } = buttonStyles;

  //검색하는함수

  console.log("landing");

  const onChange = (e) => {
    setInputText(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setPlace(inputText);
  };

  const placeNull = () => {
    setInputText("");
    setPlace("");
  };

  return (
    <div className="relative">
      <span className="absolute z-30 mt-[385px] ml-7 bg-wagmiLightGreen text-textWhite text-sm font-semibold rounded-lg py-[11px] px-4">
        장소 찾기
      </span>
      <div className="absolute z-30 mt-[440px] flex justify-between items-center w-96 p-[13px] bg-white border border-borderGray rounded-lg text-sm overflow-auto ml-7 flex justify-between items-center ">
        <form onSubmit={handleSubmit}>
          <Button styles={searchButton} icon="search" />
          <input
            placeholder="추천할 장소를 검색해보세요"
            onChange={onChange}
            value={inputText}
          />
        </form>
      </div>
      <div className="z-30 overflow-auto  ">
        <SearchStore
          inputText={inputText}
          searchPlace={place}
          placeNull={placeNull}
        />
      </div>
    </div>
  );
}
export default LandingPage;
