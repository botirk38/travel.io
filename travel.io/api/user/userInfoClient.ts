export async function getUserInfoClient() {


	try {
		const response = await fetch('http://localhost:8080/users/me', {
			headers: {
				'Content-Type': 'application/json',
			},
			method: 'GET',
			credentials: 'include',
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
