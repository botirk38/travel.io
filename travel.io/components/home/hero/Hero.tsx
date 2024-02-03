import { Button } from "@/components/ui/button"
import { OAuthUser } from "@/types/userTypes"
import { faArrowRight } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import Image from "next/image"
import { faPlane } from "@fortawesome/free-solid-svg-icons"
import Link from "next/link"

interface HeroProps {

	data: OAuthUser

}

export default function Hero({ data }: HeroProps) {


	return (

		<section className="flex flex-col items-center w-full justify-center gap-14 md:items-start">

			<h1 className="font-bold text-xl text-wrap md:text-2xl"> 👋 Welcome, <span className="text-blue-500">{data?.name}</span> to Travel.io </h1>


			<div className="shadow-2xl rounded-xl flex flex-col-reverse w-full justify-center items-center p-4 text-center md:flex-row md:text-start md:p-6">

				<div className="flex flex-col w-full justify-center items-center gap-6 md:items-start">

					<h1 className="font-bold text-lg"> Start Planning Your Vacation Today! </h1>


					<p> Create your ultimate vacation plan with the click of a button. Whether you want to go somewhere exotic
						or just a getaway to the countryside. We got you covered.

					</p>


					<Button className="gap-2">
						<Link href="/plans/create">Create your plan</Link>
						<FontAwesomeIcon icon={faPlane} />

					</Button>



				</div>



				<aside className="flex">
					<Image src={"/images/hero_img.jpeg"} alt="Hero Image showing a family on vacation." width={500} height={500} className="object-cover aspect-auto" />

				</aside>

			</div>




		</section>


	)

}
