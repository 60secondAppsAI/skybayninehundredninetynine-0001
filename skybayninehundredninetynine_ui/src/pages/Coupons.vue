<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <coupon-table
            v-if="coupons && coupons.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:coupons="coupons"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-coupons="getAllCoupons"
             >

            </coupon-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CouponTable from "@/components/CouponTable";
import CouponService from "../services/CouponService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CouponTable,
  },
  data() {
    return {
      coupons: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllCoupons(sortBy='couponId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CouponService.getAllCoupons(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.coupons.length) {
					this.coupons = response.data.coupons;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching coupons:", error);
        }
        
      } catch (error) {
        console.error("Error fetching coupon details:", error);
      }
    },
  },
  mounted() {
    this.getAllCoupons();
  },
  created() {
    this.$root.$on('searchQueryForCouponsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCoupons();
    })
  }
};
</script>
<style></style>
