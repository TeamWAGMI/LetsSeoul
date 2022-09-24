const SearchBar = () => {
  return (
    <div className="flex justify-between items-center p-[13px] bg-white border border-borderGray rounded-lg text-sm">
      <input
        className="grow focus:outline-none"
        placeholder="테마를 키워드로 검색해보세요."
      />
      <button className="p-[13px] m-[-13px]">
        <img src="images/search.svg" alt="search_bar" />
      </button>
    </div>
  );
};

export default SearchBar;
