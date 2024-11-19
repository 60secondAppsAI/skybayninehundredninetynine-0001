import http from "../http-common"; 

class MaintenanceService {
  getAllMaintenances(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/maintenance/maintenances`, searchDTO);
  }

  get(maintenanceId) {
    return this.getRequest(`/maintenance/${maintenanceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/maintenance?field=${matchData}`, null);
  }

  addMaintenance(data) {
    return http.post("/maintenance/addMaintenance", data);
  }

  update(data) {
  	return http.post("/maintenance/updateMaintenance", data);
  }
  
  uploadImage(data,maintenanceId) {
  	return http.postForm("/maintenance/uploadImage/"+maintenanceId, data);
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

export default new MaintenanceService();
