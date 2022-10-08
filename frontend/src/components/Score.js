import { useState } from "react";

function Score({ score, handleReviewChange }) {
  const [hovered, setHovered] = useState(null);

  return (
    <span className="inline cursor-pointer">
      {[1, 2, 3, 4, 5].map((el) => (
        <span
          key={el}
          onMouseEnter={() => setHovered(el)}
          onMouseLeave={() => setHovered(null)}
          onClick={() => handleReviewChange((prev) => ({ ...prev, score: el }))}
        >
          {score >= el || hovered >= el ? (
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
