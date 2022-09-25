function Button({
  name = null,
  emoji = null,
  icon = null,
  handleButtonClick,
  style,
  isFull = false,
}) {
  // svg icon button
  if (icon) {
    return (
      <button className={style} onClick={handleButtonClick}>
        <img
          className={icon !== "hamburger" ? "inline" : null}
          src={`/images/${icon}.svg`}
          alt={icon}
        />
        {name && <p className="w-full text-center">{name}</p>}
      </button>
    );
  }

  return (
    <button
      className={`${style} ${isFull && "w-full"}`}
      onClick={handleButtonClick}
    >
      <span>{name}</span>
      {emoji && <span className="ml-2">{emoji}</span>}
    </button>
  );
}

export default Button;
