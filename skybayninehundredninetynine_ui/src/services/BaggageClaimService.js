import http from "../http-common"; 

class BaggageClaimService {
  getAllBaggageClaims(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/baggageClaim/baggageClaims`, searchDTO);
  }

  get(baggageClaimId) {
    return this.getRequest(`/baggageClaim/${baggageClaimId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/baggageClaim?field=${matchData}`, null);
  }

  addBaggageClaim(data) {
    return http.post("/baggageClaim/addBaggageClaim", data);
  }

  update(data) {
  	return http.post("/baggageClaim/updateBaggageClaim", data);
  }
  
  uploadImage(data,baggageClaimId) {
  	return http.postForm("/baggageClaim/uploadImage/"+baggageClaimId, data);
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

export default new BaggageClaimService();
