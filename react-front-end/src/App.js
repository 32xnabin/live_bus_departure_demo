import React from "react";

import "./App.css";

import SearchStop from "./components/SearchStop";

require("dotenv").config();

function App() {
  return (
    <div className="App">
      <div style={{ margin: "10px" }}>
        <label>Demo Application for Live bus departurs in Australia</label>
      </div>

      <SearchStop />
    </div>
  );
}

export default App;
