
import { getUserInfo } from "@/api/user/userInfo";
import MobileNav from "@/components/home/nav/MobileNav";
import AuthenticationGuard from "@/components/ui/AuthenticationGuard";

import Nav from "@/components/home/nav/Nav";
import { OAuthUser } from "@/types/userTypes";
import Hero from "@/components/home/hero/Hero";
import Plans from "@/components/home/plans/Plans";



export default async function Page() {


	const data: OAuthUser = await getUserInfo();
	console.log(data);


	return (

		<AuthenticationGuard>

			<main className="flex flex-col justify-center p-3 items-center gap-14 md:px-10 lg:px-24 md:py-10">

				<MobileNav data={data} />

				<Nav data={data} />

				<Hero data={data} />

				<Plans />

			</main>

		</AuthenticationGuard>


	);

}
