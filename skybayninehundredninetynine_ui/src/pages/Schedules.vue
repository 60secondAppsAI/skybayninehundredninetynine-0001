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
            <schedule-table
            v-if="schedules && schedules.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:schedules="schedules"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-schedules="getAllSchedules"
             >

            </schedule-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ScheduleTable from "@/components/ScheduleTable";
import ScheduleService from "../services/ScheduleService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ScheduleTable,
  },
  data() {
    return {
      schedules: [],
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
    async getAllSchedules(sortBy='scheduleId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ScheduleService.getAllSchedules(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.schedules.length) {
					this.schedules = response.data.schedules;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching schedules:", error);
        }
        
      } catch (error) {
        console.error("Error fetching schedule details:", error);
      }
    },
  },
  mounted() {
    this.getAllSchedules();
  },
  created() {
    this.$root.$on('searchQueryForSchedulesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSchedules();
    })
  }
};
</script>
<style></style>
