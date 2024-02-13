import { cookies } from "next/headers";

export async function getUserInfo() {
	const cookieStore = cookies();
	const JSSESSION = cookieStore.get('JSESSIONID')
	console.log(JSSESSION);



	try {
		const response = await fetch('http://localhost:8080/users/me', {
			headers: {
				'Content-Type': 'application/json',
				'Cookie': `JSESSIONID=${JSSESSION?.value}`
			},
			method: 'GET',
			cache: 'no-store',
		});

		if (!response.ok) {
			console.error(await response.text());
			return null;			
		}

		return await response.json();

	} catch (error) {
		console.error('Failed to fetch UserInfo', error);
		throw new Error('Failed to fetch UserInfo. Please try again later.');
	}


}


