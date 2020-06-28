import axios from "axios";

const AxiosService = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
});

export default AxiosService;
