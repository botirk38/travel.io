"use client"

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"

import { Button } from "@/components/ui/button"
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input"

import { toast } from "@/components/ui/use-toast"
import { OAuthUser, SpringUser } from "@/types/userTypes"
import { useRouter } from "next/navigation"
import { updateProfile } from "@/api/profile/updateProfile"
import { useMutation } from "react-query"

const phoneRegex = new RegExp(
  /^([+]?[\s0-9]+)?(\d{3}|[(]?[0-9]+[)])?([-]?[\s]?[0-9])+$/
);

const profileFormSchema = z.object({
  username: z
    .string()
    .min(2, {
      message: "Username must be at least 2 characters.",
    })
    .max(30, {
      message: "Username must not be longer than 30 characters.",
    }),
  email: z
    .string({
      required_error: "Please select an email to display.",
    })
    .email(),

  phone: z.string().regex(phoneRegex, 'Invalid Phone number'),
  address: z.string({ required_error: "Please enter a valid address" }).min(1),
  password: z.string().min(4).optional(),
  id: z.string().optional()
})

export type ProfileFormValues = z.infer<typeof profileFormSchema>



export function ProfileForm({ data }: { data: SpringUser | OAuthUser }) {

  const router = useRouter();




  const defaultValues: Partial<ProfileFormValues> = {

    username: data?.username,
    email: data?.email,
    phone: data?.phone,
    address: data?.address,

  }

  const form = useForm<ProfileFormValues>({
    resolver: zodResolver(profileFormSchema),
    defaultValues,
    mode: "onChange",
  })

  const mutation = useMutation((values: ProfileFormValues) => updateProfile(values));

  function onSubmit(values: ProfileFormValues) {

    values = { id: data.id, ...values }

    mutation.mutate(values, {
      onSuccess: (values: ProfileFormValues) => {
        console.log('Signup successful', values);
        toast({
          title: "Profile was updated successfully.",
          description: "Refreshing page"


        })
        router.push("/profile");
      },
      onError: (error: any) => {

        console.log("Values: ", values);
        toast({
          title: "Profile update failed",
          description: "Please try again."
        })
        console.error('Login error form', error);
      },
    });




    router.push("/profile");
  }

  console.log(data);


  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <FormField
          control={form.control}
          name="username"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Username</FormLabel>
              <FormControl>
                <Input placeholder="shadcn" {...field} />
              </FormControl>
              <FormDescription>
                This is your public display name. It can be your real name or a
                pseudonym. You can only change this once every 30 days.
              </FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Password</FormLabel>
              <FormControl>
                <Input placeholder="password" {...field} type="password" />
              </FormControl>
              <FormDescription>
                This is your password, keep it secret.              
                </FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input placeholder="shadcn@shadcn.com" {...field} />

              </FormControl>

              <FormDescription>
                You can manage your verified email address here
              </FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />



        <FormField
          control={form.control}
          name="phone"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Phone Number</FormLabel>
              <FormControl>
                <Input placeholder="+44 123456789" {...field} />

              </FormControl>

              <FormDescription>
                You can manage your verified phoen number here
              </FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="address"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Address</FormLabel>
              <FormControl>
                <Input placeholder="Canary Wharf" {...field} />

              </FormControl>

              <FormDescription>
                You can manage your address here
              </FormDescription>
              <FormMessage />
            </FormItem>
          )}
        />


        <Button type="submit">Update profile</Button>
      </form>
    </Form>
  )
}
