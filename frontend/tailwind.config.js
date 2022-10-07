/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      fontFamily: {},
      colors: {
        wagmiGreen: "#55AB67",
        wagmiLightGreen: "#DEDE26",
        textWhite: "#FFFFFF",
        textGray: "#999999",
        textDarkGray: "#555555",
        bgGray: "#EDEDED",
        borderGray: "#E0E0E0",
        modalBg: "rgba(0, 0, 0, 0.5)",
        heartRed: "#ff0000",
      },
      dropShadow: {
        up: "0 20px 20px rgba(0, 0, 0, 0.25)",
      },
    },
    screens: {
      desktop: "500px",
    },
  },
  plugins: [],
};
