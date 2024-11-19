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
            <lounge-table
            v-if="lounges && lounges.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:lounges="lounges"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-lounges="getAllLounges"
             >

            </lounge-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LoungeTable from "@/components/LoungeTable";
import LoungeService from "../services/LoungeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LoungeTable,
  },
  data() {
    return {
      lounges: [],
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
    async getAllLounges(sortBy='loungeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LoungeService.getAllLounges(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.lounges.length) {
					this.lounges = response.data.lounges;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching lounges:", error);
        }
        
      } catch (error) {
        console.error("Error fetching lounge details:", error);
      }
    },
  },
  mounted() {
    this.getAllLounges();
  },
  created() {
    this.$root.$on('searchQueryForLoungesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLounges();
    })
  }
};
</script>
<style></style>
