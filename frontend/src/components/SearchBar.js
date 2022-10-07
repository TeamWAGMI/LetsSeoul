import { buttonStyles } from "lib/styles";
import Button from "./common/Button";

const SearchBar = ({ inputKeyword, handleInputChange, handleInputSubmit }) => {
  const { searchButton } = buttonStyles;

  return (
    <div className="flex justify-between items-center p-[13px] bg-white border border-borderGray rounded-lg text-sm">
      <input
        className="grow"
        placeholder="테마를 키워드로 검색해보세요."
        value={inputKeyword}
        onChange={handleInputChange}
        onKeyPress={(e) => (e.key === "Enter" ? handleInputSubmit() : null)}
      />
      <Button
        styles={searchButton}
        icon="search"
        handleButtonClick={handleInputSubmit}
      />
    </div>
  );
};

export default SearchBar;
