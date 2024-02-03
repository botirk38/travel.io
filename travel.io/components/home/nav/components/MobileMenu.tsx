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

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import Link from "next/link";

export function MobileMenu() {
	return (
		<DropdownMenu>
			<DropdownMenuTrigger asChild>
				<FontAwesomeIcon icon={faBars} size="xl" />

			</DropdownMenuTrigger>
			<DropdownMenuContent className="w-56">
				<DropdownMenuLabel>Travel.io</DropdownMenuLabel>
				<DropdownMenuSeparator />
				<DropdownMenuGroup>
					<DropdownMenuItem>

						<Link href="/plans/create"> Create Plan</Link>

					</DropdownMenuItem>
					<DropdownMenuItem>
						<Link href="/billing">Billing</Link>
					</DropdownMenuItem>
					<DropdownMenuItem>
						<Link href="/plans"> Plans </Link>
					</DropdownMenuItem>
				</DropdownMenuGroup>

				<DropdownMenuSeparator />
				<DropdownMenuItem><Link href="https://github.com/botirk38/travel.io"> GitHub </Link> </DropdownMenuItem>
				<DropdownMenuItem><Link href="/support">  Support </Link> </DropdownMenuItem>
				<DropdownMenuSeparator />

			</DropdownMenuContent>
		</DropdownMenu>
	)
}

