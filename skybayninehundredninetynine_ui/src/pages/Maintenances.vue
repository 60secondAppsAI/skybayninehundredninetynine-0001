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
            <maintenance-table
            v-if="maintenances && maintenances.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:maintenances="maintenances"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-maintenances="getAllMaintenances"
             >

            </maintenance-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MaintenanceTable from "@/components/MaintenanceTable";
import MaintenanceService from "../services/MaintenanceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MaintenanceTable,
  },
  data() {
    return {
      maintenances: [],
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
    async getAllMaintenances(sortBy='maintenanceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MaintenanceService.getAllMaintenances(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.maintenances.length) {
					this.maintenances = response.data.maintenances;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching maintenances:", error);
        }
        
      } catch (error) {
        console.error("Error fetching maintenance details:", error);
      }
    },
  },
  mounted() {
    this.getAllMaintenances();
  },
  created() {
    this.$root.$on('searchQueryForMaintenancesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMaintenances();
    })
  }
};
</script>
<style></style>
