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
            <baggageClaim-table
            v-if="baggageClaims && baggageClaims.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:baggageClaims="baggageClaims"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-baggage-claims="getAllBaggageClaims"
             >

            </baggageClaim-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BaggageClaimTable from "@/components/BaggageClaimTable";
import BaggageClaimService from "../services/BaggageClaimService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BaggageClaimTable,
  },
  data() {
    return {
      baggageClaims: [],
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
    async getAllBaggageClaims(sortBy='baggageClaimId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BaggageClaimService.getAllBaggageClaims(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.baggageClaims.length) {
					this.baggageClaims = response.data.baggageClaims;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching baggageClaims:", error);
        }
        
      } catch (error) {
        console.error("Error fetching baggageClaim details:", error);
      }
    },
  },
  mounted() {
    this.getAllBaggageClaims();
  },
  created() {
    this.$root.$on('searchQueryForBaggageClaimsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBaggageClaims();
    })
  }
};
</script>
<style></style>
