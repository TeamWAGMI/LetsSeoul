/*global kakao */
import React, { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import { useParams } from "react-router-dom";

function ThemeMap() {
  //const [center, setCenter] = useState();
  const [themeData, SetThemeData] = useState([]);
  const { tid } = useParams();

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_SERVER}/api/v1/themes/${tid}`, {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res);
        SetThemeData(res.data);
        mapScript();
      })
      .catch((err) => console.log(err));
  }, []);

  const mapScript = () => {
    let container = document.getElementById("map");
    let options = {
      center: new kakao.maps.LatLng(37.566769, 126.978323),
      level: 6,
    };
    // 지도 생성
    const map = new kakao.maps.Map(container, options);

    const imageSrc = "/images/marker.svg", // 마커이미지의 주소
      imageSize = new kakao.maps.Size(40, 30), // 마커이미지의 크기
      imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성
    var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      ),
      // 마커가 표시될 위치
      markerPosition = new kakao.maps.LatLng(37.54699, 127.09598);
    themeData.map((el) => {
      //마커 생성
      const marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(el.lat, el.lng),
        image: markerImage,
      });
      marker.setMap(map);

      // 인포윈도우에 표시할 내용
      const infowindow = new kakao.maps.InfoWindow({
        content: `<div style="width:150px; text-align:center; padding:3px 0; line-height:20px; font-size:15px; border-radius:50px 50px">
            <div> ${el.storeTitle} ${el.reviewCount}</div>
            </div>`,
      });
      //마커에 mouseover 이벤트와 mouseout 이벤트 등록
      kakao.maps.event.addListener(
        marker,
        "mouseover",
        makeOverListener(map, marker, infowindow)
      );
      kakao.maps.event.addListener(
        marker,
        "mouseout",
        makeOutListener(infowindow)
      );
      // 마커에 클릭이벤트를 등록
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커 위에 인포윈도우를 표시
        infowindow.open(map, marker);
      });
    });
    // 인포윈도우 표시하기
    function makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    }
    // 인포윈도우 닫기
    function makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    }
  };

  return (
    <div>
      <div id="map" style={{ width: "100%", height: "100vh" }}></div>
    </div>
  );
}

export default ThemeMap;
