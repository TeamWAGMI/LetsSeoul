import Follow from "pages/Follow";
import Main from "pages/Main";
import Request from "pages/Request";
import Review from "pages/Review";
import Search from "pages/Search";
import SearchStore from "pages/SearchStore";
import StoreInfo from "pages/StoreInfo";
import ThemeMap from "pages/ThemeMap";
import UserInfo from "pages/UserInfo";
import UserPickMap from "pages/UserPickMap";
import UserWishMap from "pages/UserWishMap";
import { BrowserRouter, Route, Routes } from "react-router-dom";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Main />} />
        <Route path="/search" element={<Search />} />
        <Route path="/request" element={<Request />} />
        <Route path="/storeinfo/:sid" element={<StoreInfo />} />
        <Route path="/review/:sid" element={<Review />} />
        <Route path="/user/:uid" element={<UserInfo />} />
        <Route path="/user/:uid/follow" element={<Follow />} />
        <Route path="/theme/:tid" element={<ThemeMap />} />
        <Route path="/theme/search/:tid" element={<SearchStore />} />
        <Route path="/mypick/:uid" element={<UserPickMap />} />
        <Route path="/mywish/:uid" element={<UserWishMap />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
