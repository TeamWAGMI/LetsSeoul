import { cardStyles } from "../styles";

function FixedCard({ id }) {
  const { fixedCard } = cardStyles;
  return (
    <div
      key={id}
      className={`${fixedCard} flex flex-col items-center animate-pulse`}
    >
      <div className="h-6 w-6 rounded-full bg-gray-200 dark:bg-gray-800" />
      <div className="h-[20px] w-[88px] my-2 rounded-lg bg-gray-200 dark:bg-gray-800" />
      <div className="h-3 w-20 mt-1 rounded-lg bg-gray-200 dark:bg-gray-800" />
    </div>
  );
}

export default FixedCard;
