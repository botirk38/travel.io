"use client";

import { useState } from "react";

import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from "@/components/ui/card";
import CustomPagination from "@/components/ui/CustomPagination";
import MobileNav from "@/components/home/nav/MobileNav";
import Nav from "@/components/home/nav/Nav";
import { useQuery } from "react-query";
import { OAuthUser } from "@/types/userTypes";
import { getUserInfoClient } from "@/api/user/userInfoClient";


export default function PreviousPlansPage() {


	// Array of 10 plan objects with IDs
	const plans = [
		{
			id: 1,
			name: "Plan 1",
			destinations: ["Ldn", "Istanbul", "NYC"],
		},
		{
			id: 2,
			name: "Plan 2",
			destinations: ["Paris", "Tokyo", "Sydney"],
		},
		{
			id: 3,
			name: "Plan 3",
			destinations: ["Rome", "Athens", "Cairo"],
		},
		{
			id: 4,
			name: "Plan 4",
			destinations: ["Berlin", "Vienna", "Madrid"],
		},
		{
			id: 5,
			name: "Plan 5",
			destinations: ["San Fran", "Los Angeles", "Las Vegas"],
		},
		{
			id: 6,
			name: "Plan 6",
			destinations: ["Rio", "Buenos Aires", "Santiago"],
		},
		{
			id: 7,
			name: "Plan 7",
			destinations: ["Toronto", "Montreal", "Vancouver"],
		},
		{
			id: 8,
			name: "Plan 8",
			destinations: ["Dubai", "Abu Dhabi", "Doha"],
		},
		{
			id: 9,
			name: "Plan 9",
			destinations: ["Sydney", "Melbourne", "Brisbane"],
		},
		{
			id: 10,
			name: "Plan 10",
			destinations: ["New Delhi", "Mumbai", "Bangalore"],
		},
	];

	const [currentPage, setCurrentPage] = useState(1);
	const plansPerPage = 5; // Number of plans per page



	// Determine the plans to show on the current page
	const indexOfLastPlan = currentPage * plansPerPage;
	const indexOfFirstPlan = indexOfLastPlan - plansPerPage;
	const currentPlans = plans.slice(indexOfFirstPlan, indexOfLastPlan);

	// Handle pagination click
	const handlePaginationClick = (pageNumber: number) => {
		setCurrentPage(pageNumber);
	}

	const {data} = useQuery('userInfo', getUserInfoClient);


	return (
		<main className="flex flex-col justify-center items-start gap-16 p-8 lg:p-10 lg:justify-start lg:min-h-screen">

			<MobileNav data={data} />

			<Nav data={data} />

			<section className="flex flex-col justify-center items-center w-full gap-6  lg:grid lg:grid-cols-3 mt-10 ">
				{currentPlans.map((plan) => (
					<Card key={plan.id} className="w-full">
						<CardHeader>
							<CardTitle>{plan.name}</CardTitle>
						</CardHeader>
						<CardContent>
							<CardDescription>
								Destinations: {plan.destinations.join("-> ")}
							</CardDescription>
						</CardContent>
					</Card>
				))}

			</section>


			<section className="w-full flex justify-center items-center">
				<CustomPagination
					totalItems={plans.length}
					itemsPerPage={plansPerPage}
					currentPage={currentPage}
					onPageChange={handlePaginationClick}
				/>


			</section>

		</main >
	);
}

