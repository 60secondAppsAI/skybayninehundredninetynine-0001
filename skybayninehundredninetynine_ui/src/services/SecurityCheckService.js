import http from "../http-common"; 

class SecurityCheckService {
  getAllSecurityChecks(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/securityCheck/securityChecks`, searchDTO);
  }

  get(securityCheckId) {
    return this.getRequest(`/securityCheck/${securityCheckId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/securityCheck?field=${matchData}`, null);
  }

  addSecurityCheck(data) {
    return http.post("/securityCheck/addSecurityCheck", data);
  }

  update(data) {
  	return http.post("/securityCheck/updateSecurityCheck", data);
  }
  
  uploadImage(data,securityCheckId) {
  	return http.postForm("/securityCheck/uploadImage/"+securityCheckId, data);
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

export default new SecurityCheckService();
