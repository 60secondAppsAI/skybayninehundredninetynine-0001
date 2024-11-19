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
            <securityCheck-table
            v-if="securityChecks && securityChecks.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:securityChecks="securityChecks"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-security-checks="getAllSecurityChecks"
             >

            </securityCheck-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SecurityCheckTable from "@/components/SecurityCheckTable";
import SecurityCheckService from "../services/SecurityCheckService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SecurityCheckTable,
  },
  data() {
    return {
      securityChecks: [],
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
    async getAllSecurityChecks(sortBy='securityCheckId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SecurityCheckService.getAllSecurityChecks(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.securityChecks.length) {
					this.securityChecks = response.data.securityChecks;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching securityChecks:", error);
        }
        
      } catch (error) {
        console.error("Error fetching securityCheck details:", error);
      }
    },
  },
  mounted() {
    this.getAllSecurityChecks();
  },
  created() {
    this.$root.$on('searchQueryForSecurityChecksChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSecurityChecks();
    })
  }
};
</script>
<style></style>
