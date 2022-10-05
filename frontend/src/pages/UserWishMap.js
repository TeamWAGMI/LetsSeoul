import React from "react";
import { Map } from "react-kakao-maps-sdk";

function UserWishMap() {
  return (
    <Map
      center={{ lat: 37.566769, lng: 126.978323 }}
      style={{ width: "100%", height: "100vh" }}
      level={9}
    ></Map>
  );
}

export default UserWishMap;
