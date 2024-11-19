import http from "../http-common"; 

class GateService {
  getAllGates(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/gate/gates`, searchDTO);
  }

  get(gateId) {
    return this.getRequest(`/gate/${gateId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/gate?field=${matchData}`, null);
  }

  addGate(data) {
    return http.post("/gate/addGate", data);
  }

  update(data) {
  	return http.post("/gate/updateGate", data);
  }
  
  uploadImage(data,gateId) {
  	return http.postForm("/gate/uploadImage/"+gateId, data);
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

export default new GateService();
