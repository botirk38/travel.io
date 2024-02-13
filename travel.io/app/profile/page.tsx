import { Separator } from "@/components/ui/separator"
import { ProfileForm } from "./profile-form"
import AuthenticationGuard from "@/components/ui/AuthenticationGuard"
import { getUserInfo } from "@/api/user/userInfo"

export default async function SettingsProfilePage() {

  const userInfo = await getUserInfo();


  return (
    <AuthenticationGuard>
      <div className="space-y-6">
        <div>
          <h3 className="text-lg font-medium">Profile</h3>
          <p className="text-sm text-muted-foreground">
            This is how others will see you on the site.
          </p>
        </div>
        <Separator />
        <ProfileForm data={userInfo} />
      </div>

    </AuthenticationGuard>

  )
}
