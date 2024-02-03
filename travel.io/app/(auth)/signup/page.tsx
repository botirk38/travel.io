"use client";

import { CircleUserIcon, FacebookIcon, GithubIcon } from "lucide-react";
import { Button } from "@/components/ui/button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import Link from "next/link";
import * as z from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useRouter } from "next/navigation";
import { postSignup } from "@/api/signup/signup";


const formSchema = z.object({
	username: z.string().min(2).max(50),
	password: z.string().min(2).max(50),
	email: z.string().email().max(320),
});
import { useMutation } from "react-query";
import { useToast } from "@/components/ui/use-toast";

export default function Home() {
	// 1. Define your form.
	const form = useForm<z.infer<typeof formSchema>>({
		resolver: zodResolver(formSchema),
		defaultValues: {
			username: "",
			password: "",
			email:"",
		},
	});

	const { toast } = useToast();

	const router = useRouter();

	const mutation = useMutation((values: z.infer<typeof formSchema>) => postSignup(values));



	function onSubmit(values: z.infer<typeof formSchema>) {
		mutation.mutate(values, {
			onSuccess: (data) => {
				console.log('Signup successful', data);
				toast({
					title: "Signup Successful",
					description: "Redirecting to Login"


				})
				router.push("")
			},
			onError: (error) => {
				toast({
					title: "Signup failed",
					description: "Please try again."
				})
				console.error('Signup error form', error);
			},
			// Optional: Do something after mutation is settled (success or failure)
		});

	}


	function googleSignIn() {

		router.push("http://localhost:8080/oauth2/authorization/google");



	}

	function githubSignIn() {
		router.push("http://localhost:8080/oauth2/authorization/github");


	}



	return (
		<main className="flex justify-center items-center w-full min-h-screen">
			<section className="flex flex-col justify-center items-center w-80 md:w-[30rem] rounded-xl shadow-xl">
				<div className="w-full flex flex-col justify-center items-center gap-8 p-8 md:p-10">
					<CircleUserIcon className="w-8 h-8" />

					<h1 className="text-2xl font-bold"> Sign up </h1>

					<p>
						Already have an account?{" "}
						<Link className="underline" href={"/"}>
							Log in
						</Link>{" "}
					</p>


					<Button className="rounded-full gap-2 p-6" onClick={githubSignIn}>
						<GithubIcon />

						<p> Log in with Github </p>
					</Button>

					<Button className="rounded-full gap-2 p-6" onClick={googleSignIn}>
						<FontAwesomeIcon icon={faGoogle} size="xl" />
						<p> Log in with Google </p>
					</Button>

					<div className="flex justify-center items-center gap-2 w-full">
						<div className="w-full h-1 bg-gray-300" />
						<h3 className="text-lg text-gray-400 uppercase"> Or </h3>
						<div className="w-full h-1 bg-gray-300" />
					</div>

					<Form {...form}>
						<form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
							<FormField
								control={form.control}
								name="username"
								render={({ field }) => (
									<FormItem>
										<FormLabel>Username</FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											Choose your unique username.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="email"
								render={({ field }) => (
									<FormItem>
										<FormLabel>Email</FormLabel>
										<FormControl>
											<Input placeholder="shadcn" {...field} />
										</FormControl>
										<FormDescription>
											Choose your unique email
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<FormField
								control={form.control}
								name="password"
								render={({ field }) => (
									<FormItem>
										<FormLabel>Password</FormLabel>
										<FormControl>
											<Input placeholder="abc1234" {...field} />
										</FormControl>
										<FormDescription>
											Choose a strong password for your account.
										</FormDescription>
										<FormMessage />
									</FormItem>
								)}
							/>

							<Button type="submit" className="rounded-full w-full p-6">
								{" "}
								Sign up with email{" "}
							</Button>
						</form>
					</Form>
				</div>
			</section>
		</main>
	);
}
