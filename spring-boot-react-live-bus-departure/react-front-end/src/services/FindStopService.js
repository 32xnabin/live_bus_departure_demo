import AxiosService from "./AxiosService";

const getByName = (searchText) => {
  return AxiosService.get("http://18.185.3.35:8080/stops/" + searchText);
  // AxiosService.get("http://18.185.3.35:8080/stops/" + searchText, {
  //   headers: { "Access-Control-Allow-Origin": "*" },
  //   responseType: "json",
  // }).then((response) => {
  //   return response;
  // });
};
const getAll = () => {
  return AxiosService.get("http://18.185.3.35:8080/stops/");

  // AxiosService.get("http://18.185.3.35:8080/stops/", {
  //   headers: { "Access-Control-Allow-Origin": "*" },
  //   responseType: "json",
  // })
  //   .then((response) => {
  //     console.log(response);
  //     return response;
  //   })
  //   .catch(function (err) {
  //     console.log(err);
  //   });
};

const getDepartures = (stopId) => {
  return AxiosService.get("http://18.185.3.35:8080/departures/" + stopId);

  // AxiosService.get("http://18.185.3.35:8080/departures/" + stopId, {
  //   headers: { "Access-Control-Allow-Origin": "*" },
  //   responseType: "json",
  // }).then((response) => {
  //   return response;
  // });
};

export default {
  getAll,
  getByName,
  getDepartures,
};
