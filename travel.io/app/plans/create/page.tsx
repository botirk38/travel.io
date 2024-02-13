"use client";

import AuthenticationGuard from "@/components/ui/AuthenticationGuard";

import { z } from "zod"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"

import { Button } from "@/components/ui/button"
import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import React, { useEffect } from "react";
import {
	Dialog,
	DialogContent,
	DialogDescription,
	DialogFooter,
	DialogHeader,
	DialogTitle,
	DialogTrigger,
	DialogClose
} from "@/components/ui/dialog"
import { Label } from "@/components/ui/label"

import Nav from "@/components/home/nav/Nav";
import { getUserInfoClient } from "@/api/user/userInfoClient";
import { useQuery } from "react-query";
import MobileNav from "@/components/home/nav/MobileNav";

const formSchema = z.object({
	questions: z.array(z.object({
		q: z.string().min(2),
	})),
});

export default function CreatePlanPage() {
	const maxSize = 5; // Desired size of the array
	const [questions, setQuestions] = React.useState(
		Array.from({ length: 1 }, () => ({ q: 'What are you feeling?' }))
	);
	const [question, setQuestion] = React.useState("");

	const { isLoading, isError, data } = useQuery('userInfo', getUserInfoClient)


	useEffect(() => {

		console.log(data);

	}, [data])

	const form = useForm({
		resolver: zodResolver(formSchema),
		defaultValues: {
			questions: [{ q: '' }],
		},
	});

	const addQuestion = () => {
		setQuestions(currentQuestions => [...currentQuestions, { q: question }]);
		form.reset({ ...form.getValues(), questions: [...form.getValues().questions, { q: '' }] });
	};

	const onSubmit = (values: z.infer<typeof formSchema>) => {
		console.log(values);
	};

	return (
		<main className="flex flex-col justify-center items-center w-full">

			<nav className="w-full md:p-10">
				<Nav data={data} />
				<MobileNav data={data} />

			</nav>

			<section className="w-full flex-col justify-center items-center p-10 ">
				<h1 className="text-blue-500 font-bold text-xl mb-10 md:text-2xl">Create your plan today!</h1>
				<Form {...form} >
					<form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
						{questions.map((question, index) => (
							<FormField
								key={index}
								control={form.control}
								name={`questions.${index}.q`}
								render={({ field }) => (
									<FormItem className="space-y-4">
										<FormLabel>{question.q}</FormLabel>
										<FormControl>
											<Input {...field} />
										</FormControl>
									</FormItem>
								)}
							/>
						))}

						<div className="space-x-4 w-full">

							{questions.length < maxSize && (
								<Dialog>

									<DialogTrigger asChild>
										<Button variant="outline">Add a Question</Button>
									</DialogTrigger>


									<DialogContent>

										<DialogHeader>
											<DialogTitle>Add any question</DialogTitle>
											<DialogDescription>
												To improve your travel plan we recommend adding your preferences.
											</DialogDescription>
										</DialogHeader>


										<div className="grid gap-4 py-4">


											<div className="grid grid-cols-4 items-center gap-4">
												<Label htmlFor="name" className="text-right">
													Question
												</Label>
												<Input id="question" className="col-span-3" onChange={(e) => setQuestion(e.target.value)} />


											</div>
										</div>

										<DialogClose asChild>

											<Button type="submit" onClick={addQuestion}>Add question</Button>



										</DialogClose>


									</DialogContent>





								</Dialog>

							)}
							<Button type="submit">Create your plan.</Button>

						</div>

					</form>
				</Form>
			</section>
		</main>
	);
}
