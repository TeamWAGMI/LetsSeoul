import { Link } from "react-router-dom";

function SearchThemes() {
  return (
    <div className="mb-[30px]">
      <div className="smHeadline">테마 검색하기</div>
      <Link to="/search">
        <div className="flex items-center text-sm text-textGray p-[13px] bg-textWhite rounded-lg border border-borderGray">
          <img className="w-4 h-4 mr-2" src="images/search.svg" alt="search " />
          <div>찾는 맛집의 테마가 뭐예요?</div>
        </div>
      </Link>
    </div>
  );
}

export default SearchThemes;
