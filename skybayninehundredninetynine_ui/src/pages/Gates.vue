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
            <gate-table
            v-if="gates && gates.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:gates="gates"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-gates="getAllGates"
             >

            </gate-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import GateTable from "@/components/GateTable";
import GateService from "../services/GateService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    GateTable,
  },
  data() {
    return {
      gates: [],
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
    async getAllGates(sortBy='gateId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await GateService.getAllGates(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.gates.length) {
					this.gates = response.data.gates;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching gates:", error);
        }
        
      } catch (error) {
        console.error("Error fetching gate details:", error);
      }
    },
  },
  mounted() {
    this.getAllGates();
  },
  created() {
    this.$root.$on('searchQueryForGatesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllGates();
    })
  }
};
</script>
<style></style>
