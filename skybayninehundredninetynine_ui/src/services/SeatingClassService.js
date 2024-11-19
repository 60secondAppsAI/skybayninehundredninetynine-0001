import http from "../http-common"; 

class SeatingClassService {
  getAllSeatingClasss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seatingClass/seatingClasss`, searchDTO);
  }

  get(seatingClassId) {
    return this.getRequest(`/seatingClass/${seatingClassId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seatingClass?field=${matchData}`, null);
  }

  addSeatingClass(data) {
    return http.post("/seatingClass/addSeatingClass", data);
  }

  update(data) {
  	return http.post("/seatingClass/updateSeatingClass", data);
  }
  
  uploadImage(data,seatingClassId) {
  	return http.postForm("/seatingClass/uploadImage/"+seatingClassId, data);
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

export default new SeatingClassService();
