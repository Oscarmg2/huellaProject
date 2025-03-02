import axios from "axios";

const NEIGHBORHOOD_BASE_REST_API_URL="http://localhost:8080/neighborhood";
class NeighborhoodServices {
    getAllNeighborhoods() {
        return axios.get(NEIGHBORHOOD_BASE_REST_API_URL);
    }
}

export default new NeighborhoodServices();