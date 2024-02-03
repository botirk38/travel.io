"use client";


import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { MobileMenu } from "./components/MobileMenu";
import { ProfileMenu } from "./components/ProfileMenu";


export default function MobileNav({ data }) {




	return (
		<nav className="flex justify-between items-center w-full md:hidden py-4 px-6">

			<MobileMenu />

			<h1 className="font-bold text-2xl text-blue-500">Travel.io</h1>

			<ProfileMenu data={data} />




		</nav>
	);
}

