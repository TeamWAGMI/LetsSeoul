import React, { useRef } from "react";
import Card from "components/common/Card";
import { useDraggable } from "react-use-draggable-scroll";
import { cardStyles } from "lib/styles";
import FixedCard from "lib/skeleton/FixedCard";

function RecommendedCurators({ listOfCards, isLoading }) {
  const { fixedCard } = cardStyles;
  const ref = useRef(null);
  const { events } = useDraggable(ref);

  return (
    <div className="mb-[30px]">
      <div className="smHeadline">추천 큐레이터</div>
      <ul
        className="grid grid-flow-col gap-2 overflow-x-scroll no-scrollbar"
        ref={ref}
        {...events}
      >
        {!isLoading
          ? listOfCards.map(({ userId, emoji, nickname, reviewCount }) => (
              <Card
                key={userId}
                id={userId}
                emoji={emoji}
                name={nickname}
                option={`${reviewCount}개의 장소 추천중`}
                styles={fixedCard}
                path={`/user/${userId}`}
              />
            ))
          : new Array(5)
              .fill(0)
              .map((_, idx) => <FixedCard key={idx} id={idx} />)}
      </ul>
    </div>
  );
}

export default RecommendedCurators;
