import { Button } from "@/components/ui/button";
import { faBook } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";

import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from "@/components/ui/card"
import { redirect } from "next/navigation";

export default function Plans() {


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

	];


	return (

		<section className="w-full">

			<div className="flex justify-start items-center gap-2 p-2 md:justify-between md:w-full">

				<FontAwesomeIcon icon={faBook} className="" />

				<Button variant={"link"} className="p-0" > <Link href="/plans" className="underline text-blue-500">View All </Link> </Button>


			</div>


			<div className="flex flex-col justify-center items-center gap-6 w-full md:grid md:grid-cols-3">
				{plans.map((plan) => (

					<Link href={`/plans/${plan.id}`} key={plan.id} className="w-full">
						<Card className="w-full">

							<CardHeader>
								<CardTitle>{plan.name} </CardTitle>
							</CardHeader>


							<CardContent>


							</CardContent>

							<CardFooter>


								{plan.destinations.join(" -> ")}

							</CardFooter>


						</Card>
					</Link>
				))}

			</div>



		</section>


	);

}
