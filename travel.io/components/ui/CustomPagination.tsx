import {
	Pagination,
	PaginationContent,
	PaginationEllipsis,
	PaginationItem,
	PaginationLink,
	PaginationNext,
	PaginationPrevious,
} from "@/components/ui/pagination";

interface CustomPaginationProps {
  
  totalItems: number,
  itemsPerPage: number,
  currentPage: number,
  onPageChange: (page: number) => void,

}

const CustomPagination: React.FC<CustomPaginationProps> = ({
	totalItems,
	itemsPerPage,
	currentPage,
	onPageChange,
}) => {
	const pageCount = Math.ceil(totalItems / itemsPerPage);

	const renderPaginationLinks = () => {
		const items = [];
		for (let number = 1; number <= pageCount; number++) {
			items.push(
				<PaginationItem key={number}>
					<PaginationLink
						href="#"
						onClick={() => onPageChange(number)}
						isActive={number === currentPage}
					>
						{number}
					</PaginationLink>
				</PaginationItem>,
			);
		}
		return items;
	};

	return (
		<Pagination>
			<PaginationContent>
				<PaginationItem>
					<PaginationPrevious
						href="#"
						onClick={() => onPageChange(currentPage - 1)}
					/>
				</PaginationItem>
				{renderPaginationLinks()}
				<PaginationItem>
					<PaginationNext
						href="#"
						onClick={() => onPageChange(currentPage + 1)}
					/>
				</PaginationItem>
			</PaginationContent>
		</Pagination>
	);
};

export default CustomPagination;
