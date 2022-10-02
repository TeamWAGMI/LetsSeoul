export const getConvertedDate = (date) => {
  const convertedDate = new Date(date);
  const hours = String(convertedDate.getHours()).padStart(2, "0");
  const minutes = String(convertedDate.getMinutes()).padStart(2, "0");
  return `${convertedDate.toLocaleDateString()} ${hours}:${minutes}`;
};
