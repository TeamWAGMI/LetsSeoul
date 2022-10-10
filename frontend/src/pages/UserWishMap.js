import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { Map, MapMarker, CustomOverlayMap } from "react-kakao-maps-sdk";
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
          <CustomOverlayMap
            position={{ lat: store.latitude, lng: store.longitude }}
            yAnchor={1.9}
            xAnchor={0.5}
          >
            <div className="relative bg-white rounded-3xl py-3 px-8 text-center shadow-xl shadow-black-10">
              <Link to={`/store/${store.storeId}`}>
                <div className="font-semibold text-wagmiGreen hover:underline leading-none mb-2">
                  {store.storeName}
                </div>
              </Link>
              <div className="text-[0.5rem] leading-none">
                <span className="text-wagmiLightGreen mr-1">♥</span>
                <span className="text-textDarkGray">{store.reviewCount}</span>
              </div>
            </div>
            <div className="absolute border border-t-[10px] border-t-white bottom-[-10px] border-x-transparent border-x-[10px] border-b-transparent border-b-0 w-fit left-[calc(50%-10px)]"></div>
          </CustomOverlayMap>
        </div>
      ))}
    </Map>
  );
}

export default UserWishMap;
