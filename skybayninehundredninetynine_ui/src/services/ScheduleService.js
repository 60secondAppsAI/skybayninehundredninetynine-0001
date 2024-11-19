import http from "../http-common"; 

class ScheduleService {
  getAllSchedules(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/schedule/schedules`, searchDTO);
  }

  get(scheduleId) {
    return this.getRequest(`/schedule/${scheduleId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/schedule?field=${matchData}`, null);
  }

  addSchedule(data) {
    return http.post("/schedule/addSchedule", data);
  }

  update(data) {
  	return http.post("/schedule/updateSchedule", data);
  }
  
  uploadImage(data,scheduleId) {
  	return http.postForm("/schedule/uploadImage/"+scheduleId, data);
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

export default new ScheduleService();
