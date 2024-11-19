import http from "../http-common"; 

class CouponService {
  getAllCoupons(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/coupon/coupons`, searchDTO);
  }

  get(couponId) {
    return this.getRequest(`/coupon/${couponId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/coupon?field=${matchData}`, null);
  }

  addCoupon(data) {
    return http.post("/coupon/addCoupon", data);
  }

  update(data) {
  	return http.post("/coupon/updateCoupon", data);
  }
  
  uploadImage(data,couponId) {
  	return http.postForm("/coupon/uploadImage/"+couponId, data);
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

export default new CouponService();
