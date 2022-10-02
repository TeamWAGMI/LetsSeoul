import React, { useRef } from "react";
import Card from "components/Card";
import { useDraggable } from "react-use-draggable-scroll";
import { cardStyles } from "lib/styles";

function RecommendedThemes({ listOfCards }) {
  const { fixedThemeCard } = cardStyles;
  const ref = useRef(null);
  const { events } = useDraggable(ref);

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">추천 테마지도</div>
      <ul
        className="grid grid-flow-col gap-2 overflow-x-scroll no-scrollbar"
        ref={ref}
        {...events}
      >
        {listOfCards.map(({ id, emoji, title, count }) => {
          return (
            <Card
              key={id}
              id={id}
              emoji={emoji}
              name={title}
              option={`${count}개의 추천 장소`}
              styles={fixedThemeCard}
              path={`/theme/${id}`}
            />
          );
        })}
      </ul>
    </div>
  );
}

export default RecommendedThemes;
