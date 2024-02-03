
import { getUserInfo } from "@/api/user/userInfo";
import AuthenticationGuard from "@/components/ui/AuthenticationGuard";


export default async function Page() {


	const data = await getUserInfo();


	return (

		<AuthenticationGuard>

			<main className="flex flex-col justify-center items-center gap-6">

				<h1> Welcome, <span>{data?.name}</span> to Travel.io </h1>



			</main>

		</AuthenticationGuard>


	);

}
