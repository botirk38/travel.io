import { cookies } from "next/headers";

export async function getUserInfo() {
	const cookieStore = cookies();
	const JSSESSION = cookieStore.get('JSESSIONID')

	const controller = new AbortController();
	const { signal } = controller;

	try {
		const response = await fetch('http://localhost:8080/oauth/me', {
			headers: {
				'Content-Type': 'application/json',
				'Cookie': `JSESSIONID=${JSSESSION?.value}`
			},
			method: 'GET',
			signal,
		});

		if (!response.ok) {
			return null;			
		}

		return await response.json();

	} catch (error) {
		console.error('Failed to fetch UserInfo', error);
		throw new Error('Failed to fetch UserInfo. Please try again later.');
	}


}
