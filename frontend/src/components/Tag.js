function Tag({ title, tags }) {
  return (
    <div className="mb-[10px]">
      <div className="smHeadline">{title}</div>
      <div>
        {tags.map((tag) => (
          <button
            key={tag.id}
            className="bg-white rounded-lg text-[#756E56] font-medium text-xs leading-none p-[10px] mr-[10px] mb-[10px]"
          >
            {tag.name}
          </button>
        ))}
      </div>
    </div>
  );
}

export default Tag;
