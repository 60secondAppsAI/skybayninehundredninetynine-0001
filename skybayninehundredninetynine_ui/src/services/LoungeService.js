import http from "../http-common"; 

class LoungeService {
  getAllLounges(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/lounge/lounges`, searchDTO);
  }

  get(loungeId) {
    return this.getRequest(`/lounge/${loungeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/lounge?field=${matchData}`, null);
  }

  addLounge(data) {
    return http.post("/lounge/addLounge", data);
  }

  update(data) {
  	return http.post("/lounge/updateLounge", data);
  }
  
  uploadImage(data,loungeId) {
  	return http.postForm("/lounge/uploadImage/"+loungeId, data);
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

export default new LoungeService();
