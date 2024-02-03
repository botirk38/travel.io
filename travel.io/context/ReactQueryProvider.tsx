"use client"

import {
	QueryClient,
	QueryClientProvider,
} from 'react-query'

function ReactQueryProvider({ children }: any) {
	const client = new QueryClient();

	return (
		<>
			<QueryClientProvider client={client}>
				{children}
			</QueryClientProvider>
		</>
	)
}

export { ReactQueryProvider }
