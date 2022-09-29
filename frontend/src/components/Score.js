import { useState } from "react";

function Score() {
  const [hovered, setHovered] = useState(null);
  const [clicked, setClicked] = useState(null);

  return (
    <span className="inline">
      {[1, 2, 3, 4, 5].map((el) => (
        <span
          key={el}
          onMouseEnter={() => setHovered(el)}
          onMouseLeave={() => setHovered(null)}
          onClick={() => setClicked(el)}
        >
          {clicked >= el || hovered >= el ? (
            <span className="text-wagmiLightGreen">★</span>
          ) : (
            <span className="text-textGray">☆</span>
          )}
        </span>
      ))}
    </span>
  );
}

export default Score;
