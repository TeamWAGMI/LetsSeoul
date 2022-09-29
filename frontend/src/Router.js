import FollowList from "pages/FollowList";
import Main from "pages/Main";
import RequestForm from "pages/RequestForm";
import ReviewForm from "pages/ReviewForm";
import Search from "pages/Search";
import SearchStore from "pages/SearchStore";
import StoreInfo from "pages/StoreInfo";
import ThemeMap from "pages/ThemeMap";
import UserInfo from "pages/UserInfo";
import UserPickMap from "pages/UserPickMap";
import UserWishMap from "pages/UserWishMap";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginData from "pages/LoginData";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Main />} />
        <Route path="/search" element={<Search />} />
        <Route path="/request" element={<RequestForm />} />
        <Route path="/store/:sid" element={<StoreInfo />} />
        <Route path="/review/:sid" element={<ReviewForm />} />
        <Route path="/user/:uid" element={<UserInfo />} />
        <Route path="/user/:uid/:follow" element={<FollowList />} />
        <Route path="/theme/:tid" element={<ThemeMap />} />
        <Route path="/theme/search/:tid" element={<SearchStore />} />
        <Route path="/mypick/:uid" element={<UserPickMap />} />
        <Route path="/mywish/:uid" element={<UserWishMap />} />
        <Route path="/transition/login" element={<LoginData />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
