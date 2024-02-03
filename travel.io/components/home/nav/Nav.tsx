import { ProfileMenu } from "./components/ProfileMenu";
import NavLinks from "./components/NavLinks";
import { OAuthUser } from "@/types/userTypes";

import Link from "next/link";

export type LinkType = {
	text: string;
	href: string;


}

interface NavProps {
	
	data: OAuthUser

}

export default function Nav({ data } : NavProps) {




	const links: LinkType[] = [
		{ text: "Plans", href: "/plans/create" },
		{ text: "Billing", href: "/billing" },
		{ text: "Plans", href: "/plans" },
		{ text: "Github", href: "https://github.com/botirk38/travel.io" },
		{ text: "Support", href: "/support" },
	];

	return (
		<nav className="hidden md:flex items-center justify-between w-full">
			<h1 className="font-bold text-2xl text-blue-500"><Link href="/home">Travel.io </Link></h1>
			<NavLinks links={links}  />
			<ProfileMenu data={data} />
		</nav>
	);
}

