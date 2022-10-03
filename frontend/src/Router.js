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
import Layout1 from "layout/Layout1";
import Layout2 from "layout/Layout2";
import Layout3 from "layout/Layout3";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout1 />}>
          <Route index element={<Main />} />
          <Route path="/search" element={<Search />} />
        </Route>
        <Route element={<Layout2 />}>
          <Route path="/request" element={<RequestForm />} />
          <Route path="/user/:uid" element={<UserInfo />} />
          <Route path="/user/:uid/:follow" element={<FollowList />} />
        </Route>
        <Route element={<Layout3 />}>
          <Route path="/user/:uid/pick" element={<UserPickMap />} />
          <Route path="/user/:uid/wish" element={<UserWishMap />} />
        </Route>
        <Route path="/store/:sid" element={<StoreInfo />} />
        <Route path="/store/review" element={<ReviewForm />} />
        <Route path="/theme/:tid" element={<ThemeMap />} />
        <Route path="/theme/search/:tid" element={<SearchStore />} />
        <Route path="/transition/login" element={<LoginData />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
