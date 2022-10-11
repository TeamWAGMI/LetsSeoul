import { BrowserRouter, Route, Routes } from "react-router-dom";
import Layout1 from "layout/Layout1";
import Layout2 from "layout/Layout2";
import Main from "pages/Main";
import SearchTheme from "pages/SearchTheme";
import UserInfo from "pages/UserInfo";
import FollowList from "pages/FollowList";
import RequestForm from "pages/RequestForm";
import ReviewForm from "pages/ReviewForm";
import EditForm from "pages/EditForm";
import StoreInfo from "pages/StoreInfo";
import ThemeMap from "pages/ThemeMap";
import SearchMap from "pages/SearchMap";
import UserPickMap from "pages/UserPickMap";
import UserWishMap from "pages/UserWishMap";
import LoginData from "pages/LoginData";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout1 />}>
          <Route index element={<Main />} />
          <Route path="/search" element={<SearchTheme />} />
          <Route path="/request" element={<RequestForm />} />
          <Route path="/user/:uid" element={<UserInfo />} />
          <Route path="/user/:uid/:follow" element={<FollowList />} />
        </Route>
        <Route element={<Layout2 />}>
          <Route path="/user/:uid/pick" element={<UserPickMap />} />
          <Route path="/user/:uid/wish" element={<UserWishMap />} />
        </Route>
        <Route path="/store/:sid" element={<StoreInfo />} />
        <Route path="/store/review" element={<ReviewForm />} />
        <Route path="/store/review/edit" element={<EditForm />} />
        <Route path="/theme/:tid" element={<ThemeMap />} />
        <Route path="/theme/search/:tid" element={<SearchMap />} />
        <Route path="/transition/login" element={<LoginData />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
