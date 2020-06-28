import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import LinearProgress from "@material-ui/core/LinearProgress";

import TextField from "@material-ui/core/TextField";
import Autocomplete from "@material-ui/lab/Autocomplete";

import Countdown from "react-countdown";

import FindStopService from "../services/FindStopService";

var undef;

const SearchStop = (props) => {
  const fetchData = async () => {
    setSearching(true);
    const result = await FindStopService.getAll();
    setSearching(false);
    console.log("React", result);

    setStops(result.data.locations);
  };

  const fetchData1 = async (searchText) => {
    setSearching(true);
    const result = await FindStopService.getByName(searchText);
    setSearching(false);
    //console.log(result);

    setStops(result.data.locations);
  };

  const fetchData2 = async () => {
    var undef;
    if (selectedStop != undef) {
      setSearching(true);
      const result = await FindStopService.getDepartures(selectedStop.id);
      setSearching(false);

      console.log("Departures:", result.data.stopEvents);
      setDeps(result.data.stopEvents);
    }
  };

  const [stops, setStops] = useState([]);

  const [selectedStop, setSelectedStop] = useState();
  const [searchText, setSearchText] = useState();
  const [deps, setDeps] = useState([]);

  const [searching, setSearching] = useState(false);

  const textChanged = (e) => {
    //console.log(event.value);
    console.log(e.target.value);
    setSearchText(e.target.value);
  };

  //first cal only
  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    console.log("selectedStop:", selectedStop);
    var undef;
    if (selectedStop != undef) {
      fetchData2();
    }
  }, [selectedStop]);

  useEffect(() => {
    console.log("searchText:", searchText);
    fetchData1(searchText);
  }, [searchText]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",

        width: "35%",
        margin: "auto",
      }}
    >
      <Autocomplete
        onChange={(event, newValue) => {
          setSelectedStop(newValue);
        }}
        id="controllable-states-demo"
        options={stops}
        getOptionLabel={(option) => option.name}
        renderInput={(params) => (
          <TextField
            {...params}
            label="Stop Name"
            value={searchText}
            onChange={(e) => {
              textChanged(e);
            }}
            variant="outlined"
          />
        )}
      />

      <div>
        {searching && (
          <div>
            <LinearProgress />
            <lablel>Loading...</lablel>
          </div>
        )}
      </div>

      <div
        style={{
          background: "#000",
          borderRadius: "10px",
          marginTop: "50px",
          height: "600px",
        }}
      >
        <label style={{ color: "#3faf3f", fontSize: 30, margin: "20px" }}>
          Departues: {selectedStop != undef && selectedStop.name}
        </label>
        <hr />
        {deps.length > 0 ? (
          <div
            style={{
              height: "500px",
              overflowY: "auto",
              background: "#000",
            }}
          >
            <table style={{ listStyle: "none", color: "#3faf3f" }}>
              <tbody>
                {deps.map((dep, index) => (
                  <tr key={index}>
                    <td>
                      <label>{index + 1}</label>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td>
                      {index % 4 == 0 ? (
                        <marquee>{dep.location.name}</marquee>
                      ) : (
                        <label style={{ color: "#3faf3f" }}>
                          {dep.transportation.destination.name}
                        </label>
                      )}
                    </td>
                    <td>&nbsp;</td>
                    <td>
                      <label>{"Time Left (HH:mm:ss):"}</label>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                      <b>
                        <Countdown
                          daysInHours={true}
                          date={parseInt(dep.departureTimePlanned)}
                        />
                      </b>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td>
                      <label>{"Operator:"}</label>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td>
                      <label style={{ color: "#3faf3f" }}>
                        {dep.transportation.operator.name}
                      </label>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          ""
        )}
      </div>
    </div>
  );
};

export default SearchStop;
