import { z } from "zod";


const formSchema = z.object({
	username: z.string().min(2).max(50),
	name: z.string().min(2).max(50),
	password: z.string().min(2).max(50),
	email: z.string().email().max(320),

});


export const postSignup = async (values: z.infer<typeof formSchema>) => {
	const controller = new AbortController();
	const { signal } = controller;

	try {
		const response = await fetch('http://localhost:8080/users/register', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(values),
			signal,
		});

		if (!response.ok) {
			throw new Error(`Server responded with ${response.status}: ${response.statusText}`);
		}

		return await response.json();
	} catch (error) {
		console.error('Signup error:', error);
		throw new Error('Failed to sign up. Please try again later.');
	}
};
