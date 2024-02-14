import { redirect } from "next/navigation"
import { getUserInfo } from "@/api/user/userInfo"

export default async function AuthenticationGuard({ children }: { children: React.ReactNode }) {

  const userInfoResponse = await getUserInfo();
  console.log(userInfoResponse);


  if (!userInfoResponse) {
     redirect('/')
  }

  return (
    <>
      {children}
    </>
  )



}
