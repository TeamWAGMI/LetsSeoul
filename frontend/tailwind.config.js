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
        borderGray: "#E0E0E0",
        modalBg: "rgba(0, 0, 0, 0.5)",
      },
      dropShadow: {
        upButton: "0 20px 20px rgba(0, 0, 0, 0.25)",
      },
    },
  },
  plugins: [],
};
