import { ProfileFormValues } from "@/app/profile/profile-form"


export const updateProfile = async (values: ProfileFormValues) => {

	const id = values.id;


	const endpoint = `http://localhost:8080/users/update-profile/${id}`;


	try {

		const response = await fetch(endpoint, {

			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},

			body: JSON.stringify(values),

			credentials: 'include'
		});


		if (!response.ok) {
			const error = await response.text();
			throw new Error(`Server responded with ${response.status}: ${error}`);
		}

		return await response.json();







	} catch (err: any) {
		console.error('Signup error:', err);
		throw new Error('Failed to sign up. Please try again later.');



	}



}
