
import Link from "next/link";
import { LinkType } from "../Nav";




interface NavLinksProps {
  links: LinkType[]; 
}


export default function NavLinks({ links} : NavLinksProps) {
	return (
		<ul className="flex justify-center items-center gap-10 font-semibold uppercase">
			{links.map((link, index) => (
				<li className="hover:text-blue-300" key={index}>
					<Link href={link.href}>{link.text}</Link>
				</li>
			))}
		</ul>
	);
}

