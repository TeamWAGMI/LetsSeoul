function Tag({ title, tags, selectedTag, setSelectedTag }) {
  const tagClass = (id) => {
    if (selectedTag === id)
      return "bg-wagmiLightGreen rounded-lg text-white font-semibold text-xs leading-none p-[10px] mr-[10px] mb-[10px]";
    return "bg-white rounded-lg text-[#756E56] font-semibold text-xs leading-none p-[10px] mr-[10px] mb-[10px] hover:bg-[#E6E3D6]";
  };

  const handleTagSelect = (id) => {
    setSelectedTag(id);
    if (selectedTag === id) setSelectedTag(null);
  };

  return (
    <div className="mb-[10px]">
      <div className="smHeadline">{title}</div>
      <div>
        {tags.map((tag) => (
          <button
            key={tag.id}
            onClick={() => handleTagSelect(tag.id)}
            className={tagClass(tag.id)}
          >
            {tag.name}
          </button>
        ))}
      </div>
    </div>
  );
}

export default Tag;
