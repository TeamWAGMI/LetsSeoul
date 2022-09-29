import { useEffect, useState } from "react";

function LoginData() {
  const [query, setQuery] = useState(null);
  const [userdata, setUserdata] = useState([]);

  useEffect(() => {
    setQuery(window.location.search.slice(3));
    setUserdata(decodeURIComponent(escape(window.atob(query))).split(","));
  }, [query]);

  return (
    <div>
      <div>{userdata[0]}</div>
      <div>{userdata[1]}</div>
      <div>{userdata[2]}</div>
    </div>
  );
}

export default LoginData;
