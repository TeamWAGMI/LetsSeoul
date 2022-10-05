function Tag({ title, tags, selectedTag, setSelectedTag }) {
  const tagClass = (name) => {
    return `${
      selectedTag.includes(name)
        ? "bg-wagmiLightGreen text-white"
        : "bg-white text-[#756E56] hover:bg-[#E6E3D6]"
    } rounded-lg text-xs font-semibold p-[10px] mr-[10px] mb-[10px] leading-none`;
  };

  const handleTagSelect = (name) => {
    setSelectedTag((prev) => [...prev, name]);

    if (selectedTag.includes(name)) {
      setSelectedTag(selectedTag.filter((tag) => tag !== name));
    }
  };

  return (
    <div className="mb-[10px]">
      <div className="smHeadline">{title}</div>
      <div>
        {tags.map((tag) => (
          <button
            key={tag.name}
            onClick={() => handleTagSelect(tag.name)}
            className={tagClass(tag.name)}
          >
            {tag.name}
          </button>
        ))}
      </div>
    </div>
  );
}

export default Tag;
