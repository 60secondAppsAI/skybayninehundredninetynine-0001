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
            <seatingClass-table
            v-if="seatingClasss && seatingClasss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:seatingClasss="seatingClasss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seating-classs="getAllSeatingClasss"
             >

            </seatingClass-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SeatingClassTable from "@/components/SeatingClassTable";
import SeatingClassService from "../services/SeatingClassService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SeatingClassTable,
  },
  data() {
    return {
      seatingClasss: [],
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
    async getAllSeatingClasss(sortBy='seatingClassId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SeatingClassService.getAllSeatingClasss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.seatingClasss.length) {
					this.seatingClasss = response.data.seatingClasss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching seatingClasss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seatingClass details:", error);
      }
    },
  },
  mounted() {
    this.getAllSeatingClasss();
  },
  created() {
    this.$root.$on('searchQueryForSeatingClasssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSeatingClasss();
    })
  }
};
</script>
<style></style>
