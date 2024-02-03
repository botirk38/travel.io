"use client";

import { ClipboardIcon, HomeIcon, ListIcon, TowerControl } from "lucide-react";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCity } from "@fortawesome/free-solid-svg-icons";
import {
	Card,
	CardDescription,
	CardContent,
	CardTitle,
	CardHeader,
} from "@/components/ui/card";

import { ScrollArea } from "@/components/ui/scroll-area";

import {
	Accordion,
	AccordionContent,
	AccordionItem,
	AccordionTrigger,
} from "@/components/ui/accordion";

import Image from "next/image";
import { Badge } from "@/components/ui/badge";

export default function Page({ params }: { params: { id: string } }) {
	const planData = {
		id: params.id,
		name: "Exciting World Tour",
		destinations: [
			{
				name: "Paris",
				description:
					"The City of Lights, known for its culture, art, and architecture.",
				images: ["paris1.jpg", "paris2.jpg"],
				videos: ["https://www.youtube.com/watch?v=XC5ssX_RZa0"],
				likes: ["Eiffel Tower", "Art Museums"],
				localWonders: [
					{ name: "Louvre Museum", image: "louvre.jpg" },
					{ name: "Notre Dame", image: "notredame.jpg" },
				],
			},
			{
				name: "Tokyo",
				description:
					"A bustling metropolis blending the ultramodern and the traditional.",
				images: ["tokyo1.jpg", "tokyo2.jpg"],
				videos: ["https://www.youtube.com/watch?v=a4ruD_iQKlk"],
				likes: ["Anime", "Technology"],
				localWonders: [
					{ name: "Tokyo Tower", image: "tokyotower.jpg" },
					{ name: "Sensō-ji Temple", image: "sensoji.jpg" },
				],
			},
			{
				name: "Cairo",
				description: "The heart of Egypt, home to millennia-old monuments.",
				images: ["cairo1.jpg", "cairo2.jpg"],
				videos: ["https://www.youtube.com/watch?v=RKzeyrlzwt4"],
				likes: ["Pyramids", "Nile River"],
				localWonders: [
					{ name: "Great Pyramid of Giza", image: "pyramid.jpg" },
					{ name: "Sphinx", image: "sphinx.jpg" },
				],
			},
		],
	};

	for (let i = 0; i < planData.destinations.length; i++) {
		for (let j = 0; j < planData.destinations[i].videos.length; j++) {
			const videoURL = new URL(planData.destinations[i].videos[j]);
			const videoID = new URLSearchParams(videoURL.search).get("v");
			planData.destinations[i].videos[j] =
				`https://www.youtube.com/embed/${videoID}`;
		}
	}

	return (
		<main className="flex relative w-full justify-end items-end min-h-screen">
			<nav className=" w-48 px-3 py-5 left-0 top-0 fixed  flex flex-col items-center justify-between lg:px-6 lg:py-10 shadow-xl min-h-screen lg:w-52 gap-10 xl:w-72">
				<ul className="flex flex-col justify-start text-start items-start w-full gap-6">
					{planData.destinations.map((destination) => (
						<li
							key={destination.name}
							className="font-bold hover:text-blue-500 flex justify-center items-center gap-2"
						>
							<FontAwesomeIcon icon={faCity} />
							{destination.name}
						</li>
					))}
				</ul>

				<ul className="flex flex-col justify-center text-center items-start w-full gap-6 border-t py-5">
					<Link
						className="font-bold hover:text-blue-500 flex justify-center items-center gap-2"
						href={"/plans"}
					>
						<ClipboardIcon />
						Previous plans
					</Link>
					<Link
						className="font-bold hover:text-blue-500 flex justify-center items-center gap-2"
						href={"/home"}
					>
						<HomeIcon />
						Home
					</Link>
				</ul>
			</nav>

			<section className="flex flex-col w-2/4 p-6 justify-start items-center gap-6 lg:p-10 lg:w-3/4 lg:gap-10 lg:min-h-screen">
				{planData.destinations.map((destination) => (
					<Card key={destination.name} className="w-full">
						<CardHeader>
							<CardTitle>{destination.name} </CardTitle>
							<CardDescription> {destination.description} </CardDescription>
						</CardHeader>

						<CardContent>
							<Accordion type="single" collapsible className="w-full">
								<AccordionItem value="images">
									<AccordionTrigger> Images </AccordionTrigger>
									<AccordionContent>
										<ScrollArea className="w-full whitespace-nowrap rounded-md border">
											<div className="flex w-max space-x-10 p-4">
												{destination.images.map((image, index) => (
													<figure key={image + index}>
														<Image
															src={`/${image}`}
															alt={"Image of destination"}
															className="aspect-[3/4] h-fit w-fit object-cover"
															width={300}
															height={400}
														/>
													</figure>
												))}
											</div>
										</ScrollArea>
									</AccordionContent>
								</AccordionItem>

								<AccordionItem value="things_like">
									<AccordionTrigger> Most Liked Aspects </AccordionTrigger>

									<AccordionContent className="space-x-4">
										{destination.likes.map((like, index) => (
											<Badge key={like + index}>{like} </Badge>
										))}
									</AccordionContent>
								</AccordionItem>

								<AccordionItem value="videos">
									<AccordionTrigger> Videos </AccordionTrigger>

									<AccordionContent>
										<ScrollArea className="w-full whitespace-nowrap rounded-md border">
											<div className="flex w-max space-x-10 p-4">
												{destination.videos.map((video, index) => (
													<figure key={video + index}>
														<iframe
															src={video}
															title="Video"
															allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
															allowFullScreen
														/>
													</figure>
												))}
											</div>
										</ScrollArea>
									</AccordionContent>
								</AccordionItem>

								<AccordionItem value="local-wonders">
									<AccordionTrigger> Local Wonders </AccordionTrigger>

									<AccordionContent>
										<ScrollArea className="w-full whitepsace-nowrap rounded-md border">
											<div className="flex w-max space-x-10 p-4">
												{destination.localWonders.map((wonder, index) => (
													<figure key={wonder.name}>
														<div className="overflow-hidden rounded-md">
															<Image
																src={`/${wonder.image}`}
																alt={`Picture of ${wonder.name}`}
																width={300}
																height={400}
															/>
														</div>

														<figcaption className="pt-2 text-xs text-muted-foreground">
															Photo by{" "}
															<span className="font-semibold text-foreground">
																{wonder.name}
															</span>
														</figcaption>
													</figure>
												))}
											</div>
										</ScrollArea>
									</AccordionContent>
								</AccordionItem>
							</Accordion>
						</CardContent>
					</Card>
				))}
			</section>
		</main>
	);
}
