import http from "../http-common"; 

class ReservationAgentService {
  getAllReservationAgents(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/reservationAgent/reservationAgents`, searchDTO);
  }

  get(reservationAgentId) {
    return this.getRequest(`/reservationAgent/${reservationAgentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/reservationAgent?field=${matchData}`, null);
  }

  addReservationAgent(data) {
    return http.post("/reservationAgent/addReservationAgent", data);
  }

  update(data) {
  	return http.post("/reservationAgent/updateReservationAgent", data);
  }
  
  uploadImage(data,reservationAgentId) {
  	return http.postForm("/reservationAgent/uploadImage/"+reservationAgentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ReservationAgentService();
