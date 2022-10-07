import React, { useRef } from "react";
import Card from "components/common/Card";
import { useDraggable } from "react-use-draggable-scroll";
import { cardStyles } from "lib/styles";

function RecommendedCurators({ listOfCards }) {
  const { fixedThemeCard } = cardStyles;
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
        {listOfCards.map(({ userId, emoji, nickname, reviewCount }) => (
          <Card
            key={userId}
            id={userId}
            emoji={emoji}
            name={nickname}
            option={`${reviewCount}개의 장소 추천중`}
            styles={fixedThemeCard}
            path={`/user/${userId}`}
          />
        ))}
      </ul>
    </div>
  );
}

export default RecommendedCurators;
