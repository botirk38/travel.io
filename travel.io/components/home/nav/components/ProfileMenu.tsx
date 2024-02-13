
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuGroup,
	DropdownMenuItem,
	DropdownMenuLabel,
	DropdownMenuPortal,
	DropdownMenuSeparator,
	DropdownMenuShortcut,
	DropdownMenuSub,
	DropdownMenuSubContent,
	DropdownMenuSubTrigger,
	DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

import Link from "next/link";


export function ProfileMenu( {data} ) {


	function getFirstLetterOfFirstName(name: string) {
		return name?.substring(0, 1);
	}

	function getFirstLetterOfLastname(name: string) {
		return name?.split(' ')[1]?.substring(0, 1);
	}



	return (


		<DropdownMenu>
			<DropdownMenuTrigger asChild>

				<Avatar>
					<AvatarImage src={data?.imageUrl} />
					<AvatarFallback>
						{getFirstLetterOfFirstName(data?.name)}{getFirstLetterOfLastname(data?.name)}
					</AvatarFallback>
				</Avatar>

			</DropdownMenuTrigger>
			<DropdownMenuContent className="w-56">
				<DropdownMenuLabel>My Account</DropdownMenuLabel>
				<DropdownMenuSeparator />
				<DropdownMenuGroup>
					<DropdownMenuItem>

						<Link href="/profile"> View Profile </Link>

					</DropdownMenuItem>
					
				</DropdownMenuGroup>

				

			</DropdownMenuContent>
		</DropdownMenu>
	);


}
