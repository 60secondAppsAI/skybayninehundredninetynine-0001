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
            <reservationAgent-table
            v-if="reservationAgents && reservationAgents.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:reservationAgents="reservationAgents"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-reservation-agents="getAllReservationAgents"
             >

            </reservationAgent-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ReservationAgentTable from "@/components/ReservationAgentTable";
import ReservationAgentService from "../services/ReservationAgentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ReservationAgentTable,
  },
  data() {
    return {
      reservationAgents: [],
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
    async getAllReservationAgents(sortBy='reservationAgentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ReservationAgentService.getAllReservationAgents(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.reservationAgents.length) {
					this.reservationAgents = response.data.reservationAgents;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching reservationAgents:", error);
        }
        
      } catch (error) {
        console.error("Error fetching reservationAgent details:", error);
      }
    },
  },
  mounted() {
    this.getAllReservationAgents();
  },
  created() {
    this.$root.$on('searchQueryForReservationAgentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllReservationAgents();
    })
  }
};
</script>
<style></style>
