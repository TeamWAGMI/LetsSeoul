import React, { useEffect } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  useEffect(() => {
    let code = new URL(window.location.href).searchParams.get("code");
    axios
      .post(
        `https://kauth.kakao.com/oauth/token?grant_type=authorization_code&code=${code}&client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/login/oauth2/authurl/kakao`
      )
      .then((res) => {
        axios
          .get(`https://kapi.kakao.com/v2/user/me`, {
            headers: {
              Authorization: `Bearer ${res.data.access_token}`,
            },
          })
          .then((res) => {
            // alert(res.data.properties.nickname.toString());
            console.log(res.data);
            navigate("/");
          });
        //   .catch((err) => console.log(err));
      })
      .catch((err) => console.log("카카오 로그인 에러", err));
  }, []);
  return <div>Login</div>;
}

export default Login;
