import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import axios from "axios";

function UserWishMap() {
  const [storeList, setStoreList] = useState([]);
  const { uid } = useParams();

  useEffect(() => {
    axios
      .get(`/api/v1/locations/${uid}/wishes`)
      .then((res) => setStoreList(res.data));
  }, [uid]);

  return (
    <Map
      center={{ lat: 37.566769, lng: 126.978323 }}
      style={{ width: "100%", height: "100vh" }}
      level={9}
    >
      {storeList.map((store) => (
        <div key={store.storeId}>
          <MapMarker position={{ lat: store.latitude, lng: store.longitude }} />
        </div>
      ))}
    </Map>
  );
}

export default UserWishMap;
