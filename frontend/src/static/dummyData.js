// 서버 통신 전 테스트용 데이터

// 메인 페이지 (Main)
export const recommendedThemes = [
  {
    id: 1,
    emoji: "🧇",
    name: "크로플 존맛인 카페",
    count: 5,
  },
  {
    id: 2,
    emoji: "💻",
    name: "혼자 노트북 들고 가기 좋은 카페",
    count: 5,
  },
  {
    id: 3,
    emoji: "💻",
    name: "혼자 노트북 들고 가기 좋은 카페",
    count: 5,
  },
  {
    id: 4,
    emoji: "💻",
    name: "혼자 노트북 들고 가기 좋은 카페",
    count: 5,
  },
  {
    id: 5,
    emoji: "💻",
    name: "혼자 노트북 들고 가기 좋은 카페",
    count: 5,
  },
];

export const popularThemes = [
  {
    id: 1,
    emoji: "🥯",
    name: "빵지순례 필수코스",
    count: 5,
  },
  {
    id: 2,
    emoji: "🍟",
    name: "감자튀김이 바삭한 곳",
    count: 5,
  },
  {
    id: 3,
    emoji: "🧀",
    name: "치즈 좋아하면 모여라 필수코스",
    count: 5,
  },
  {
    id: 4,
    emoji: "🍣",
    name: "초밥 먹고 싶으면 여기로",
    count: 5,
  },
  {
    id: 5,
    emoji: "💻",
    name: "혼자 노트북 들고 가기 좋은 카페",
    count: 5,
  },
  {
    id: 6,
    emoji: "🥗🥙",
    name: "난 샐러드를 맛있어서 먹어",
    count: 5,
  },
];

export const recommendedCurators = [
  {
    id: 1,
    emoji: "🐹",
    nickname: "맛잘알 햄스터",
    reviewCount: 10,
  },
  {
    id: 2,
    emoji: "🐹",
    nickname: "맛잘알 햄스터",
    reviewCount: 10,
  },
  {
    id: 3,
    emoji: "🐹",
    nickname: "맛잘알 햄스터",
    reviewCount: 10,
  },
  {
    id: 4,
    emoji: "🐹",
    nickname: "맛잘알 햄스터",
    reviewCount: 10,
  },
  {
    id: 5,
    emoji: "🐹",
    nickname: "맛잘알 햄스터",
    reviewCount: 10,
  },
];

// 검색 페이지 (Search)
export const withWhom = [
  { id: 1, name: "혼자" },
  { id: 2, name: "친구" },
  { id: 3, name: "연인" },
  { id: 4, name: "부모님" },
  { id: 5, name: "아이" },
  { id: 6, name: "반려동물" },
  { id: 7, name: "모임" },
];

export const doWhat = [
  { id: 1, name: "이야기 하기" },
  { id: 2, name: "책 읽기" },
  { id: 3, name: "일하기" },
  { id: 4, name: "점심식사" },
  { id: 5, name: "저녁식사" },
  { id: 6, name: "공부하기" },
  { id: 7, name: "쉬기" },
];

export const atWhere = [
  { id: 1, name: "식당" },
  { id: 2, name: "카페" },
  { id: 3, name: "주점" },
  { id: 4, name: "베이커리" },
];

// 가게 정보 페이지 (store)
export const storeTheme = [
  {
    id: 1,
    emoji: "🥘",
    name: "엄마표 된장찌개보다 맛있어",
    count: 10,
  },
  {
    id: 2,
    emoji: "🍚",
    name: "집밥이 그리울 때 생각나는 백반집",
    count: 5,
  },
];

export const storeReview = {
  content: [
    {
      userId: 1,
      userEmoji: "🥰",
      userNickname: "맛집 사냥꾼",
      reviewId: 1,
      reviewScore: 4,
      reviewContent: "여기 진짜 맛있습니다!",
      createdDatetime: "createdAt",
      modifiedDateTime: "modifiedAt",
      reviewImages: ["이미지경로1"],
    },
    {
      userId: 2,
      userEmoji: "🍟",
      userNickname: "바삭바삭",
      reviewId: 2,
      reviewScore: 3,
      reviewContent: "여기 진짜 맛있습니다!",
      createdDatetime: "createdAt",
      modifiedDateTime: "modifiedAt",
      reviewImages: ["이미지경로12"],
    },
  ],
  pageInfo: {
    nowPage: 1,
    nowCount: 1,
    totalPage: 2,
    totalCount: 5,
  },
};
