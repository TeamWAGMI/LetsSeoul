function Button({
  style,
  isFull = false,
  name = null,
  emoji = null,
  icon = null,
  handleButtonClick,
}) {
  // svg icon button
  if (icon) {
    return (
      <button className={style} onClick={handleButtonClick}>
        <img
          className={icon !== "hamburger" ? "inline" : null}
          src={`images/${icon}.svg`}
          alt={icon}
        />
        {name && <p className="w-[100%] text-center">{name}</p>}
      </button>
    );
  }

  return (
    <button
      className={`${style} ${isFull && "w-[100%]"}`}
      onClick={handleButtonClick}
    >
      <span>{name}</span>
      {emoji && <span className="ml-2">{emoji}</span>}
    </button>
  );
}

export default Button;
