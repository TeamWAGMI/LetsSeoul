import React, { useRef } from "react";
import Card from "components/common/Card";
import { useDraggable } from "react-use-draggable-scroll";
import { cardStyles } from "lib/styles";
import FixedCard from "lib/skeleton/FixedCard";

function RecommendedThemes({ listOfCards, isLoading }) {
  const { fixedCard } = cardStyles;
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
        {!isLoading
          ? listOfCards.map(({ id, emoji, title, storeCount }) => {
              return (
                <Card
                  key={id}
                  id={id}
                  emoji={emoji}
                  name={title}
                  option={`${storeCount}개의 추천 장소`}
                  styles={fixedCard}
                  path={`/theme/${id}`}
                />
              );
            })
          : new Array(5)
              .fill(0)
              .map((_, idx) => <FixedCard key={idx} id={idx} />)}
      </ul>
    </div>
  );
}

export default RecommendedThemes;
