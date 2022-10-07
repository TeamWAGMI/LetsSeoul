import React, { useEffect, useState } from "react";

const { kakao } = window;

const SearchStore = (props) => {
  const { searchPlace } = props;
  const [Places, setPlaces] = useState([]);

  useEffect(() => {
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    const container = document.getElementById("myMap");
    const options = {
      center: new kakao.maps.LatLng(37.566769, 126.978323),
      level: 10,
    };
    const map = new kakao.maps.Map(container, options);

    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(searchPlace, placesSearchCB);

    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        let bounds = new kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i++) {
          displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        map.setBounds(bounds);
        displayPagination(pagination);
        setPlaces(data);
      }
    }

    function displayPagination(pagination) {
      let paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        let el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    }

    function displayMarker(place) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      kakao.maps.event.addListener(marker, "click", function () {
        infowindow.setContent(place.place_name);
        infowindow.open(map, marker);
      });
    }
  }, [searchPlace]);

  return (
    <div className="realtive overflow-hidden w-full">
      <div className="absolute overflow-scroll no-scrollbar z-30 max-h-[295px] mt-[500px] w-96 ml-7 scroll-smooth">
        {Places.map((item, i) => (
          <div
            className="padding-container mb-2 rounded-lg bg-wagmiGreen text-white cursor-pointer overflow-auto "
            key={i}
          >
            <div className="font-semibold text-lg">
              {i + 1}. {item.place_name}
            </div>
            <span className="text-sm">{item.address_name}</span>
          </div>
        ))}
        <div
          className="absolute z-34 cursor-pointer ml-40 flex space-x-4 bg-wagmiLightGreen text-textWhite text-sm font-semibold rounded-lg py-[8px] px-4 "
          id="pagination"
        ></div>
      </div>
      <div className="w-full h-screen" id="myMap"></div>
    </div>
  );
};


export default SearchStore;
