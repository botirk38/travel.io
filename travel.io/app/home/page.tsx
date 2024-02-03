"use client";

import { Button } from "@/components/ui/button"
import {
	Dialog,
	DialogContent,
	DialogDescription,
	DialogFooter,
	DialogHeader,
	DialogTitle,
	DialogTrigger,
} from "@/components/ui/dialog"


import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from "@/components/ui/card"

import { Label } from "@/components/ui/label";

import { Input } from "@/components/ui/input";

import { z } from "zod"

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"


import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from "@/components/ui/form"


const formSchema = z.object({
	question1: z.string().min(5),
	question2: z.string().min(5),
	question3: z.string().min(5),
	question4: z.string().min(5),
	question5: z.string().min(5),
	question6: z.string().min(5),
});



export default function Page() {

	const questions = ["1.Do you want to relax or be on an adventure?", "How long do you want to go away for?", "What's your budget?", "Are you more of cities, beaches or simply nature?", "What excites you the most - cultural exploration, outdoor adventures or culinary experiences?", "Will you be travelling alone, with a partner, family or friends?"];

	const form = useForm<z.infer<typeof formSchema>>({
		resolver: zodResolver(formSchema),
		defaultValues: {
			question1: "",
			question2: "",
			question3: "",
			question4: "",
			question5: "",
			question6: "",
		},
	})

	// 2. Define a submit handler.
	function onSubmit(values: z.infer<typeof formSchema>) {
		// Do something with the form values.
		// ✅ This will be type-safe and validated.
		console.log(values)
	}



	return (

		<main className="flex flex-col justify-center items-center gap-6">

			<h1 className="font-bold text-2xl"> Start travelling like a pro. </h1>

			<p className="text-gray-500"> Get a bird's eye view using the power of <span className="text-blue-500 font-bold"> AI </span> to plan your vacations. Make it easy. </p>


			<Dialog >

				<DialogTrigger asChild>

					<Button >Choose your dream Destination</Button>

				</DialogTrigger>


				<Form {...form}>

					<form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">

						<DialogContent className="sm:max-w-[425px] space-y-8">

							<DialogHeader className="space-y-4">

								<DialogTitle> What are you feeling like today</DialogTitle>

								<DialogDescription className="text-center">

									Answer a few straightforward questions, and we'll guide you to your dream destination.

								</DialogDescription>

							</DialogHeader>


							<FormField
								control={form.control}
								name="question1"
								render={({ field }) => (
									<FormItem>
										<FormLabel> 1.Do you want to relax or be on an adventure?</FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="question2"
								render={({ field }) => (
									<FormItem>

										<FormLabel> 2. How long do you want to go away for? </FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="question3"
								render={({ field }) => (
									<FormItem>

										<FormLabel> 3. What's your budget? </FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="question4"
								render={({ field }) => (
									<FormItem>

										<FormLabel> 4. Are you more of cities, beaches or simply nature? </FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="question5"
								render={({ field }) => (
									<FormItem>

										<FormLabel> 5. What excites you the most - cultural exploration, outdoor adventures or culinary experiences? </FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>


							<FormField
								control={form.control}
								name="question6"
								render={({ field }) => (
									<FormItem>

										<FormLabel>6. Will you be travelling alone, with a partner, family or friends? </FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											This is your public display name.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>


							<DialogFooter className="flex justify-start items-start p-4 rounded-b-lg">

								<Button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Plan my dream Holiday!</Button>

							</DialogFooter>


						</DialogContent>

					</form>

				</Form>


			</Dialog>
		</main>


	);

}
