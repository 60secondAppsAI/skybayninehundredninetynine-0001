import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airplanes from  '@/pages/Airplanes.vue';
import AirplaneDetail from  '@/pages/AirplaneDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import SeatingClasss from  '@/pages/SeatingClasss.vue';
import SeatingClassDetail from  '@/pages/SeatingClassDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import CrewMembers from  '@/pages/CrewMembers.vue';
import CrewMemberDetail from  '@/pages/CrewMemberDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Luggages from  '@/pages/Luggages.vue';
import LuggageDetail from  '@/pages/LuggageDetail.vue';
import Schedules from  '@/pages/Schedules.vue';
import ScheduleDetail from  '@/pages/ScheduleDetail.vue';
import Gates from  '@/pages/Gates.vue';
import GateDetail from  '@/pages/GateDetail.vue';
import BaggageClaims from  '@/pages/BaggageClaims.vue';
import BaggageClaimDetail from  '@/pages/BaggageClaimDetail.vue';
import ReservationAgents from  '@/pages/ReservationAgents.vue';
import ReservationAgentDetail from  '@/pages/ReservationAgentDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import Maintenances from  '@/pages/Maintenances.vue';
import MaintenanceDetail from  '@/pages/MaintenanceDetail.vue';
import Lounges from  '@/pages/Lounges.vue';
import LoungeDetail from  '@/pages/LoungeDetail.vue';
import SecurityChecks from  '@/pages/SecurityChecks.vue';
import SecurityCheckDetail from  '@/pages/SecurityCheckDetail.vue';
import Coupons from  '@/pages/Coupons.vue';
import CouponDetail from  '@/pages/CouponDetail.vue';
import FrequentFlyers from  '@/pages/FrequentFlyers.vue';
import FrequentFlyerDetail from  '@/pages/FrequentFlyerDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airplanes',
		name: 'Airplanes',
		layout: DefaultLayout,
		component: Airplanes,
	},
	{
	    path: '/airplane/:airplaneId', 
	    name: 'AirplaneDetail',
		layout: DefaultLayout,
	    component: AirplaneDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seatingClasss',
		name: 'SeatingClasss',
		layout: DefaultLayout,
		component: SeatingClasss,
	},
	{
	    path: '/seatingClass/:seatingClassId', 
	    name: 'SeatingClassDetail',
		layout: DefaultLayout,
	    component: SeatingClassDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewMembers',
		name: 'CrewMembers',
		layout: DefaultLayout,
		component: CrewMembers,
	},
	{
	    path: '/crewMember/:crewMemberId', 
	    name: 'CrewMemberDetail',
		layout: DefaultLayout,
	    component: CrewMemberDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/luggages',
		name: 'Luggages',
		layout: DefaultLayout,
		component: Luggages,
	},
	{
	    path: '/luggage/:luggageId', 
	    name: 'LuggageDetail',
		layout: DefaultLayout,
	    component: LuggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/schedules',
		name: 'Schedules',
		layout: DefaultLayout,
		component: Schedules,
	},
	{
	    path: '/schedule/:scheduleId', 
	    name: 'ScheduleDetail',
		layout: DefaultLayout,
	    component: ScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/gates',
		name: 'Gates',
		layout: DefaultLayout,
		component: Gates,
	},
	{
	    path: '/gate/:gateId', 
	    name: 'GateDetail',
		layout: DefaultLayout,
	    component: GateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggageClaims',
		name: 'BaggageClaims',
		layout: DefaultLayout,
		component: BaggageClaims,
	},
	{
	    path: '/baggageClaim/:baggageClaimId', 
	    name: 'BaggageClaimDetail',
		layout: DefaultLayout,
	    component: BaggageClaimDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reservationAgents',
		name: 'ReservationAgents',
		layout: DefaultLayout,
		component: ReservationAgents,
	},
	{
	    path: '/reservationAgent/:reservationAgentId', 
	    name: 'ReservationAgentDetail',
		layout: DefaultLayout,
	    component: ReservationAgentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenances',
		name: 'Maintenances',
		layout: DefaultLayout,
		component: Maintenances,
	},
	{
	    path: '/maintenance/:maintenanceId', 
	    name: 'MaintenanceDetail',
		layout: DefaultLayout,
	    component: MaintenanceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/lounges',
		name: 'Lounges',
		layout: DefaultLayout,
		component: Lounges,
	},
	{
	    path: '/lounge/:loungeId', 
	    name: 'LoungeDetail',
		layout: DefaultLayout,
	    component: LoungeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/securityChecks',
		name: 'SecurityChecks',
		layout: DefaultLayout,
		component: SecurityChecks,
	},
	{
	    path: '/securityCheck/:securityCheckId', 
	    name: 'SecurityCheckDetail',
		layout: DefaultLayout,
	    component: SecurityCheckDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coupons',
		name: 'Coupons',
		layout: DefaultLayout,
		component: Coupons,
	},
	{
	    path: '/coupon/:couponId', 
	    name: 'CouponDetail',
		layout: DefaultLayout,
	    component: CouponDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/frequentFlyers',
		name: 'FrequentFlyers',
		layout: DefaultLayout,
		component: FrequentFlyers,
	},
	{
	    path: '/frequentFlyer/:frequentFlyerId', 
	    name: 'FrequentFlyerDetail',
		layout: DefaultLayout,
	    component: FrequentFlyerDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
